package es.imposoft.twins.components;

import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import es.imposoft.twins.card.*;
import es.imposoft.twins.gametypes.Game;
import es.imposoft.twins.strategies.DealOneDeck;
import es.imposoft.twins.strategies.DealSpecialDecks;
import es.imposoft.twins.strategies.DealTwoDecks;
import es.imposoft.twins.strategies.Dealer;

public class Deck {

    List<String> challengesWon;
    Dealer cardDealer;

    public Deck() { }

    public void assignCardTheme(DeckTheme theme, ArrayList<Card> concreteCards, Game game, Button[] buttons, Context context, String email) {
        if(isChallengeOrCasual(game)){
            if(game.getId() == 10){
                cardDealer = new DealTwoDecks();
                cardDealer.assignCardTheme(theme, concreteCards, game, buttons, context, email);
            } else {
                cardDealer = new DealOneDeck();
                cardDealer.assignCardTheme(theme, concreteCards, game, buttons, context, email);
            }
        } else {
            cardDealer = new DealSpecialDecks();
            cardDealer.assignCardTheme(theme, concreteCards, game, buttons, context, email);
        }
    }

    public void addChallengesWon(List<String> succedeedChallenges) {
        this.challengesWon = succedeedChallenges;
    }

    private boolean isChallengeOrCasual(Game game) {
        return (game.getGameMode().equals(GameMode.CASUAL) || game.getGameMode().equals(GameMode.CHALLENGE));
    }
}
