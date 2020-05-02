package es.imposoft.twins;

import android.content.Intent;
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

    public void goBack(View view) {
        Intent intent = new Intent(SelectionGameModeActivity.this, Popup.class);
        intent.putExtra("TYPE", Popup.WindowType.WARNING);
        startActivityForResult(intent,0);
    }

}
