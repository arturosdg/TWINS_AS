package es.imposoft.twins.gametypes;

import es.imposoft.twins.components.Deck;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.plantilla.AbstractScore;

public class Game {
    private final int seconds;
    private final Chronometer chronometerType;
    private final int minScore;
    private final int cardAmount;
    private final int revealSeconds;
    private final Deck deck;
    private final AbstractScore scoreManager;

    public Game(int seconds, Chronometer chronometerType, int minScore, int cardAmount, int revealSeconds, Deck deck, AbstractScore scoreManager){
        this.seconds = seconds;
        this.chronometerType = chronometerType;
        this.minScore = minScore;
        this.cardAmount = cardAmount;
        this.revealSeconds = revealSeconds;
        this.deck = deck;
        this.scoreManager = scoreManager;
    }

    public int getSeconds(){
        return seconds;
    }

    public Chronometer getChronometerType(){
        return chronometerType;
    }

    public int getMinScore(){
        return minScore;
    }

    public int getCardAmount(){
        return cardAmount;
    }

    public int getRevealSeconds(){
        return revealSeconds;
    }

    public Deck getDeck(){
        return deck;
    }

    public AbstractScore getScoreManager() { return scoreManager; }

    public String printGame() {
        return "game lasts " + seconds +
                " with chronometer " + chronometerType
                + "with minscore of " + minScore
                + "and this cards " + cardAmount
                + "revealed for " +revealSeconds
                + "Game type " + deck;
    }
}
