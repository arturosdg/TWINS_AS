package es.imposoft.twins;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.annotation.RequiresApi;
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

    private static int themeCard;
    Card[] cards;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);
    }

    public void playByGameModes(View view) {
        setContentView(R.layout.activity_selectgamemode);
    }

    /*
    public void implantThemeCard() {
        switch (themeCard) {
            case 1:
                assignCardTheme("emoji");
                break;
            case 2:
                assignCardTheme("coche");
                break;
        }
    }
    */


    public void onOptionsPressed(View view){
        Intent intent = new Intent(MainActivity.this, Popup.class);
        intent.putExtra("TYPE", Popup.WindowType.OPTIONS);
        startActivityForResult(intent,2);
    }

    public void onFinishPressed(View view){
        Intent intent = new Intent(MainActivity.this, Popup.class);
        intent.putExtra("TYPE", Popup.WindowType.WARNING);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Method executed from the popup window

        if (data != null) {
            switch ((Integer) data.getExtras().get("WINDOW")) {
                case 0:
                    //Called from the finish game popup
                    if (resultCode == RESULT_OK) {
                        setContentView(R.layout.activity_startgame);
                    }
                case 2:
                    //Called from the options menu
                    if (resultCode == RESULT_OK) {
                        Bundle returnInfo = data.getExtras();
                        int chosenCard = -1;
                        if (returnInfo.containsKey("CARD")) {
                            chosenCard = (Integer) returnInfo.get("CARD");
                        }
                        changeCardDesign(chosenCard);
                    }
            }
        }
    }

    private void changeCardDesign(int choosenCard) {
        switch (choosenCard) {
            case 1:
                themeCard = 1;
                break;
            case 2:
                themeCard = 2;
                break;
            default:
                System.err.println("Error - doesn't exist this baraja");
        }
    }

    /**
     * Formato de nombre de imagen: "tema + numero"
     * los numeros de las barajas seran (de momento) como minimo del 0 - 7 (incluidos)
     * (ya que tenemos 16 cartas)*/
    private void assignCardTheme(String theme) {
        ArrayList<Integer> numeros = new ArrayList<>();
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

}
