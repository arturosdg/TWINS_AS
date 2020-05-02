package es.imposoft.twins.builders;

import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.gametypes.Game;

public class ConcreteBuilderLevel implements LevelBuilder {
    private int seconds;
    private Chronometer chronometerType;
    private int minScore;
    private int cardAmount;
    private int revealSeconds;

    @Override
    public void setChronometer(int seconds, Chronometer type) {
        this.seconds = seconds;
        this.chronometerType = type;
    }

    @Override
    public void setMinScore(int score) {
        this.minScore = score;
    }

    @Override
    public void setCardAmount(int amount) {
        this.cardAmount = amount;
    }

    @Override
    public void setRevealTime(int tiempo) {
        this.revealSeconds = tiempo;
    }

    public Game getResult(){
        return new Game(seconds,  chronometerType, minScore, cardAmount, revealSeconds);
    }
}
