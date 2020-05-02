package es.imposoft.twins.gametypes;

import es.imposoft.twins.components.Chronometer;

public class Game {
    private final int seconds;
    private final Chronometer chronometerType;
    private final int minScore;
    private final int cardAmount;
    private final int revealSeconds;

    public Game(int seconds, Chronometer chronometerType,int minScore,int cardAmount,int revealSeconds){
        this.seconds = seconds;
        this.chronometerType = chronometerType;
        this.minScore = minScore;
        this.cardAmount = cardAmount;
        this.revealSeconds = revealSeconds;
    }
}
