package es.imposoft.twins;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.gametypes.Game;

public class SelectionGameModeActivity extends AppCompatActivity {

    GameMode gameModeSelected;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgamemode);
    }

    public void openLevelsLayout(View view) {
        setContentView(R.layout.activity_gamescene4x4);
        gameModeSelected = GameMode.LEVELS;
        System.out.println("TEST");
    }

    public void playCasualGame(View view) {
        setContentView(R.layout.activity_gamescene4x4);
        gameModeSelected = GameMode.CASUAL;
    }

    public void playStandardGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        //setContentView(R.layout.activity_gamescene4x4);
        //gameModeSelected = GameMode.STANDARD;
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
