package es.imposoft.twins.components;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.Button;

import java.util.ArrayList;

import es.imposoft.twins.card.ConcreteCard;
import es.imposoft.twins.gametypes.Game;

public class Deck {

    ArrayList<ConcreteCard> shuffled;
    ArrayList<Integer> numbers, images;
    int random, position, cards;
    //ESTE NUMERO VARIA EN FUNCION DEL NUMERO DE CARTAS EXISTENTES PARA CADA TIPO DE BARAJA
    int MAX_CARD_DESIGNS = 12;

    //Crearemos las barajas de cartas, y llamaremos a un metodo de esta clase para crear el tablero
    public Deck() {
        shuffled = new ArrayList<>();
        images = new ArrayList<>();
        numbers = new ArrayList<>();
    }

    public void assignCardTheme(DeckTheme theme, ArrayList<ConcreteCard> concreteCards, Game game, Button[] buttons, Context context) {
        cards = game.getCardAmount();
        numbers = randomList();
        for (int i = 0; i < cards / 2; i++) {
            images.add(context.getResources().getIdentifier(theme.toString().toLowerCase() + numbers.get(i), "drawable", context.getPackageName()));
        }
        numbers.clear();
        for (int i = 0; i < cards; i++) {
            numbers.add(i);
            concreteCards.add(new ConcreteCard(buttons[ i ], context));
        }

        while (!numbers.isEmpty()) {
            random = (int) (Math.random() * numbers.size());
            position = numbers.remove(random);
            shuffled.add(concreteCards.get(position));
        }

        for (int i = 0; i < shuffled.size(); i++) {
            shuffled.get(i).setFrontImage(BitmapFactory.decodeResource(context.getResources(), images.get(i / 2)));
            shuffled.get(i).setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("background2", "drawable", context.getPackageName())));
        }
    }

    public void assign2CardThemes(DeckTheme theme, DeckTheme theme2, ArrayList<ConcreteCard> concreteCards, Game game, Button[] buttons, Context context) {
        cards = game.getCardAmount();
        numbers = randomList();
        for (int i = 0; i < cards / 2; i++) {
            if(i % 2 == 0)
                images.add(context.getResources().getIdentifier(theme.toString().toLowerCase() + numbers.get(i), "drawable", context.getPackageName()));
            else
                images.add(context.getResources().getIdentifier(theme2.toString().toLowerCase() + numbers.get(i), "drawable", context.getPackageName()));
        }
        numbers.clear();
        for (int i = 0; i < cards; i++) {
            numbers.add(i);
            concreteCards.add(new ConcreteCard(buttons[ i ], context));
        }

        while (!numbers.isEmpty()) {
            random = (int) (Math.random() * numbers.size());
            position = numbers.remove(random);
            shuffled.add(concreteCards.get(position));
        }

        for (int i = 0; i < shuffled.size(); i++) {
            shuffled.get(i).setFrontImage(BitmapFactory.decodeResource(context.getResources(), images.get(i / 2)));
            shuffled.get(i).setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("background2", "drawable", context.getPackageName())));
        }
    }

    private ArrayList<Integer> randomList() {
        ArrayList<Integer> aux = new ArrayList<>();
        if (cards == 24) {
            for (int i = 0; i < cards / 2; i++)
                aux.add(i);
        } else {
            while (aux.size() <= cards / 2) {
                random = (int) (Math.random() * MAX_CARD_DESIGNS);
                if (!aux.contains(random)) {
                    aux.add(random);
                }
            }
        }
        return aux;
    }

    //Por cada carta especial que ganemos, este valor se incrementara.
    // Las cartas especiales son iguales para ambos tipos de DeckTheme
    private void incrementMaxCardsDesign() {
        MAX_CARD_DESIGNS++;
    }
}
