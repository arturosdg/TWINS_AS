package es.imposoft.twins.components;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.Button;

import java.util.ArrayList;

import es.imposoft.twins.Card.ConcreteCard;
import es.imposoft.twins.gametypes.Game;

public class Deck {

    ArrayList<ConcreteCard> shuffled;
    ArrayList<Integer> numbers, images;
    int random, position;

    //Crearemos las barajas de cartas, y llamaremos a un metodo de esta clase para crear el tablero
    public Deck() {
        shuffled = new ArrayList<>();
        images = new ArrayList<>();
        numbers = new ArrayList<>();
    }

    public void assignCardTheme(DeckTheme theme, ArrayList<ConcreteCard> concreteCards, Game game, Button[] buttons, Context context) {
        for (int i = 0; i < game.getCardAmount(); i++) numbers.add(i);
        for (int i = 0; i < game.getCardAmount(); i++)
            concreteCards.add(new ConcreteCard(buttons[ i ], context));

        for (int i = 0; i < game.getCardAmount() / 2; i++)
            images.add(context.getResources().getIdentifier(theme.toString().toLowerCase() + i, "drawable", context.getPackageName()));

        while (!numbers.isEmpty()) {
            random = (int) (Math.random() * numbers.size());
            position = numbers.get(random);
            numbers.remove(random);
            shuffled.add(concreteCards.get(position));
        }

        for (int i = 0; i < shuffled.size(); i++) {
            shuffled.get(i).setFrontImage(BitmapFactory.decodeResource(context.getResources(), images.get(i / 2)));
            shuffled.get(i).setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("background2", "drawable", context.getPackageName())));
        }
    }
}
