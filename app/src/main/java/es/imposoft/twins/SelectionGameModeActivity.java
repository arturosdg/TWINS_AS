package es.imposoft.twins;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SelectionGameModeActivity extends AppCompatActivity {

    enum GameMode {CASUAL, STANDARD, LEVELS, CHALLENGE, TOURNAMENT}
    GameMode gameModeSelected;

    public void openLevelsLayout(View view) {
        setContentView(R.layout.activity_gamescene);
        gameModeSelected = GameMode.LEVELS;
    }

    public void playCasualGame(View view) {
        setContentView(R.layout.activity_gamescene);
        gameModeSelected = GameMode.CASUAL;
    }

    public void playStandardGame(View view) {
        setContentView(R.layout.activity_gamescene);
        gameModeSelected = GameMode.STANDARD;
    }
}
