package es.imposoft.twins.builders;

import es.imposoft.twins.R;
import es.imposoft.twins.components.DeckSize;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.components.GameDuration;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.components.MaxFails;
import es.imposoft.twins.components.MinScore;
import es.imposoft.twins.components.RevealTime;
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
    public void setChronometerParameters(GameDuration gameDuration, Chronometer chronometerType) {
        //Prevent fails creating levels
        if(chronometerType == Chronometer.NONE || chronometerType == Chronometer.NORMAL){
            gameDuration = GameDuration.INFINITE;
        }
        switch (gameDuration){
            case INFINITE:
                this.chronometerSeconds = 0;
                break;
            case SHORT:
                this.chronometerSeconds = 45;
                break;
            case MEDIUM:
                this.chronometerSeconds = 65;
                break;
            case LONG:
                this.chronometerSeconds = 90;
                break;
            case CLOSECHALLENGE:
                this.chronometerSeconds = 18;
                break;
        }
        this.chronometerType = chronometerType;
    }

    @Override
    public void setScoreParameters(MinScore minScore, MaxFails maxFails) {
        switch (minScore){
            case NONE:
                this.minScore = 0;
                break;
            case EASY:
                this.minScore = 18;
                break;
            case MEDIUM:
                this.minScore = 27;
                break;
            case HARD:
                this.minScore = 40;
                break;
        }

        switch (maxFails){
            case NONE:
                this.maxFails = 1000000;
                break;
            case EASY:
                this.maxFails = 15;
                break;
            case MEDIUM:
                this.maxFails = 10;
                break;
            case HARD:
                this.maxFails = 5;
                break;
            case IMPOSSIBLE:
                this.maxFails = 1;
                break;
        }
    }

    @Override
    public void setTableParameters(DeckSize deckSize, RevealTime revealTime, DeckTheme deckTheme) {
        switch (revealTime){
            case NONE:
                this.revealSeconds = 0;
                break;
            case SHORT:
                this.revealSeconds = 1;
                break;
            case MEDIUM:
                this.revealSeconds = 3;
                break;
            case LONG:
                this.revealSeconds = 8;
                break;
        }

        this.deckTheme = deckTheme;
        switch(deckSize){
            case BIG:
                this.cardAmount = 24;
                break;
            case MEDIUM:
                this.cardAmount = 20;
                break;
            case SMALL:
                this.cardAmount = 16;
                break;
        }
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
