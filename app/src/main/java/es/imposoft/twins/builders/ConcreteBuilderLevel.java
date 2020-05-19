package es.imposoft.twins.builders;

import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.components.GameType;
import es.imposoft.twins.gametypes.Game;

public class ConcreteBuilderLevel implements LevelBuilder {
    private int seconds;
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
        return new Game(levelId, seconds,  chronometerType, minScore, cardAmount, revealSeconds, deckTheme, gameMode, gameType, song, maxFails);
    }
}
