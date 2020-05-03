package es.imposoft.twins.gametypes;

import es.imposoft.twins.Deck;
import es.imposoft.twins.components.Chronometer;

public class Game {
    private final int seconds;
    private final Chronometer chronometerType;
    private final int minScore;
    private final int cardAmount;
    private final int revealSeconds;
    private final Deck deck;

    public Game(int seconds, Chronometer chronometerType,int minScore,int cardAmount,int revealSeconds, Deck deck){
        this.seconds = seconds;
        this.chronometerType = chronometerType;
        this.minScore = minScore;
        this.cardAmount = cardAmount;
        this.revealSeconds = revealSeconds;
        this.deck = deck;
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

    public String printGame() {
        return "game lasts " + seconds +
                " with chronometer " + chronometerType
                + "with minscore of " + minScore
                + "and this cards " + cardAmount
                + "revealed for " +revealSeconds
                + "Game type " + deck;
    }
}
