package es.imposoft.twins.builders;

import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.gametypes.Game;

public class ConcreteBuilderLevel implements LevelBuilder {
    private int chronometerSeconds;
    private Chronometer chronometerType;
    private int minScore;
    private int cardAmount;
    private int revealSeconds;
    private DeckTheme deckTheme;
    private GameMode gameMode;
    private int levelId;
    private int song;
    private int maxFails;

    @Override
    public void setChronometerParameters(int chronometerSeconds, Chronometer chronometerType) {
        this.chronometerSeconds = chronometerSeconds;
        this.chronometerType = chronometerType;
    }

    @Override
    public void setScoreParameters(int minScore, int maxFails) {
        this.maxFails = maxFails;
        this.minScore = minScore;
    }

    @Override
    public void setTableParameters(int cardAmount, int revealTime, DeckTheme deckTheme) {
        this.revealSeconds = revealTime;
        this.cardAmount = cardAmount;
        this.deckTheme = deckTheme;
    }

    @Override
    public void setGameParameters(GameMode gameMode, int levelId) {
        this.gameMode = gameMode;
        this.levelId = levelId;
    }

    @Override
    public void setSong(int song) {
        this.song = song;
    }

    public Game getResult(){
        return new Game(levelId, chronometerSeconds,  chronometerType, minScore, cardAmount, revealSeconds, deckTheme, gameMode, song, maxFails);
    }
}
