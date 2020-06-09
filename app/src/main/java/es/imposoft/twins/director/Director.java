package es.imposoft.twins.director;

import es.imposoft.twins.R;
import es.imposoft.twins.builders.ConcreteBuilderLevel;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.components.GameMode;

public class Director {

    private DeckTheme deckTheme;

    public Director(DeckTheme deckTheme){
        this.deckTheme = deckTheme;
    }

    public void constructStandardGame(ConcreteBuilderLevel builder){
        builder.setChronometerParameters(0, Chronometer.NORMAL);
        builder.setGameParameters(GameMode.STANDARD, 1);
        builder.setScoreParameters(0,10);
        builder.setTableParameters(20,3,deckTheme);
        builder.setSong(R.raw.partidaestandar);
    }

    public void constructCasualGame(ConcreteBuilderLevel builder){
        builder.setChronometerParameters(90, Chronometer.NONE);
        builder.setGameParameters(GameMode.CASUAL, 2);
        builder.setScoreParameters(0,1000000);
        builder.setTableParameters(20,3,deckTheme);
        builder.setSong(R.raw.musicapartidalibre);
    }

    public void constructLevel1(ConcreteBuilderLevel builder){
        builder.setGameParameters(GameMode.LEVELS, 3);
        builder.setScoreParameters(5,7);
        builder.setTableParameters(16,3,DeckTheme.EMOJI);
        builder.setChronometerParameters(60, Chronometer.DESCENDING);
        builder.setSong(R.raw.niveltranquilo);
    }

    public void constructLevel2(ConcreteBuilderLevel builder){
        builder.setGameParameters(GameMode.LEVELS, 4);
        builder.setScoreParameters(17,6);
        builder.setTableParameters(16,2,DeckTheme.CARS);
        builder.setChronometerParameters(50, Chronometer.DESCENDING);
        builder.setSong(R.raw.nivelintensito);
    }

    public void constructLevel3(ConcreteBuilderLevel builder){
        builder.setGameParameters(GameMode.LEVELS, 5);
        builder.setScoreParameters(25,5);
        builder.setTableParameters(20,2,DeckTheme.EMOJI);
        builder.setChronometerParameters(60, Chronometer.DESCENDING);
        builder.setSong(R.raw.nivel3);
    }

    public void constructLevel4(ConcreteBuilderLevel builder){
        builder.setGameParameters(GameMode.LEVELS, 6);
        builder.setScoreParameters(38,5);
        builder.setTableParameters(20,1,DeckTheme.CARS);
        builder.setChronometerParameters(50, Chronometer.DESCENDING);
        builder.setSong(R.raw.nivel3);
    }

    public void constructLevel5(ConcreteBuilderLevel builder){
        builder.setGameParameters(GameMode.LEVELS, 7);
        builder.setScoreParameters(45,5);
        builder.setTableParameters(24,1,DeckTheme.EMOJI);
        builder.setChronometerParameters(50, Chronometer.DESCENDING);
        builder.setSong(R.raw.nivel3);
    }

    //Close call challenge
    public void constructChallenge1(ConcreteBuilderLevel builder) {
        builder.setGameParameters(GameMode.CHALLENGE, 8);
        builder.setScoreParameters(0,6);
        builder.setTableParameters(16,3,DeckTheme.EMOJI);
        builder.setChronometerParameters(18, Chronometer.DESCENDING);
        builder.setSong(R.raw.desafiotiempo);
    }

    //Perfect Challenge
    public void constructChallenge2(ConcreteBuilderLevel builder) {
        builder.setGameParameters(GameMode.CHALLENGE, 9);
        builder.setScoreParameters(0,1);
        builder.setTableParameters(24,8,DeckTheme.EMOJI);
        builder.setChronometerParameters(0, Chronometer.NONE);
        builder.setSong(R.raw.desafioperfecto2);
    }

    //Mixed decks challenge
    public void constructChallenge3(ConcreteBuilderLevel builder) {
        builder.setGameParameters(GameMode.CHALLENGE, 10);
        builder.setScoreParameters(0,5);
        builder.setTableParameters(24,3,DeckTheme.EMOJI);
        builder.setChronometerParameters(50, Chronometer.DESCENDING);
        builder.setSong(R.raw.desafiodosbarajas);
    }
}
