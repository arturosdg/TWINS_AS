package es.imposoft.twins;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private int maxCards;
    Button[] buttons;
    Card[] cards;
    Context context;
    int tapCounter;
    private int restantMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);
    }

    public void play(View view) {
        setContentView(R.layout.activity_gamescene);
        maxCards = 16;
        restantMatches = maxCards / 2;
        buttons = new Button[maxCards];
        cards = new Card[maxCards];
        fillArray();
        createCards();
    }


    @SuppressLint("WrongViewCast")
    public void changeSprite(View view) {
        if(tapCounter == 0) startChronometer();
        if(restantMatches == 0) stopChronometer();
        cards[randomPosition(maxCards)].turnCard();
        tapCounter++;
    }

    private void fillArray(){
        for(int i = 0; i < maxCards; i++) {
            int imageID = getResources().getIdentifier("imgPos" + i,"id", getPackageName());
            //int buttonID = getResources().getIdentifier("imgPos" + i,"id", getPackageName());
            buttons[i] = findViewById(imageID);
        }
    }

    private void createCards() {
        for (int i = 0; i < maxCards; i++)
            cards[i] = new Card(buttons[i], context);

    }

    private void stopChronometer() {
        ((Chronometer) findViewById(R.id.text_timer)).stop();
    }

    private void startChronometer() {
        ((Chronometer) findViewById(R.id.text_timer)).setBase(SystemClock.elapsedRealtime());
        ((Chronometer) findViewById(R.id.text_timer)).start();
    }

    private static int randomPosition(int maxPos) {
        return (int) (Math.random() * (maxPos));
    }
}
