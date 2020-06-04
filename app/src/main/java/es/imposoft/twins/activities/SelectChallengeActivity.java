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

public class SelectChallengeActivity extends AppCompatActivity {
    private Context context;
    private Director director;
    private SucceededChallenge succeededChallenge;
    private ConcreteBuilderLevel levelBuilder;
    private List<Integer> challenges;
    private DeckTheme deckTheme;
    private Game game;
    private int currentChallenge;
    private final int MAX_CHALLENGES = 3;
    private Button[] challengeButtons = new Button[MAX_CHALLENGES];
    Intent intent;
    private GoogleSignInAccount signedInAccount;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);

        Bundle windowInfo = getIntent().getExtras();

        deckTheme = (DeckTheme) windowInfo.get("THEME");

        director = new Director(deckTheme);
        levelBuilder =  new ConcreteBuilderLevel();

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_selectchallenge);

        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signedInAccount == null) email = "";
        else email = signedInAccount.getEmail();

        succeededChallenge = new SucceededChallenge(email);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        succeededChallenge.loadChallenges(sp);
        if(succeededChallenge != null) {
            challenges = succeededChallenge.getSuccedeedChallenges();
            succeededChallenge.saveChallenges(sp);
        }

        fillArray();
    }

    private void setScreenSettings() {

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
        String gChallenges = gson.toJson(succeededChallenge);

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
