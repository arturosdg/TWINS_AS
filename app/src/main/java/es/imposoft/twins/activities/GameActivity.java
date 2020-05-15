package es.imposoft.twins.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import es.imposoft.twins.Card;
import es.imposoft.twins.MusicService;
import es.imposoft.twins.SucceededLevel;
import es.imposoft.twins.components.Deck;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.R;
import es.imposoft.twins.Scoreboard;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.gametypes.Game;
import es.imposoft.twins.plantilla.*;

public class GameActivity extends AppCompatActivity {
    DeckTheme themeCard;
    Chronometer chronoTimer;
    Button pauseButton;
    Button restartButton;
    TextView scoreText;

    private int maxCards;
    Button[] buttons;
    ArrayList<Card> cards;
    Context context;
    int tapCounter, pauseTapCounter, visibleCards;
    Scoreboard scoreboard;

    int acertadosSeguidos;
    boolean anteriorAcertada, pausedGame;

    int score;
    private int restantMatches;
    private boolean isClickable;
    List<Card> pairs = new ArrayList<>();
    int tapErrors;

    long timeWhenStarted, timeWhenStopped;

    Bundle windowInfo;
    Game game;
    Gson gson;
    AbstractScore scoreManager;
    SucceededLevel succeededLevels;
    Deck deck;

    SharedPreferences sharedPreferences;
    private GameMode gameMode;
    int levelPlayed;

    Handler timeHandler;
    Intent intent;
    private String gscoreboard;
    private String glevels;
    ActionBar mToolbar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        windowInfo = getIntent().getExtras();
        timeHandler = new Handler();

        gson = new Gson();
        game = gson.fromJson((String) windowInfo.get("GAME"),Game.class);
        if(windowInfo.get("LEVEL") != null) { levelPlayed = (int) windowInfo.get("LEVEL"); }
        context = getApplicationContext();
        super.onCreate(savedInstanceState);

        selectLayout();

        pauseButton = findViewById(R.id.button_pause);
        pauseButton.setVisibility(View.INVISIBLE);

        restartButton = findViewById(R.id.button_restart);
        restartButton.setVisibility(View.INVISIBLE);

        scoreText = findViewById(R.id.text_score);

        chronoTimer = findViewById(R.id.text_timer);
        timeWhenStopped = 0;
        setChronometerType();

        pauseTapCounter = 0;
        tapCounter = 0;
        tapErrors = 0;
        maxCards = game.getCardAmount();
        restantMatches = maxCards / 2;
        visibleCards = 0;

        buttons = new Button[maxCards];
        cards = new ArrayList<>(maxCards);
        isClickable = false;
        pausedGame = false;

        MusicService bg = MusicService.getInstance(getApplicationContext());
        bg.stopMusic();

        gameMode = game.getGameMode();

        score = 0;
        scoreboard = new Scoreboard(game.getId());
        getScoreManager();

        acertadosSeguidos = 0;
        anteriorAcertada = false;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        scoreboard.loadHighscores(sharedPreferences);
        if(isLevelMode()) {
            succeededLevels = new SucceededLevel(gameMode.ordinal());
            succeededLevels.loadSuccedeedLevels(sharedPreferences);
        }

        gscoreboard = ""; glevels = "";

