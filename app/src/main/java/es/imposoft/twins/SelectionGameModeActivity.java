package es.imposoft.twins;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import es.imposoft.twins.gametypes.Game;



public class SelectionGameModeActivity extends AppCompatActivity {
    Context context;

    //TODO Crear aqui con el director una partida segun el modo de juego seleccionada y pasarselo a GameActivity
    /*
        Director director = new Director();
        ConcreteBuilderLevel levelBuilder =  new ConcreteBuilderLevel();
        director.constructStandardGame(levelBuilder);
        Game partida = levelBuilder.getResult();
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgamemode);
    }

    public void openLevelsLayout(View view) {
        Intent intent = new Intent(this, SelectLevelActivity.class);
        //intent.putExtra(partida);
        startActivity(intent);
    }

    public void playCasualGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void playStandardGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
