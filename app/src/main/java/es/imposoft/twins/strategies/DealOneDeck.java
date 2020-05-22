package es.imposoft.twins.strategies;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.imposoft.twins.card.Card;
import es.imposoft.twins.card.CardDecorator;
import es.imposoft.twins.card.ConcreteCard;
import es.imposoft.twins.card.SpecialCardDecorator1;
import es.imposoft.twins.card.SpecialCardDecorator2;
import es.imposoft.twins.card.SpecialCardDecorator3;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.gametypes.Game;

public class DealOneDeck implements Dealer {
    ArrayList<Card> shuffled;
    ArrayList<Integer> numbers, imagesNormal, imagesChallenges;
    List<Integer> challengesWon;
    ArrayList<Integer> newCards;
    int random, position, cards;
    //ESTE NUMERO VARIA EN FUNCION DEL NUMERO DE CARTAS EXISTENTES PARA CADA TIPO DE BARAJA
    int MAX_CARD_DESIGNS = 12;

    @Override
    public void assignCardTheme(DeckTheme theme, ArrayList<Card> concreteCards, Game game, Button[] buttons, Context context) {
        shuffled = new ArrayList<>();
        imagesNormal = new ArrayList<>();
        imagesChallenges = new ArrayList<>();
        numbers = new ArrayList<>();
        newCards = new ArrayList<>();

        cards = game.getCardAmount();

        //Asignamos las cartas normales aleatoriamente entre las existentes
        numbers = randomList();

        for (int i = 0; i < cards / 2; i++) {
            imagesNormal.add(context.getResources().getIdentifier(theme.toString().toLowerCase() + numbers.get(i), "drawable", context.getPackageName()));
        }

        //Images es un array con una imagen de cada una de las que hay que asignar

        numbers.clear();

        Collections.shuffle(Arrays.asList(buttons));
        //Creamos todas las cartas contando las de desafios
        for(int i = 0; i < cards; i++){
            concreteCards.add(new ConcreteCard(buttons[i], context));
        }

        //Ya tenemos todas las cartas creadas pero sin la imagen asignada
        imagesNormal.addAll(imagesNormal);

        for (Card cardToAsign: concreteCards) {
                int actualImage = imagesNormal.remove(0);
                cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), actualImage));
                cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("card", "drawable", context.getPackageName())));
                cardToAsign.setFrontName(actualImage);
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

}
