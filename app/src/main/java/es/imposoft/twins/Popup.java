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
        SCOREBOARD,
        OPTIONS
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle windowInfo = getIntent().getExtras();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        Button cancelButton;
        Button acceptButton;
        switch ((WindowType) windowInfo.get("TYPE")){
            case WARNING:
                setContentView(R.layout.activity_popupwindow);
                getWindow().setLayout((int) (screenWidth*.8), (int) (screenHeight*.6));

                acceptButton = findViewById(R.id.acceptButton);

                acceptButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("WINDOW",0);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                });

                cancelButton = findViewById(R.id.cancelButton);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("WINDOW",-1);
                        finish();
                    }
                });

                break;
            case SCOREBOARD:
                setContentView(R.layout.activity_popupscoreboard);
                getWindow().setLayout((int) (screenWidth*.85), (int) (screenHeight*.67));

                ListView scoreList = findViewById(R.id.scoreList);
                Scoreboard scoreboard = (Scoreboard) windowInfo.get("SCORE");

                final ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.simple_list_item_centered, scoreboard.getHighscores());
                scoreList.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

                int score = (int) windowInfo.get("LAST");
                TextView currentScore = findViewById(R.id.currentScore);
                TextView currentStars = findViewById(R.id.starsText);
                currentScore.setText("" + score);
                currentStars.setText(""+scoreboard.getStarsFromScore(score));

                cancelButton = findViewById(R.id.cancelButton);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("WINDOW",-1);
                        finish();
                    }
                });
                break;
            case OPTIONS:
                setContentView(R.layout.activity_popupoptions);
                acceptButton = findViewById(R.id.acceptButton);

                Button firstCard = findViewById(R.id.firstCard);
                Button secondCard = findViewById(R.id.secondCard);

                acceptButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("WINDOW",-1);
                        finish();
                    }
                });

                firstCard.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        returnIntent.putExtra("WINDOW",2);
                        returnIntent.putExtra("CARD",1);
                        finish();
                    }
                });

                secondCard.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("CARD",2);
                        returnIntent.putExtra("WINDOW",2);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }
                });
                break;
        }
    }
}
