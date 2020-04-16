package es.imposoft.twins;

import android.content.Context;
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
    public Context context;
    public Card exampleCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);

    }

    public void play(View view) {
        setContentView(R.layout.activity_gamescene);
        maxCards = 16;
        buttons = new Button[maxCards];
        fillArray();
        exampleCard = new Card(buttons[0],context);
    }

    //Method change: cuando se descubre la ultima pareja, el chronometro se para
    public void stopChronometer(View view) {
        ((Chronometer) findViewById(R.id.text_timer)).stop();
        buttons[0].setBackground(exampleCard.getBackImage());
    }

    //Method change: con el primer click en la carta es cuando se inicia el chronometro
    public void startChronometer(View view) {
        ((Chronometer) findViewById(R.id.text_timer)).setFormat("%s");
        ((Chronometer) findViewById(R.id.text_timer)).setBase(SystemClock.elapsedRealtime());
        ((Chronometer) findViewById(R.id.text_timer)).start();
    }

    @SuppressLint("WrongViewCast")
    public void changeSprite(View view) {
        System.out.println("Test " + view.getId());
        if(view.getId() == buttons[0].getId()) {
            //int[] aux = CardImageController.matchingCards(maxCards);
            for (int i = 0; i < maxCards; i++)
                //if (aux[ i ] == i || aux[ i ] == i / 2)
                buttons[i].setBackgroundResource(R.drawable.boo);
        }
    }

    private void fillArray(){
        for(int i = 0; i < maxCards; i++) {
            int buttonID = getResources().getIdentifier("imgPos" + i,"id", getPackageName());
            buttons[i] = findViewById(buttonID);
        }
    }
}
