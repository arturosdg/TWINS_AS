package es.imposoft.twins.logic.gametypes;

import es.imposoft.twins.logic.components.DeckTheme;
import es.imposoft.twins.logic.components.Chronometer;
import es.imposoft.twins.logic.components.GameMode;

public class Game {
    //Podrian ser un componente Chronometer, 3 productos concretos segun el chronometer(?)
    private final int seconds;
    private final Chronometer chronometerType;

    //Solo en partida libre
    private final int minScore;

    //En todos menos partida libre
    private int maxFails;

    //En todos
    private final int revealSeconds;

    //Podria establecerse en funcion del builder, cada gamemode un producto game concreto
    private final GameMode gameMode;

    //Poner la logica de crear el tablero y asignarselo a la clase game
    private final int cardAmount;

    //Algunas partidas te dejan elegir, otras no Challenges no
    private final DeckTheme deckTheme;

    //Solo ponemos id si son niveles o challenges(?) o son 2 productos concretos distintos
    private final int id;

    //Podriamos meter la logica de poner la cancion en la creacion del objeto(?)
    private int song;

    public Game(int id, int seconds, Chronometer chronometerType, int minScore, int cardAmount, int revealSeconds, DeckTheme deckTheme, GameMode gameMode,int song, int maxFails){
        this.song = song;
        this.id = id;
        this.seconds = seconds;
        this.chronometerType = chronometerType;
        this.minScore = minScore;
        this.cardAmount = cardAmount;
        this.revealSeconds = revealSeconds;
        this.deckTheme = deckTheme;
        this.gameMode = gameMode;
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
