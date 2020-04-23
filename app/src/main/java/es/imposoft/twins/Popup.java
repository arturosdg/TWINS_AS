package es.imposoft.twins;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;

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
            ListView scoreList = findViewById(R.id.scoreList);

            Scoreboard scoreboard = (Scoreboard) extras.get("SCORE");

            final ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.simple_list_item_centered, scoreboard.getHighscores());

            scoreList.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();

            getWindow().setLayout((int) (screenWidth*.85), (int) (screenHeight*.67));

            int score = (int) extras.get("LAST");
            TextView currentScore = findViewById(R.id.currentScore);
            TextView currentStars = findViewById(R.id.starsText);
            currentScore.setText("" + score);
            currentStars.setText(""+getStars(score));

            final Button cancelButton = findViewById(R.id.cancelButton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    private String getStars(int score){
        String stars = "";

        int maxStars = 0;

        if(score<10){
            maxStars = 1;
        } else if(score<25){
            maxStars = 2;
        } else if(score<75){
            maxStars = 3;
        } else if(score<150){
            maxStars = 4;
        } else {
            maxStars = 5;
        }

        for(int i=1;i<=maxStars;i++){
            stars += "⭐";
        }
        return stars;
    }
}
