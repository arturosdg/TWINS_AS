package es.imposoft.twins.logic.strategies;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import es.imposoft.twins.logic.card.Card;
import es.imposoft.twins.logic.card.ConcreteCard;
import es.imposoft.twins.logic.components.DeckTheme;
import es.imposoft.twins.logic.gametypes.Game;

public class DealTwoDecks implements Dealer {
    ArrayList<Integer> randomNumbers, imagesNormal;
    int totalCards;

    @Override
    public void assignCardTheme(DeckTheme theme, ArrayList<Card> concreteCards, Game game, Button[] buttons, Context context, String email) {
        //Initialize arrays
        imagesNormal = new ArrayList<>();
        randomNumbers = new ArrayList<>();
        totalCards = game.getCardAmount();

        //Load the right amount of cards randomly on imagesNormal
        randomNumbers = DealerServant.randomList(totalCards);
        for (int i = 0; i < totalCards / 2; i++) {
            if (i % 2 == 0) {
                imagesNormal.add(context.getResources().getIdentifier(DeckTheme.EMOJI.toString().toLowerCase()
                        + randomNumbers.get(i), "drawable", context.getPackageName()));
            } else {
                imagesNormal.add(context.getResources().getIdentifier(DeckTheme.CARS.toString().toLowerCase()
                        + randomNumbers.get(i), "drawable", context.getPackageName()));
            }
        }

        //We shuffle the card buttons to assign a Card randomly
        Collections.shuffle(Arrays.asList(buttons));
        for(int i = 0; i < totalCards; i++)
            concreteCards.add(new ConcreteCard(buttons[i], context));

        //Duplicate the cards so we have 2 of each kind and assign the right card image
        imagesNormal.addAll(imagesNormal);
        for (Card cardToAsign: concreteCards) {
            if(cardToAsign instanceof ConcreteCard) {
                int actualImage = imagesNormal.remove(0);
                cardToAsign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), actualImage));
                cardToAsign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources()
                        .getIdentifier("card", "drawable", context.getPackageName())));
                cardToAsign.setFrontName(actualImage);
            }
        }
    }
}
