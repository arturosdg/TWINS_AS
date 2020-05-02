package es.imposoft.twins;

import android.widget.TextView;

public class Score {
    private int score;
    private boolean anteriorAcertada;
    private int acertadosSeguidos;
    /*
        Clase base para aplicar el patron PLANTILLA
        Hara la parte fija del algoritmo.
        Las subclases haran el calculo de la puntuacion en funcion del modo de juego seleccionado
     */
    //Este deberia ser el metodo abstracto a implementar en las subclases
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
        //((TextView) findViewById(R.id.text_score)).setText("Puntos: " + score);
    }
}
