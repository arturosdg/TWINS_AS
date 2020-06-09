package es.imposoft.twins.strategies;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import es.imposoft.twins.database.SucceededChallenge;
import es.imposoft.twins.card.Card;
import es.imposoft.twins.card.CardDecorator;
import es.imposoft.twins.card.ConcreteCard;
import es.imposoft.twins.card.SpecialCardDecorator1;
import es.imposoft.twins.card.SpecialCardDecorator2;
import es.imposoft.twins.card.SpecialCardDecorator3;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.gametypes.Game;

public class DealSpecialDecks implements Dealer {
    private ArrayList<Integer> randomNumbers, imagesNormal;
    private List<String> challengesWon;
    private int totalCards;
    private SucceededChallenge challengesProgress;

    @Override
    public void assignCardTheme(DeckTheme theme, ArrayList<Card> concreteCards, Game game, Button[] buttons, Context context, String email) {
        //Initialize arrays
        imagesNormal = new ArrayList<>();
        randomNumbers = new ArrayList<>();

        //Load won challenges
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        challengesProgress = new SucceededChallenge(email);
        challengesProgress.loadChallenges(sharedPreferences);
        challengesWon = challengesProgress.getSuccedeedChallenges();

        totalCards = game.getCardAmount();

        int totalChallenges = 0;
        if(challengesWon != null) totalChallenges = challengesWon.size() * 2;

        //Load the right amount of cards randomly on imagesNormal
        randomNumbers = DealerServant.randomList(totalCards);
        for (int i = 0; i < (totalCards - totalChallenges) / 2; i++){
            imagesNormal.add(context.getResources().getIdentifier(theme.toString().toLowerCase()
                    + randomNumbers.get(i), "drawable", context.getPackageName()));
        }

        //Duplicate all the challengesWon to have the right amount
        challengesWon.addAll(challengesWon);

        //We shuffle the card buttons to assign a Card randomly
        Collections.shuffle(Arrays.asList(buttons));
        for(int i = 0; i < totalCards; i++){
            if(i<totalChallenges){
                ConcreteCard normalCard = new ConcreteCard(buttons[i], context);
                CardDecorator specialCard;
                if(challengesWon.get(i).equals("1")) {
                    specialCard = new SpecialCardDecorator1(normalCard);
                    concreteCards.add(specialCard);
                } else if (challengesWon.get(i).equals("2")) {
                    specialCard = new SpecialCardDecorator2(normalCard);
                    concreteCards.add(specialCard);
                } else if (challengesWon.get(i).equals("3")) {
                    specialCard = new SpecialCardDecorator3(normalCard);
                    concreteCards.add(specialCard);
                }
            } else {
                concreteCards.add(new ConcreteCard(buttons[i], context));
            }
        }

        //Duplicate the cards so we have 2 of each kind and assign the right card image
        imagesNormal.addAll(imagesNormal);
        for (Card cardToAsign: concreteCards) {
            if (cardToAsign instanceof SpecialCardDecorator1) {
                cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("challengecard1", "drawable", context.getPackageName())));
                cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("card", "drawable", context.getPackageName())));
                cardToAsign.setFrontName(context.getResources().getIdentifier("challengecard1", "drawable", context.getPackageName()));
            } else if (cardToAsign instanceof SpecialCardDecorator2) {
                cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("challengecard2", "drawable", context.getPackageName()) ));
                cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("card", "drawable", context.getPackageName())));
                cardToAsign.setFrontName(context.getResources().getIdentifier("challengecard2", "drawable", context.getPackageName()));
            } else if (cardToAsign instanceof SpecialCardDecorator3) {
                cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("challengecard3", "drawable", context.getPackageName()) ));
                cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("card", "drawable", context.getPackageName())));
                cardToAsign.setFrontName(context.getResources().getIdentifier("challengecard3", "drawable", context.getPackageName()));
            } else if(cardToAsign instanceof ConcreteCard) {
                int actualImage = imagesNormal.remove(0);
                cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), actualImage));
                cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("card", "drawable", context.getPackageName())));
                cardToAsign.setFrontName(actualImage);
            }
        }
    }
}
