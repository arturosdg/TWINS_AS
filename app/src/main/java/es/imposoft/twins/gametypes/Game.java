package es.imposoft.twins.gametypes;

import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.components.GameType;

public class Game {
    private final int seconds;
    private final Chronometer chronometerType;
    private final int minScore;
    private final int cardAmount;
    private final int revealSeconds;
    private final DeckTheme deckTheme;
    private final GameMode gameMode;
    private final int id;
    private GameType gameType;
    private int song;
    private int maxFails;

    public Game(int id, int seconds, Chronometer chronometerType, int minScore, int cardAmount, int revealSeconds, DeckTheme deckTheme, GameMode gameMode, GameType gameType,int song, int maxFails){
        this.song = song;
        this.id = id;
        this.seconds = seconds;
        this.chronometerType = chronometerType;
        this.minScore = minScore;
        this.cardAmount = cardAmount;
        this.revealSeconds = revealSeconds;
        this.deckTheme = deckTheme;
        this.gameMode = gameMode;
        this.gameType = gameType;
        this.maxFails = maxFails;
    }

    public int getId(){ return id;}

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

    public DeckTheme getDeckTheme(){
        return deckTheme;
    }

    public GameMode getGameMode() { return gameMode; }

    public int getSong(){
        return song;
    }

    public int getMaxFails() { return maxFails; }
}
