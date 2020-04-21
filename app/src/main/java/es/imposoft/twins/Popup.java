package es.imposoft.twins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Popup extends Activity {
    enum WindowType{
        WARNING,
        SCOREBOARD
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        if(extras.get("TYPE") == WindowType.WARNING){
            setContentView(R.layout.activity_popupwindow);

            getWindow().setLayout((int) (screenWidth*.8), (int) (screenHeight*.6));

            final Button acceptButton = findViewById(R.id.acceptButton);

            acceptButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            });

            final Button cancelButton = findViewById(R.id.cancelButton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });
        } else if(extras.get("TYPE") == WindowType.SCOREBOARD){
            setContentView(R.layout.activity_popupscoreboard);
            getWindow().setLayout((int) (screenWidth*.85), (int) (screenHeight*.9));

            final TextView score = findViewById(R.id.score);
            Scoreboard scoreboard = (Scoreboard) extras.get("SCORE");
            score.setText(scoreboard.getHighscores().toString());

            final Button cancelButton = findViewById(R.id.cancelButton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
