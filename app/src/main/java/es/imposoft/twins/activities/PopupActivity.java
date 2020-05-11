package es.imposoft.twins.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import es.imposoft.twins.R;
import es.imposoft.twins.Scoreboard;
import es.imposoft.twins.SucceededLevel;

public class PopupActivity extends Activity {
    public enum WindowType{
        WARNING,
        SCOREBOARD,
        OPTIONS,
        GAMEOVER
    }

    ListView scoreList;
    Gson gson;
    Scoreboard scoreboard;
    SucceededLevel succeededLevels;

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
                        /*Intent returnIntent = new Intent();
                        returnIntent.putExtra("WINDOW",0);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();*/
                        Intent returnIntent = new Intent(getBaseContext(), MainActivity.class);
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(returnIntent);
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

                scoreList = findViewById(R.id.scoreList);
                gson = new Gson();
                scoreboard = gson.fromJson(getIntent().getStringExtra("SCORE"),Scoreboard.class);


                final ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.simple_list_item_centered, scoreboard.getHighscores());
                scoreList.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

                int score = scoreboard.getLastScore();
                TextView currentScore = findViewById(R.id.currentScore);
                TextView currentStars = findViewById(R.id.starsText);
                currentScore.setText("" + score);
                currentStars.setText(""+scoreboard.getStarsFromScore(score));

                cancelButton = findViewById(R.id.cancelButton);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        /*Intent returnIntent = new Intent();
                        returnIntent.putExtra("WINDOW",0);
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();*/
                        Intent returnIntent = new Intent(getBaseContext(), MainActivity.class);
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(returnIntent);
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
            case GAMEOVER:
                setContentView(R.layout.activity_popupgameover);
                getWindow().setLayout((int) (screenWidth*.85), (int) (screenHeight*.67));

                scoreList = findViewById(R.id.scoreList);
                gson = new Gson();
                scoreboard = gson.fromJson(getIntent().getStringExtra("SCORE"),Scoreboard.class);

                final ArrayAdapter<Integer> arrayAdapter2 = new ArrayAdapter<Integer>(this, R.layout.simple_list_item_centered, scoreboard.getHighscores());
                scoreList.setAdapter(arrayAdapter2);
                arrayAdapter2.notifyDataSetChanged();

                TextView currentSmileys = findViewById(R.id.smileysText);
                currentSmileys.setText(""+ scoreboard.getSmileys());

                cancelButton = findViewById(R.id.cancelButton);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent(getBaseContext(), MainActivity.class);
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(returnIntent);
                        finish();
                    }
                });
                break;
        }
    }
}
