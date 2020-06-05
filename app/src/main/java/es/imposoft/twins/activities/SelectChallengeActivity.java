package es.imposoft.twins.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.Gson;

import java.util.List;

import es.imposoft.twins.R;
import es.imposoft.twins.database.SucceededChallenge;
import es.imposoft.twins.builders.ConcreteBuilderLevel;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.director.Director;
import es.imposoft.twins.gametypes.Game;
import es.imposoft.twins.singleton.MusicService;

public class SelectChallengeActivity extends AppCompatActivity {
    Context context;
    private Director director;
    private ConcreteBuilderLevel levelBuilder;
    private DeckTheme deckTheme;
    private Game game;
    private int currentChallenge;
    private final int MAX_CHALLENGES = 3;
    private Button[] challengeButtons = new Button[MAX_CHALLENGES];
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);

        Bundle windowInfo = getIntent().getExtras();

        deckTheme = (DeckTheme) windowInfo.get("THEME");

        director = new Director(deckTheme);
        levelBuilder =  new ConcreteBuilderLevel();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_selectchallenge);

        fillArray();
    }

    public void goBack(View view) {
        intent = new Intent(this, SelectionGameModeActivity.class);
        intent.putExtra("THEME", deckTheme);
        startActivity(intent);
        this.finish();
    }

    public void goToLevel(View view) {
        intent = new Intent(this, GameActivity.class);

        createChallengeSelected(view.getId());
        game = levelBuilder.getResult();

        Gson gson = new Gson();
        String newGame = gson.toJson(game);
        intent.putExtra("GAME",newGame);
        intent.putExtra("CHALLENGE", currentChallenge);

        startActivity(intent);
        this.finish();

    }

    private void fillArray(){
        int challengeButton = 0;
        for(int i = 0; i < MAX_CHALLENGES; i++) {
            challengeButton = getResources().getIdentifier("button_level" + (i+1),"id", getPackageName());
            challengeButtons[i] = findViewById(challengeButton);
        }
    }

    private void createChallengeSelected(int id) {
        if(id == challengeButtons[0].getId()) { director.constructChallenge1(levelBuilder); currentChallenge = 1; }
        else if (id == challengeButtons[1].getId()) { director.constructChallenge2(levelBuilder); currentChallenge = 2; }
        else if (id == challengeButtons[2].getId()) { director.constructChallenge3(levelBuilder); currentChallenge = 3; }
    }


}
