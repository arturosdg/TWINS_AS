package es.imposoft.twins;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.twins.components.GameMode;

public class GameActivity extends AppCompatActivity {

    Chronometer chronoTimer;
    Button pauseButton;

    private int maxCards;
    Button[] buttons;
    Card[] cards;
    Context context;
    int tapCounter, pauseTapCounter, visibleCards;
    Scoreboard scoreboard;

    int acertadosSeguidos;
    boolean anteriorAcertada, pausedGame;

    int score;
    private int restantMatches;
    private boolean isClickable;
    List<Card> pairs = new ArrayList<>();

    long timeWhenStopped;

    public void playByGameModes(View view, GameMode gameMode) {
        setContentView(R.layout.activity_gamescene4x4);

        pauseButton = findViewById(R.id.button_pause);
        pauseButton.setVisibility(View.INVISIBLE);

        chronoTimer = findViewById(R.id.text_timer);
        timeWhenStopped = 0;

        pauseTapCounter = 0;
        tapCounter = 0;
        maxCards = 16;
        restantMatches = maxCards / 2;
        visibleCards = 0;

        buttons = new Button[maxCards];
        cards = new Card[maxCards];
        isClickable = false;
        pausedGame = false;

        score = 0;
        scoreboard = new Scoreboard();

        acertadosSeguidos = 0;
        anteriorAcertada = false;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        scoreboard.loadHighscores(sp);

        fillArray();
        createCards();

    }

    public void showScoreboard(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        scoreboard.addScore(score);
        scoreboard.saveHighscores(sp);

        Intent intent = new Intent(GameActivity.this, Popup.class);
        intent.putExtra("SCORE",scoreboard);
        intent.putExtra("LAST",score);
        intent.putExtra("TYPE", Popup.WindowType.SCOREBOARD);

        startActivityForResult(intent,1);
    }

    @SuppressLint("WrongViewCast")
    public void changeSprite(final View view) {
        // La primera vez que el jugador toca la pantalla, se giran todas las cartas.
        if(tapCounter == 0) {
            turnAllCards();
            //  Espera 3 segundos hasta que se vuelvan a girar
            Handler secs3 = new Handler();
            secs3.postDelayed(new Runnable() {
                public void run() {
                    turnAllCards();
                    startChronometer();
                    pauseButton.setVisibility(View.VISIBLE);
                }
            }, 3000);

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
                                actualizarControladorDePuntos(10);
                                pairs.clear();
                                visibleCards = 0;
                                stopChronometer();
                            } else {
                                actualizarControladorDePuntos(-3);
                                Handler secs1 = new Handler();
                                secs1.postDelayed(new Runnable() {
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
    }

    @SuppressLint("SetTextI18n")
    private void actualizarControladorDePuntos(int aSumar) {
        score += aSumar;
        if (aSumar < 0) {
            anteriorAcertada = false;
            acertadosSeguidos = 0;
        } else {
            if (anteriorAcertada) {
                score += Math.pow(2, acertadosSeguidos);
            }
            acertadosSeguidos++;
            anteriorAcertada = true;
        }
        ((TextView) findViewById(R.id.text_score)).setText("Puntos: " + score);
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
            //int buttonID = getResources().getIdentifier("imgPos" + i,"id", getPackageName());
            buttons[i] = findViewById(imageID);
        }
    }

    private void createCards() {
        for (int i = 0; i < maxCards; i++) {
            cards[i] = new Card(buttons[i], context);
        }
    }

    private void stopChronometer() {
        if(restantMatches == 0) {
            ((Chronometer) findViewById(R.id.text_timer)).stop();
            Handler secs1 = new Handler();
            secs1.postDelayed(new Runnable() {
                public void run() {
                    showScoreboard();
                }
            }, 1000);
        }
    }

    private void startChronometer() {
        chronoTimer.setBase(SystemClock.elapsedRealtime());
        //if game mode requires ((Chronometer) findViewById(R.id.text_timer)).setCountDown(true);
        chronoTimer.start();
    }

    private void setClickable(Button[] buttons) {
        for (Button b: buttons) {
            if(getButton(b).isPaired()) {}
            else b.setClickable(isClickable);
        }
        isClickable = !isClickable;
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

    private Card getButton(Button button) {
        for (Card c : cards) {
            if(c.getCardButton() == button) return c;
        }
        return null;
    }
}