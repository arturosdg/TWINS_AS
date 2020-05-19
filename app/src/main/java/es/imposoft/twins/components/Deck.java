package es.imposoft.twins.components;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import es.imposoft.twins.SucceededChallenges;
import es.imposoft.twins.card.Card;
import es.imposoft.twins.card.ConcreteCard;
import es.imposoft.twins.card.ConcreteCardDecorator;
import es.imposoft.twins.card.SpecialCardDecorator;
import es.imposoft.twins.gametypes.Game;

public class Deck {

    ArrayList<Card> shuffled;
    ArrayList<Integer> numbers, imagesNormal, imagesChallenges;
    List<Integer> challengesWon;
    ArrayList<Integer> newCards;
    int random, position, cards;
    //ESTE NUMERO VARIA EN FUNCION DEL NUMERO DE CARTAS EXISTENTES PARA CADA TIPO DE BARAJA
    int MAX_CARD_DESIGNS = 12;

    //Crearemos las barajas de cartas, y llamaremos a un metodo de esta clase para crear el tablero
    public Deck() {
        shuffled = new ArrayList<>();
        imagesNormal = new ArrayList<>();
        imagesChallenges = new ArrayList<>();
        numbers = new ArrayList<>();
        newCards = new ArrayList<>();
    }

    public void assignCardTheme(DeckTheme theme, ArrayList<Card> concreteCards, Game game, Button[] buttons, Context context) {
        //Cargamos el total de cartas
        cards = game.getCardAmount();

        int totalChallenges = 0;

        //Si hemos ganado algun challenge, solo se cargaran las cartas que toca
        if(challengesWon != null){
            totalChallenges = challengesWon.size()*2;

            //Asignamos las cartas de desafios
            for(int i = 0; i < challengesWon.size(); i++){
                imagesChallenges.add(context.getResources().getIdentifier("challengecard" + i, "drawable", context.getPackageName()));
            }
        }

        //Asignamos las cartas normales aleatoriamente entre las existentes
        numbers = randomList();
        for (int i = 0; i < (cards - totalChallenges ) / 2; i++) {
            imagesNormal.add(context.getResources().getIdentifier(theme.toString().toLowerCase() + numbers.get(i), "drawable", context.getPackageName()));
        }

        //Images es un array con una imagen de cada una de las que hay que asignar

        numbers.clear();

        Collections.shuffle(Arrays.asList(buttons));
        //Creamos todas las cartas contando las de desafios
        for(int i = 0; i < cards; i++){
            if(i<totalChallenges){
                //newCards.add(i);
                ConcreteCard normalCard = new ConcreteCard(buttons[i], context);
                SpecialCardDecorator specialCard = new SpecialCardDecorator(normalCard);
                concreteCards.add(specialCard);
            } else {
                //numbers.add(i);
                concreteCards.add(new ConcreteCard(buttons[i], context));
            }
        }

        //Ya tenemos todas las cartas creadas pero sin la imagen asignada

        imagesNormal.addAll(imagesNormal);
        imagesChallenges.addAll(imagesChallenges);

        for (Card cardToAsign: concreteCards) {
            if (cardToAsign instanceof SpecialCardDecorator) {
                System.out.println("test aqui" + imagesChallenges.get(0));
                int actualImage = imagesChallenges.remove(0);
                cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), actualImage));
                cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("background2", "drawable", context.getPackageName())));
                cardToAsign.setFrontName(actualImage);
            } else if(cardToAsign instanceof ConcreteCardDecorator) {
                int actualImage = imagesNormal.remove(0);
                cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), actualImage));
                cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("background2", "drawable", context.getPackageName())));
                cardToAsign.setFrontName(actualImage);
            }
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

    public void addChallengesWon(List<Integer> succedeedChallenges) {
        this.challengesWon = succedeedChallenges;
    }
}
