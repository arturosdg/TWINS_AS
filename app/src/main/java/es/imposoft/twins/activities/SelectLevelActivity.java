package es.imposoft.twins.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.Gson;

import java.util.List;

import es.imposoft.twins.database.SucceededLevel;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.R;
import es.imposoft.twins.builders.ConcreteBuilderLevel;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.director.Director;
import es.imposoft.twins.gametypes.Game;

public class SelectLevelActivity extends AppCompatActivity {
    private final int MAX_LEVELS = 5;
    int level;
    private Button[] levelButtons = new Button[MAX_LEVELS];
    Context context;

    DeckTheme deckTheme;
    private Director director;
    private ConcreteBuilderLevel levelBuilder;
    Game game;

    SucceededLevel succeededLevels;
    List<Integer> levels;

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
        setContentView(R.layout.activity_selectlevel);

        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signedInAccount == null) email = "";
        else email = signedInAccount.getEmail();

        succeededLevels = new SucceededLevel(GameMode.LEVELS.ordinal(), email);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        succeededLevels.loadSuccedeedLevels(sharedPreferences);
        if(succeededLevels != null) {
            levels = succeededLevels.getSuccedeedLevels();
            succeededLevels.saveSucceededLevels(sharedPreferences);
        }

        fillArray();
        initializeLevelButtons();
        setPlayableLevels();
    }

    public void goBack(View view) {
        intent = new Intent(this, SelectionGameModeActivity.class);
        intent.putExtra("THEME", deckTheme);
        startActivity(intent);
        this.finish();
    }

    public void goToLevel(View view) {
        intent = new Intent(this, GameActivity.class);

        createLevelSelected(view.getId());
        game = levelBuilder.getResult();

        Gson gson = new Gson();
        String newGame = gson.toJson(game);
        intent.putExtra("GAME",newGame);
        intent.putExtra("LEVEL", level);
        String glevels = gson.toJson(succeededLevels);

        startActivity(intent);
        this.finish();

    }

    private void fillArray(){
        int levelButton = 0;
        for(int i = 0; i < MAX_LEVELS; i++) {
            levelButton = getResources().getIdentifier("button_level" + (i+1),"id", getPackageName());
            levelButtons[i] = findViewById(levelButton);
        }
    }

    private void createLevelSelected(int id) {
        if(id == levelButtons[0].getId()) { director.constructLevel1(levelBuilder); level = 1; }
        else if (id == levelButtons[1].getId()) { director.constructLevel2(levelBuilder); level = 2; }
        else if (id == levelButtons[2].getId()) { director.constructLevel3(levelBuilder); level = 3; }
        else if (id == levelButtons[3].getId()) { director.constructLevel4(levelBuilder); level = 4; }
        else  { director.constructLevel5(levelBuilder); level = 5; }
    }

    private void setPlayableLevels() {
        if(levels != null) {
            for (int i = 0; i <= levels.size(); i++) {
                levelButtons[ i ].setClickable(true);
                levelButtons[i].setBackground(getDrawable(R.drawable.card));
            }
            for (Integer i : levels) {
                levelButtons[ i - 1 ].setClickable(true);
                levelButtons[ i - 1 ].setBackground(getDrawable(R.drawable.emoji8));
            }
        }

    }

    private void initializeLevelButtons() {
        for (int i = 1; i < levelButtons.length; i++) {
            levelButtons[i].setClickable(false);
            levelButtons[i].setBackground(getDrawable(R.drawable.cardlevelblocked));
        }
    }
}
