package es.imposoft.twins.gametypes;

import es.imposoft.twins.components.Deck;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.components.GameMode;

public class Game {
    private final int seconds;
    private final Chronometer chronometerType;
    private final int minScore;
    private final int cardAmount;
    private final int revealSeconds;
    private final Deck deck;
    private final GameMode gameMode;

    public Game(int seconds, Chronometer chronometerType, int minScore, int cardAmount, int revealSeconds, Deck deck, GameMode gameMode){
        this.seconds = seconds;
        this.chronometerType = chronometerType;
        this.minScore = minScore;
        this.cardAmount = cardAmount;
        this.revealSeconds = revealSeconds;
        this.deck = deck;
        this.gameMode = gameMode;
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

    public GameMode getGameMode() { return gameMode;}

    public String printGame() {
        return "game lasts " + seconds +
                " with chronometer " + chronometerType
                + "with minscore of " + minScore
                + "and this cards " + cardAmount
                + "revealed for " +revealSeconds
                + "Game type " + deck
                + "Game mode " + gameMode;
    }
}
