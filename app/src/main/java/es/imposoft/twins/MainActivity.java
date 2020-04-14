package es.imposoft.twins;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private int maxCards;
    Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);
    }

    public void play(View view) {
        setContentView(R.layout.activity_gamescene);
        maxCards = 16;
        buttons = new Button[maxCards];
        fillArray();
    }

    //Method change: cuando se descubre la ultima pareja, el chronometro se para
    public void stopChronometer(View view) {
        ((Chronometer) findViewById(R.id.text_timer)).stop();
    }

    //Method change: con el primer click en la carta es cuando se inicia el chronometro
    public void startChronometer(View view) {
        ((Chronometer) findViewById(R.id.text_timer)).setFormat("%s");
        ((Chronometer) findViewById(R.id.text_timer)).setBase(SystemClock.elapsedRealtime());
        ((Chronometer) findViewById(R.id.text_timer)).start();
    }

    @SuppressLint("WrongViewCast")
    public void changeSprite(View view) {
        //int[] aux = CardImageController.matchingCards(maxCards);
        for(int i = 0; i < maxCards; i++)
            //if (aux[ i ] == i || aux[ i ] == i / 2)
                buttons[ i ].setBackgroundResource(R.drawable.boo);
    }

    private void fillArray(){
        for(int i = 0; i < maxCards; i++) {
            int buttonID = getResources().getIdentifier("imgPos" + i,"id", getPackageName());
            buttons[i] = findViewById(buttonID);
        }
    }
}
