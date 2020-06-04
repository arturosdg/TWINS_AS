package es.imposoft.twins.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.*;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.Gson;

import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

import es.imposoft.twins.singleton.MusicService;
import es.imposoft.twins.R;
import es.imposoft.twins.database.Scoreboard;
import es.imposoft.twins.components.DeckTheme;

public class PopupActivity extends Activity {

    public enum WindowType{
        WARNING,
        SCOREBOARD,
        OPTIONS,
        GAMEOVER,
        CHALLENGE
    }

    ListView scoreList;
    Gson gson;
    Scoreboard scoreboard;
    Bundle windowInfo;

    DeckTheme deckTheme;
    int points;
    Drawable card;
    MusicService musicEngine;

    GoogleSignInAccount signedInAccount;

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

        deckTheme = (DeckTheme) windowInfo.get("THEME");
        musicEngine = MusicService.getInstance(getApplicationContext());

        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);

        TextView user;


        switch ((WindowType) windowInfo.get("TYPE")){
            case WARNING:
                //We change the screen showed
                setContentView(R.layout.activity_popupwindow);
                musicEngine.stopExtraSound();

                //Resize the screen
                changeWindowSize(screenHeight,0.8,screenWidth,0.6);

                //Find the buttons on the scene
                acceptButton = findViewById(R.id.acceptButton);
                cancelButton = findViewById(R.id.cancelButton);

                //If the user accepts, he will be sent to the main screen
                acceptButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        MusicService musicEngine = MusicService.getInstance(getApplicationContext());
                        musicEngine.stopMusic();
                        musicEngine.startGameMusic(R.raw.menusong);
                        Intent returnIntent = new Intent(getBaseContext(), SelectionGameModeActivity.class);
                        if((boolean) windowInfo.get("LEVELMODE")) {
                            returnIntent = new Intent(getBaseContext(), SelectLevelActivity.class);
                        }
                        else if ((boolean) windowInfo.get("CHALLENGE")) {
                            returnIntent = new Intent(getBaseContext(), SelectChallengeActivity.class);
                        }
                        returnIntent.putExtra("THEME", deckTheme);
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(returnIntent);
                        finish();
                    }
                });

                //If the user cancels, he will keep playing
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        finish();
                    }
                });

                break;
            case SCOREBOARD:
                //We change the screen showed
                setContentView(R.layout.activity_popupscoreboard);
                musicEngine.stopExtraSound();

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

                user = findViewById(R.id.userName);
                if(signedInAccount != null) user.setText(signedInAccount.getDisplayName());

                //When the screen is canceled, the user will be sent to the main screen
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        musicEngine.stopMusic();
                        musicEngine.startGameMusic(R.raw.menusong);

                        Intent returnIntent = new Intent(getBaseContext(), SelectionGameModeActivity.class);
                        if((boolean) windowInfo.get("LEVELMODE")) {
                            returnIntent = new Intent(getBaseContext(), SelectLevelActivity.class);
                        }
                        returnIntent.putExtra("THEME", deckTheme);
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
                        returnIntent.putExtra("SOUND",1);
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        finish();
                    }
                });

                //Called when the user selects Sound OFF
                soundButtonOff.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        returnIntent.putExtra("WINDOW",2);
                        returnIntent.putExtra("SOUND",2);
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        finish();
                    }
                });

                //When the user clicks the accept button he will close the options menu
                acceptButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
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
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        finish();
                    }
                });
                break;
            case GAMEOVER:
                //We change the screen showed
                setContentView(R.layout.activity_popupgameover);
                musicEngine.stopExtraSound();

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
                if ((boolean) windowInfo.get("CHALLENGE")) scoreList.setVisibility(View.GONE);

                //Find all elements in the scene
                TextView currentSmileys = findViewById(R.id.smileysText);
                cancelButton = findViewById(R.id.cancelButton);

                //Change the scoreboard stars to other emoji
                currentSmileys.setText(""+ scoreboard.getSmileys());

                user = findViewById(R.id.userName);
                if(signedInAccount != null) user.setText(signedInAccount.getDisplayName());

                //If he cancels the window, we go back to the main class
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        MusicService musicEngine = MusicService.getInstance(getApplicationContext());
                        musicEngine.stopMusic();
                        musicEngine.startGameMusic(R.raw.menusong);
                        
                        Intent returnIntent = new Intent(getBaseContext(), SelectionGameModeActivity.class);
                        if((boolean) windowInfo.get("LEVELMODE")) {
                            returnIntent = new Intent(getBaseContext(), SelectLevelActivity.class);
                        }
                        else if ((boolean) windowInfo.get("CHALLENGE")) {
                            returnIntent = new Intent(getBaseContext(), SelectChallengeActivity.class);
                        }
                        returnIntent.putExtra("THEME", deckTheme);
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(returnIntent);
                        finish();
                    }
                });
                break;
            case CHALLENGE:
                setContentView(R.layout.activity_popupscorechallenge);
                getWindow().setLayout((int) (screenWidth*.85), (int) (screenHeight*.67));

                musicEngine.stopExtraSound();
                getExtraPoints();

                TextView extraPoint = findViewById(R.id.extraPoints);
                extraPoint.setText(points + " " + extraPoint.getText());

                user = findViewById(R.id.userName);
                if(signedInAccount != null) user.setText(signedInAccount.getDisplayName());

                ImageView cardWon = findViewById(R.id.cardWon);
                cardWon.setImageDrawable(card);

                cancelButton = findViewById(R.id.cancelButton);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        MusicService musicEngine = MusicService.getInstance(getApplicationContext());
                        musicEngine.stopMusic();
                        musicEngine.startGameMusic(R.raw.menusong);
                        Intent returnIntent = new Intent(getBaseContext(), SelectChallengeActivity.class);
                        returnIntent.putExtra("THEME", deckTheme);
                        returnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(returnIntent);
                        finish();
                    }
                });
        }
    }

    public void changeWindowSize(int screenHeight, double widthFactor, int screenWidth, double heightFactor){
        getWindow().setLayout((int) (screenWidth*widthFactor), (int) (screenHeight*heightFactor));
    }

    private void getExtraPoints() {
        points = 0;
        card = null;
        switch((int) windowInfo.get("CHALLENGEPLAYED")) {
            case 1:
                points = 3;
                card = getDrawable(R.drawable.challengecard1);
                break;
            case 2:
                points = 5;
                card = getDrawable(R.drawable.challengecard2);
                break;
            case 3:
                points = 7;
                card = getDrawable(R.drawable.challengecard3);
                break;
        }

    }

    public void languagePressed(View view){
        switch (view.getId()){
            case R.id.langButtonEn:
                setLocale("en");
                break;
            case R.id.langButtonEsp:
                setLocale("es");
                break;
            case R.id.langButtonFr:
                setLocale("fr");
                break;
            case R.id.langButtonPt:
                setLocale("pt");
                break;
            case R.id.langButtonVal:
                setLocale("ca");
                break;
        }
    }

    public void setLocale(String lang) {
        System.out.println("test" + lang);
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        finish();
        //refresh();

    }

    private void refresh() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}
