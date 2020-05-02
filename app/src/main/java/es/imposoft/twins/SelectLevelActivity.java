package es.imposoft.twins;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectLevelActivity extends AppCompatActivity {
    private final int MAX_LEVELS = 5;
    private int level;
    private Button[] levelButtons;

    public void goBack(View view) {
    }

    public void goToLevel(View view) {
    }

    private void fillArray(){
        int levelButton = 0;
        for(int i = 0; i < MAX_LEVELS; i++) {
            levelButton = getResources().getIdentifier("button_level" + i,"id", getPackageName());
            levelButtons[i] = findViewById(levelButton);
        }
    }
}
