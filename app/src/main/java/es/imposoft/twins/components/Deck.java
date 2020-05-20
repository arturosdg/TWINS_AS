package es.imposoft.twins.components;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.imposoft.twins.card.*;
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
        if(challengesWon != null) totalChallenges = challengesWon.size() * 2;

        //Asignamos las cartas normales aleatoriamente entre las existentes
        numbers = randomList();

        if(isChallengeOrCasual(game)) {
            for (int i = 0; i < cards / 2; i++) {
                //TODO hacer comprobacion de si es el juego de dosBarajas
                if (game.getId() == 10) {
                    if (i % 2 == 0)
                        imagesNormal.add(context.getResources().getIdentifier(DeckTheme.EMOJI.toString().toLowerCase() + numbers.get(i), "drawable", context.getPackageName()));
                    else
                        imagesNormal.add(context.getResources().getIdentifier(DeckTheme.CARS.toString().toLowerCase() + numbers.get(i), "drawable", context.getPackageName()));
                } else
                    imagesNormal.add(context.getResources().getIdentifier(theme.toString().toLowerCase() + numbers.get(i), "drawable", context.getPackageName()));
            }
        } else {
            for (int i = 0; i < (cards - totalChallenges) / 2; i++)
                imagesNormal.add(context.getResources().getIdentifier(theme.toString().toLowerCase() + numbers.get(i), "drawable", context.getPackageName()));
        }
        //Images es un array con una imagen de cada una de las que hay que asignar

        numbers.clear();

        Collections.shuffle(Arrays.asList(buttons));
        //Creamos todas las cartas contando las de desafios
        for(int i = 0; i < cards; i++){
            if(i<totalChallenges && !isChallengeOrCasual(game)){
                ConcreteCard normalCard = new ConcreteCard(buttons[i], context);
                CardDecorator specialCard;

                if(challengesWon.get(i) == 1) {
                    specialCard = new SpecialCardDecorator1(normalCard);
                    concreteCards.add(specialCard);
                } else if (challengesWon.get(i) == 2) {
                    specialCard = new SpecialCardDecorator2(normalCard);
                    concreteCards.add(specialCard);
                } else if (challengesWon.get(i) == 3) {
                    specialCard = new SpecialCardDecorator3(normalCard);
                    concreteCards.add(specialCard);
                }

            } else {
                concreteCards.add(new ConcreteCard(buttons[i], context));
            }
        }

        //Ya tenemos todas las cartas creadas pero sin la imagen asignada

        imagesNormal.addAll(imagesNormal);

        for (Card cardToAsign: concreteCards) {
            if(!isChallengeOrCasual(game)) {
                if (cardToAsign instanceof SpecialCardDecorator1) {
                    cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("challengecard1", "drawable", context.getPackageName())));
                    cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("background2", "drawable", context.getPackageName())));
                    cardToAsign.setFrontName(context.getResources().getIdentifier("challengecard1", "drawable", context.getPackageName()));
                } else if (cardToAsign instanceof SpecialCardDecorator2) {
                    cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("challengecard2", "drawable", context.getPackageName()) ));
                    cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("background2", "drawable", context.getPackageName())));
                    cardToAsign.setFrontName(context.getResources().getIdentifier("challengecard2", "drawable", context.getPackageName()));
                } else if (cardToAsign instanceof SpecialCardDecorator3) {
                    cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("challengecard3", "drawable", context.getPackageName()) ));
                    cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("background2", "drawable", context.getPackageName())));
                    cardToAsign.setFrontName(context.getResources().getIdentifier("challengecard3", "drawable", context.getPackageName()));
                } else if(cardToAsign instanceof ConcreteCard) {
                    int actualImage = imagesNormal.remove(0);
                    cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), actualImage));
                    cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("background2", "drawable", context.getPackageName())));
                    cardToAsign.setFrontName(actualImage);
                }
            } else if(cardToAsign instanceof ConcreteCard) {
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

    private boolean isChallengeOrCasual(Game game) {
        return (game.getGameMode().equals(GameMode.CASUAL) || game.getGameMode().equals(GameMode.CHALLENGE));
    }
}
