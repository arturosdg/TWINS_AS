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
import es.imposoft.twins.strategies.DealOneDeck;
import es.imposoft.twins.strategies.DealSpecialDecks;
import es.imposoft.twins.strategies.DealTwoDecks;
import es.imposoft.twins.strategies.Dealer;

public class Deck {

    List<Integer> challengesWon;
    //ArrayList<Card> shuffled;
    //ArrayList<Integer> numbers, imagesNormal, imagesChallenges;
    //ArrayList<Integer> newCards;
    //int random, position, cards;

    public Deck() {
        /*
        shuffled = new ArrayList<>();
        imagesNormal = new ArrayList<>();
        imagesChallenges = new ArrayList<>();
        numbers = new ArrayList<>();
        newCards = new ArrayList<>();
         */
    }

    public void assignCardTheme(DeckTheme theme, ArrayList<Card> concreteCards, Game game, Button[] buttons, Context context) {
        if(isChallengeOrCasual(game)){
            if(game.getId() == 10){
                Dealer cardDealer = new DealTwoDecks();
                cardDealer.assignCardTheme(theme, concreteCards, game, buttons, context);
            } else {
                Dealer cardDealer = new DealOneDeck();
                cardDealer.assignCardTheme(theme, concreteCards, game, buttons, context);
            }
        } else {
            Dealer cardDealer = new DealSpecialDecks();
            cardDealer.assignCardTheme(theme, concreteCards, game, buttons, context);
        }
    }

    public void addChallengesWon(List<Integer> succedeedChallenges) {
        this.challengesWon = succedeedChallenges;
    }

    private boolean isChallengeOrCasual(Game game) {
        return (game.getGameMode().equals(GameMode.CASUAL) || game.getGameMode().equals(GameMode.CHALLENGE));
    }
}
