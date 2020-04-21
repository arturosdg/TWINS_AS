package es.imposoft.twins;

import android.content.Intent;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int maxCards;
    Button[] buttons;
    Card[] cards;
    Context context;
    int tapCounter;
    private int restantMatches;
    List<Card> pairs = new ArrayList<>();

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
        assignCardTheme("emoji");
    }

    public void onFinishPressed(View view){
        Intent intent = new Intent(MainActivity.this, Popup.class);
        intent.putExtra("TYPE", Popup.WindowType.WARNING);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                setContentView(R.layout.activity_startgame);
            }
        }
    }

    public void testScoreboard(View view){
        Intent intent = new Intent(MainActivity.this, Popup.class);
        intent.putExtra("TYPE", Popup.WindowType.SCOREBOARD);
        startActivityForResult(intent,1);
    }

    @SuppressLint("WrongViewCast")
    public void changeSprite(View view) {
        if(tapCounter == 0) {   // La primera vez que el jugador toca la pantalla, se giran todas las cartas.
            turnAllCards();
            //  Espera 3 segundos hasta que se vuelvan a girar
            Handler secs3 = new Handler();
            secs3.postDelayed(new Runnable() {
                public void run() {
                    turnAllCards();
                    startChronometer();
                }
            }, 3000);

        } else {
            if (restantMatches == 0) stopChronometer();
            //for (Card card : cards) {
            for (Card card : cards) {
                if (card.getCardButton().getId() == view.getId()) {
                    card.turnCard();
                    pairs.add(card);
                    if(pairs.size() == 2) {
                        if (pairs.get(0).getCardButton().getBackground().equals(pairs.get(1).getCardButton().getBackground())) {
                            restantMatches--;
                            pairs.get(0).setPaired();
                            pairs.get(1).setPaired();
                            pairs.clear();
                        } else {
                            Handler secs15 = new Handler();
                            secs15.postDelayed(new Runnable() {
                                public void run() {
                                    turnVisibleCards();
                                }
                            }, 1500);
                        }
                    }
                }
            }
        }
        tapCounter++;
        System.out.println(tapCounter + " parejas restantes:" + restantMatches);
    }

    private void turnAllCards() {
        for (int i = 0; i < cards.length; i++) {
            cards[i].turnCard();
        }
    }

    private void turnVisibleCards() {
        for (int i = 0; i < cards.length; i++) {
            cards[i].turnVisibleCards();
        }
    }

    private void fillArray(){
        for(int i = 0; i < maxCards; i++) {
            int imageID = getResources().getIdentifier("imgPos" + i,"id", getPackageName());
            //int buttonID = getResources().getIdentifier("imgPos" + i,"id", getPackageName());
            buttons[i] = findViewById(imageID);
        }
    }


    /**
     * Formato de nombre de imagen: "tema + numero"
     * los numeros de las barajas seran (de momento) como minimo del 0 - 7 (incluidos)
     * (ya que tenemos 16 cartas)*/
    private void assignCardTheme(String theme) {
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        for (int i = 0; i < cards.length; i++) {
            numeros.add(i);
        }

        int aleatorio;
        int posicion;
        ArrayList<Card> barajadas = new ArrayList<Card>();

        while (!numeros.isEmpty()) {
            aleatorio = (int) (Math.random()*numeros.size());
            posicion = numeros.get(aleatorio);
            numeros.remove(aleatorio);
            barajadas.add(cards[posicion]);

        }

        ArrayList<Integer> images = new ArrayList<Integer>();

        for (int i = 0; i < 8; i++) {
            images.add(getResources().getIdentifier(theme + i, "drawable", getPackageName()));
        }
        for (int i = 0; i < barajadas.size(); i++) {
            barajadas.get(i).setFrontImage(BitmapFactory.decodeResource(context.getResources(), images.get(i/2)));
        }
    }

    private void createCards() {
        for (int i = 0; i < maxCards; i++) {
            cards[i] = new Card(buttons[i], context);
        }
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
