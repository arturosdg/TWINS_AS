package es.imposoft.twins.strategies;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import es.imposoft.twins.card.Card;
import es.imposoft.twins.card.ConcreteCard;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.gametypes.Game;

public class DealOneDeck implements Dealer {
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
            imagesNormal.add(context.getResources().getIdentifier(theme.toString().toLowerCase()
                    + randomNumbers.get(i), "drawable", context.getPackageName()));
        }

        //We shuffle the card buttons to assign a Card randomly
        Collections.shuffle(Arrays.asList(buttons));
        for(int i = 0; i < totalCards; i++){
            concreteCards.add(new ConcreteCard(buttons[i], context));
        }

        //Duplicate the cards so we have 2 of each kind and assign the right card image
        imagesNormal.addAll(imagesNormal);
        for (Card cardToAssign: concreteCards) {
                int actualImage = imagesNormal.remove(0);
                cardToAssign.setFrontImage(BitmapFactory.decodeResource(context.getResources(), actualImage));
                cardToAssign.setBackImage(BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("card", "drawable", context.getPackageName())));
                cardToAssign.setFrontName(actualImage);
        }
    }
}
