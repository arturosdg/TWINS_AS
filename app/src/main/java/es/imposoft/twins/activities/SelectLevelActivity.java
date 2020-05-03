package es.imposoft.twins.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import es.imposoft.twins.R;
import es.imposoft.twins.activities.SelectionGameModeActivity;

public class SelectLevelActivity extends AppCompatActivity {
    private final int MAX_LEVELS = 5;
    private int level;
    private Button[] levelButtons;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectlevel);
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, SelectionGameModeActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void goToLevel(View view) {
        //intent.putExtra("GAMEMODE",GameMode.LEVEL1);

    }

    private void fillArray(){
        int levelButton = 0;
        for(int i = 0; i < MAX_LEVELS; i++) {
            levelButton = getResources().getIdentifier("button_level" + i,"id", getPackageName());
            levelButtons[i] = findViewById(levelButton);
        }
    }
}
