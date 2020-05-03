package es.imposoft.twins.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import es.imposoft.twins.Deck;
import es.imposoft.twins.R;
import es.imposoft.twins.Scoreboard;
import es.imposoft.twins.builders.ConcreteBuilderLevel;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.director.Director;
import es.imposoft.twins.gametypes.Game;



public class SelectionGameModeActivity extends AppCompatActivity {
    Context context;
    Deck deck;
    Game game;

    //TODO Crear aqui con el director una partida segun el modo de juego seleccionada y pasarselo a GameActivity
    /*
        Director director = new Director();
        ConcreteBuilderLevel levelBuilder =  new ConcreteBuilderLevel();
        director.constructStandardGame(levelBuilder);
        Game partida = levelBuilder.getResult();
     */
    private Director director;
    private ConcreteBuilderLevel levelBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgamemode);

        Bundle windowInfo = getIntent().getExtras();
        deck = (Deck) windowInfo.get("THEME");
        director = new Director(deck);
        levelBuilder =  new ConcreteBuilderLevel();
    }

    public void openLevelsLayout(View view) {
        Intent intent = new Intent(this, SelectLevelActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void playCasualGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        director.constructCasualGame(levelBuilder);
        game = levelBuilder.getResult();

        Gson gson = new Gson();
        String newGame = gson.toJson(game);
        intent.putExtra("GAME",newGame);

        startActivity(intent);
        this.finish();
    }

    public void playStandardGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        //intent.putExtra("GAMEMODE",GameMode.STANDARD);

        director.constructStandardGame(levelBuilder);
        game = levelBuilder.getResult();

        Gson gson = new Gson();
        String newGame = gson.toJson(game);
        intent.putExtra("GAME",newGame);

        startActivity(intent);
        this.finish();
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}