        fillArray();
        themeCard = game.getDeckTheme();
        deck =  new Deck();
        deck.assignCardTheme(themeCard, cards, game, buttons, context);

    }

    public void showScoreboard(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        scoreboard.addScore(score);
        scoreboard.saveHighscores(sharedPreferences);

        intent = new Intent(GameActivity.this, PopupActivity.class);
        gson = new Gson();
        gscoreboard = gson.toJson(scoreboard);
        intent.putExtra("SCORE",gscoreboard);
        intent.putExtra("TYPE", PopupActivity.WindowType.SCOREBOARD);
        intent.putExtra("THEME",themeCard);
        intent.putExtra("LEVELMODE", false);
        if(isLevelMode()) {
            if(!succeededLevels.getSuccedeedLevels().contains(levelPlayed)) {
                succeededLevels.addSuccedeedLevel(levelPlayed);
            }
            succeededLevels.saveSucceededLevels(sharedPreferences);
            glevels = gson.toJson(succeededLevels);
            intent.putExtra("LEVELMODE", true);
        }
        startActivityForResult(intent,1);
    }

    public void showGameOver(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        scoreboard.saveHighscores(sharedPreferences);

        intent = new Intent(GameActivity.this, PopupActivity.class);
        gson = new Gson();
        gscoreboard = gson.toJson(scoreboard);
        intent.putExtra("SCORE",gscoreboard);
        intent.putExtra("TYPE", PopupActivity.WindowType.GAMEOVER);
        intent.putExtra("THEME",themeCard);
        intent.putExtra("LEVELMODE", false);
        if(isLevelMode()) {
            succeededLevels.saveSucceededLevels(sharedPreferences);
            glevels = gson.toJson(succeededLevels);
            intent.putExtra("LEVELMODE", true);
        }
        startActivityForResult(intent,1);
    }

    @SuppressLint("WrongViewCast")
    public void changeSprite(View view) {
        if(tapCounter == 0) {
            turnAllCards();
            timeHandler.postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                public void run() {
                    turnAllCards();
                    startChronometer();
                    isTimeOver();
                    pauseButton.setVisibility(View.VISIBLE);
                    restartButton.setVisibility(View.VISIBLE);
                }
            }, game.getRevealSeconds() * 1000);
        } else {
            for (Card card : cards) {
                if(visibleCards < 2 && !pausedGame)
                    if (card.getCardButton().getId() == view.getId()) {
                        card.turnCard();
                        visibleCards++;
                        card.getCardButton().setClickable(false);
                        if(pairs.size()<2)  {
                            pairs.add(card);
                        }
                        if (pairs.size() == 2) {
                            if (pairs.get(0).equals(pairs.get(1))) {
                                restantMatches--;
                                pairs.get(0).setPaired();
                                pairs.get(1).setPaired();
                                anteriorAcertada = true;
                                updateScore();
                                pairs.clear();
                                visibleCards = 0;
                                tapErrors = 0;
                            } else {
                                tapErrors++;
                                anteriorAcertada = false;
                                updateScore();
                                timeHandler.postDelayed(new Runnable() {
                                    @RequiresApi(api = Build.VERSION_CODES.M)
                                    public void run() {
                                        turnVisibleCards();
                                        visibleCards = 0;
                                    }
                                }, 1000);
                                pairs.get(0).getCardButton().setClickable(true);
                                pairs.get(1).getCardButton().setClickable(true);

                                pairs.clear();
                            }
                        }
                    }
            }
        }
        tapCounter++;
        stopChronometer();
    }

    @SuppressLint("SetTextI18n")
    private void updateScore() {
    /**
     * Si crono = DESCENDENTE / NONE -> */
        score = scoreManager.updateScore(anteriorAcertada);
        scoreText.setText("Puntos: " + score);
    }

    private void turnAllCards() {
        for (Card card : cards) {
            card.turnCard();
        }
        setClickable(buttons);
    }

    private void turnVisibleCards() {
        for (Card card : cards) {
            card.turnVisibleCards();
        }
    }

    private void fillArray(){
        for(int i = 0; i < maxCards; i++) {
            int imageID = getResources().getIdentifier("imgPos" + i,"id", getPackageName());
            buttons[i] = findViewById(imageID);
        }
    }

    private void stopChronometer() {
        if(tapErrors == 5) timeHandler.postDelayed(new Runnable() {
            public void run() {
                showGameOver();
            }
        }, 650);
        if(restantMatches == 0) {
            chronoTimer.stop();
            timeHandler.postDelayed(new Runnable() {
                public void run() {
                    if(score >= game.getMinScore()) showScoreboard();
                    else showGameOver();
                }
            }, 650);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void startChronometer() {
        chronoTimer.setBase(SystemClock.elapsedRealtime());
        timeWhenStarted = chronoTimer.getBase();
        if (chronoTimer.isCountDown()) {
            chronoTimer.setBase(timeWhenStarted + (game.getSeconds() * 1000));
        }
        chronoTimer.start();
    }

    private void isTimeOver(){
        chronoTimer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (timeWhenStarted + (game.getSeconds() * 1000) <= SystemClock.elapsedRealtime()) {
                    chronoTimer.stop();
                    timeHandler.postDelayed(new Runnable() {
                        public void run() {
                            showGameOver();
                        }
                    }, 650);
                }
            }
        });
    }

    public void pauseGame(View view) {
        if(pauseTapCounter % 2 == 0) {
            timeWhenStopped = (chronoTimer.getBase() - SystemClock.elapsedRealtime());
            chronoTimer.stop();
            pausedGame = !pausedGame;
        }
        else {
            chronoTimer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            chronoTimer.start();
            pausedGame = !pausedGame;
        }
        pauseTapCounter++;
    }

    public void restart(View view) {
        intent = new Intent(this, GameActivity.class);
        Gson gson = new Gson();
        String newGame = gson.toJson(game);
        intent.putExtra("GAME",newGame);
        startActivity(intent);
        this.finish();
    }

    private Card getButton(Button button) {
        for (Card c : cards) {
            if(c.getCardButton() == button) return c;
        }
        return null;
    }
    private void setClickable(Button[] buttons) {
        for (Button b: buttons) {
            if(!getButton(b).getPaired()) b.setClickable(isClickable);
        }
        isClickable = !isClickable;
    }

    public void onFinishPressed(View view){
        pauseGame(view);
        Intent intent = new Intent(GameActivity.this, PopupActivity.class);
        intent.putExtra("TYPE", PopupActivity.WindowType.WARNING);
        intent.putExtra("THEME", themeCard);
        intent.putExtra("LEVELMODE", false);
        if(isLevelMode()) {
            intent.putExtra("LEVELMODE", true);
        }
        startActivityForResult(intent,0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setChronometerType(){
        switch(game.getChronometerType()) {
            case NONE:
                chronoTimer.setVisibility(View.GONE);
                break;
            case NORMAL:
                chronoTimer.setVisibility(View.VISIBLE);
                break;
            case DESCENDING:
                chronoTimer.setVisibility(View.VISIBLE);
                chronoTimer.setCountDown(true);
                break;
        }
    }

    private void getScoreManager() {
        switch(gameMode) {
            case CASUAL:
                scoreManager = new ScoreFree();
                break;
            case LEVELS:
                scoreManager = new ScoreLevels();
                break;
            case STANDARD:
                scoreManager = new ScoreStandard();
                break;
        }
    }

    private void selectLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        switch (game.getCardAmount()) {
            case 16:
                setContentView(R.layout.activity_gamescene4x4);
                break;
            case 20:
                setContentView(R.layout.activity_gamescene4x5);
                break;
            case 24:
                setContentView(R.layout.activity_gamescene4x6);
                break;
        }
    }
    private boolean isLevelMode() { return gameMode.equals(GameMode.LEVELS); }
}
