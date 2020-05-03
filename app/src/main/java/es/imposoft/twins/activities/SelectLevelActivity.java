package es.imposoft.twins.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import es.imposoft.twins.Deck;
import es.imposoft.twins.R;
import es.imposoft.twins.activities.SelectionGameModeActivity;
import es.imposoft.twins.builders.ConcreteBuilderLevel;
import es.imposoft.twins.director.Director;
import es.imposoft.twins.gametypes.Game;

public class SelectLevelActivity extends AppCompatActivity {
    private final int MAX_LEVELS = 5;
    private int level;
    private Button[] levelButtons = new Button[MAX_LEVELS];
    Context context;

    Deck deck;
    private Director director;
    private ConcreteBuilderLevel levelBuilder;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectlevel);

        Bundle windowInfo = getIntent().getExtras();
        deck = (Deck) windowInfo.get("THEME");
        director = new Director(deck);
        levelBuilder =  new ConcreteBuilderLevel();
        fillArray();
        System.out.println(levelButtons.toString());
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, SelectionGameModeActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void goToLevel(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        createLevelSelected(view.getId());
        //director.constructLevel1(levelBuilder);
        game = levelBuilder.getResult();

        Gson gson = new Gson();
        String newGame = gson.toJson(game);
        intent.putExtra("GAME",newGame);

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
        if(id == levelButtons[0].getId()) director.constructLevel1(levelBuilder);
        else if (id == levelButtons[1].getId()) director.constructLevel2(levelBuilder);
        else if (id == levelButtons[2].getId()) director.constructLevel3(levelBuilder);
        else director.constructLevel1(levelBuilder);
    }
}
