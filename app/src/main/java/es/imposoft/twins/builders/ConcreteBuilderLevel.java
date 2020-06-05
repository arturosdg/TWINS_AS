package es.imposoft.twins.builders;

import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.components.GameType;
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
    private GameType gameType;
    private int song;
    private int maxFails;

    @Override
    public void setChronometer(int chronometerSeconds, Chronometer chronometerType) {
        this.chronometerSeconds = chronometerSeconds;
        this.chronometerType = chronometerType;
    }

    @Override
    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    @Override
    public void setCardAmount(int cardAmount) {
        this.cardAmount = cardAmount;
    }

    @Override
    public void setRevealTime(int revealTime) {
        this.revealSeconds = revealTime;
    }

    @Override
    public void setCardTheme(DeckTheme deckTheme) { this.deckTheme = deckTheme; }

    @Override
    public void setGameMode(GameMode gameMode) {this.gameMode = gameMode; }

    public void setGameType(GameType gameType) { this.gameType = gameType; }

    @Override
    public void setId(int levelId){
        this.levelId = levelId;
    }

    @Override
    public void setSong(int song) {
        this.song = song;
    }

    public void setMaxFails(int maxFails) { this.maxFails = maxFails; }

    public Game getResult(){
        return new Game(levelId, chronometerSeconds,  chronometerType, minScore, cardAmount, revealSeconds, deckTheme, gameMode, gameType, song, maxFails);
    }
}
