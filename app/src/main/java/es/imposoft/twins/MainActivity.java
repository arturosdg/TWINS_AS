package es.imposoft.twins;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int maxCards;
    Button[] buttons;
    Card[] cards;
    Context context;
    int tapCounter;
    Scoreboard scoreboard;

    int acertadosSeguidos = 0;
    boolean anteriorAcertada = false;

    int score = 0;
    private int restantMatches;
    private boolean isClickable;
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
        isClickable = false;

        score = 0;
        scoreboard = new Scoreboard();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        scoreboard.loadHighscores(sp);

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
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        scoreboard.addScore(score);
        scoreboard.saveHighscores(sp);

        Intent intent = new Intent(MainActivity.this, Popup.class);
        intent.putExtra("SCORE",scoreboard);
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
                        if (pairs.get(0).equals(pairs.get(1))) {
                            restantMatches--;
                            pairs.get(0).setPaired();
                            pairs.get(1).setPaired();
                            actualizarControladorDePuntos(10);
                            pairs.clear();
                        } else {
                            actualizarControladorDePuntos(-3);
                            Handler secs1 = new Handler();
                            secs1.postDelayed(new Runnable() {
                                public void run() {
                                    turnVisibleCards();
                                }
                            }, 1500);

                            pairs.clear();
                        }
                    }
                }
            }
        }
        tapCounter++;
        System.out.println(tapCounter + " parejas restantes:" + restantMatches);
    }

    private void actualizarControladorDePuntos(int aSumar) {
        score += aSumar;
        if (aSumar < 0) {
            anteriorAcertada = false;
            acertadosSeguidos = 0;
        } else {
            if (anteriorAcertada) {
                    score += Math.pow(2, acertadosSeguidos);
            }
            acertadosSeguidos++;
            anteriorAcertada = true;
        }
        ((TextView) findViewById(R.id.text_score)).setText("Score: " + score);
    }

    private void turnAllCards() {
        for (int i = 0; i < cards.length; i++) {
            cards[i].turnCard();
        }
        setClickable(buttons);
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

    private void setClickable(Button[] buttons) {
        for (Button b: buttons) { b.setClickable(isClickable); }
        isClickable = !isClickable;
    }


}
