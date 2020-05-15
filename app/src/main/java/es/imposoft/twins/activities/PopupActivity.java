package es.imposoft.twins.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;
import es.imposoft.twins.R;
import es.imposoft.twins.Scoreboard;
import es.imposoft.twins.components.Deck;

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
    Bundle windowInfo;

    Deck deck;
    boolean isLevelMode;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        windowInfo = getIntent().getExtras();

        //Display information for future resizing.
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        //Basic buttons common to all Popups
        Button cancelButton;
        Button acceptButton;

        if (windowInfo.get("THEME") != null) {
            isLevelMode = (boolean) windowInfo.get("LEVELMODE");
            deck = (Deck) windowInfo.get("THEME");
        }

        switch ((WindowType) windowInfo.get("TYPE")){
            case WARNING:
                //We change the screen showed
                setContentView(R.layout.activity_popupwindow);

                //Resize the screen
                changeWindowSize(screenHeight,0.8,screenWidth,0.6);

                //Find the buttons on the scene
                acceptButton = findViewById(R.id.acceptButton);
                cancelButton = findViewById(R.id.cancelButton);

                //If the user accepts, he will be sent to the main screen
                acceptButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent(getBaseContext(), MainActivity.class);
                        if(isLevelMode) {
                            returnIntent = new Intent(getBaseContext(), SelectLevelActivity.class);
                            returnIntent.putExtra("THEME", deck);
                        }
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(returnIntent);
                        finish();
                    }
                });

                //If the user cancels, he will keep playing
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        /*Intent returnIntent = new Intent();
                        returnIntent.putExtra("WINDOW",-1);*/
                        finish();
                    }
                });

                break;
            case SCOREBOARD:
                //We change the screen showed
                setContentView(R.layout.activity_popupscoreboard);

                //Resize the screen
                changeWindowSize(screenHeight,0.85,screenWidth,0.67);

                //We load the scoreboard from the saved data
                scoreList = findViewById(R.id.scoreList);
                gson = new Gson();
                scoreboard = gson.fromJson(getIntent().getStringExtra("SCORE"),Scoreboard.class);

                //Set data for the scoreboard results to show centered
                final ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.simple_list_item_centered, scoreboard.getHighscores());
                scoreList.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();

                //Find all items from the scene
                TextView currentScore = findViewById(R.id.currentScore);
                TextView currentStars = findViewById(R.id.starsText);
                cancelButton = findViewById(R.id.cancelButton);

                //Show the score on the scoreboard and the stars
                int score = scoreboard.getLastScore();
                currentScore.setText("" + score);
                currentStars.setText("" + scoreboard.getStarsFromScore(score));

                //When the screen is canceled, the user will be sent to the main screen
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent(getBaseContext(), MainActivity.class);
                        if(isLevelMode) {
                            returnIntent = new Intent(getBaseContext(), SelectLevelActivity.class);
                            returnIntent.putExtra("THEME", deck);
                        }
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(returnIntent);
                        finish();
                    }
                });
                break;
            case OPTIONS:
                //We change the screen showed
                setContentView(R.layout.activity_popupoptions);

                //Find all items from the scene
                acceptButton = findViewById(R.id.acceptButton);
                Button firstCard = findViewById(R.id.firstCard);
                Button secondCard = findViewById(R.id.secondCard);
                Button soundButtonOn = findViewById(R.id.soundButtonOn);
                Button soundButtonOff = findViewById(R.id.soundButtonOff);

                //Called when the user selects Sound ON
                soundButtonOn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        returnIntent.putExtra("WINDOW",2);
                        returnIntent.putExtra("SOUND",true);
                        finish();
                    }
                });

                //Called when the user selects Sound OFF
                soundButtonOff.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        returnIntent.putExtra("WINDOW",2);
                        returnIntent.putExtra("SOUND",false);
                        finish();
                    }
                });

                //When the user clicks the accept button he will close the options menu
                acceptButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("WINDOW",-1);
                        finish();
                    }
                });

                //If the user selects first card, we change the sprites
                firstCard.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        returnIntent.putExtra("WINDOW",2);
                        returnIntent.putExtra("CARD",1);
                        finish();
                    }
                });

                //If the user selects second card, we change the sprites
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
                //We change the screen showed
                setContentView(R.layout.activity_popupgameover);

                //Resize the screen
                getWindow().setLayout((int) (screenWidth*.85), (int) (screenHeight*.67));

                //Load the scoreboard from saved data
                scoreList = findViewById(R.id.scoreList);
                gson = new Gson();
                scoreboard = gson.fromJson(getIntent().getStringExtra("SCORE"),Scoreboard.class);

                //Set data for the scoreboard results to show centered
                final ArrayAdapter<Integer> arrayAdapter2 = new ArrayAdapter<Integer>(this, R.layout.simple_list_item_centered, scoreboard.getHighscores());
                scoreList.setAdapter(arrayAdapter2);
                arrayAdapter2.notifyDataSetChanged();

                //Find all elements in the scene
                TextView currentSmileys = findViewById(R.id.smileysText);
                cancelButton = findViewById(R.id.cancelButton);

                //Change the scoreboard stars to other emoji
                currentSmileys.setText(""+ scoreboard.getSmileys());

                //If he cancels the window, we go back to the main class
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent(getBaseContext(), MainActivity.class);
                        if(isLevelMode) {
                            returnIntent = new Intent(getBaseContext(), SelectLevelActivity.class);
                            returnIntent.putExtra("THEME", deck);
                        }
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(returnIntent);
                        finish();
                    }
                });
                break;
        }
    }

    public void changeWindowSize(int screenHeight, double widthFactor, int screenWidth, double heightFactor){
        getWindow().setLayout((int) (screenWidth*widthFactor), (int) (screenHeight*heightFactor));
    }
}
