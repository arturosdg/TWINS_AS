package es.imposoft.twins.builders;

import es.imposoft.twins.components.Deck;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.gametypes.Game;

public class ConcreteBuilderLevel implements LevelBuilder {
    private int seconds;
    private Chronometer chronometerType;
    private int minScore;
    private int cardAmount;
    private int revealSeconds;
    private Deck deck;
    //private GameMode gameMode;

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
    public void setRevealTime(int gameTime) {
        this.revealSeconds = gameTime;
    }

    @Override
    public void setCardTheme(Deck deck) { this.deck = deck; }

    //public void setGameMode(GameMode gameMode) {this.gameMode = gameMode; }

    public Game getResult(){
        return new Game(seconds,  chronometerType, minScore, cardAmount, revealSeconds, deck);
    }
}
