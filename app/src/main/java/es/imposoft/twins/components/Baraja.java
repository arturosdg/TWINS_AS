package es.imposoft.twins.components;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.twins.Card;
import es.imposoft.twins.gametypes.Game;

public class Baraja {
    //Context context;
    ArrayList<Card> shuffled;
    ArrayList<Integer> numbers, images;

    //Crearemos las barajas de cartas, y llamaremos a un metodo de esta clase para crear el tablero
    public Baraja() {
        shuffled = new ArrayList<>();
    }

    public void assignCardTheme(Deck theme, ArrayList<Card> cards, Game game, Button[] buttons, Context context) {
        numbers = new ArrayList<>();
        for (int i = 0; i < game.getCardAmount(); i++) numbers.add(i);

        for (int i = 0; i < game.getCardAmount(); i++) {
            cards.add(new Card(buttons[ i ], context));
        }

        int random, position;

        images = new ArrayList<>();
        for (int i = 0; i < game.getCardAmount() / 2; i++)
            images.add(context.getResources().getIdentifier(theme.toString().toLowerCase() + i, "drawable", context.getPackageName()));


        while (!numbers.isEmpty()) {
            random = (int) (Math.random() * numbers.size());
            position = numbers.get(random);
            numbers.remove(random);
            shuffled.add(cards.get(position));
        }


        for (int i = 0; i < shuffled.size(); i++) {
            shuffled.get(i).setFrontImage(BitmapFactory.decodeResource(context.getResources(), images.get(i / 2)));
            shuffled.get(i).setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("background2", "drawable", context.getPackageName())));

        }
        cards = shuffled;
    }
}
