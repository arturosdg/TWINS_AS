package es.imposoft.twins.logic.director;

import es.imposoft.twins.R;
import es.imposoft.twins.logic.builders.ConcreteBuilderLevel;
import es.imposoft.twins.logic.components.Chronometer;
import es.imposoft.twins.logic.components.DeckSize;
import es.imposoft.twins.logic.components.DeckTheme;
import es.imposoft.twins.logic.components.GameDuration;
import es.imposoft.twins.logic.components.GameMode;
import es.imposoft.twins.logic.components.MaxFails;
import es.imposoft.twins.logic.components.MinScore;
import es.imposoft.twins.logic.components.RevealTime;

public class Director {

    private DeckTheme deckTheme;

    public Director(DeckTheme deckTheme){
        this.deckTheme = deckTheme;
    }

    public void constructStandardGame(ConcreteBuilderLevel builder){
        builder.setChronometerParameters(GameDuration.INFINITE, Chronometer.NORMAL);
        builder.setGameParameters(GameMode.STANDARD, 1);
        builder.setScoreParameters(MinScore.NONE, MaxFails.EASY);
        builder.setTableParameters(DeckSize.MEDIUM, RevealTime.MEDIUM,deckTheme);
        builder.setSong(R.raw.partidaestandar);
    }

    public void constructCasualGame(ConcreteBuilderLevel builder){
        builder.setChronometerParameters(GameDuration.LONG, Chronometer.NONE);
        builder.setGameParameters(GameMode.CASUAL, 2);
        builder.setScoreParameters(MinScore.NONE,MaxFails.NONE);
        builder.setTableParameters(DeckSize.MEDIUM,RevealTime.MEDIUM,deckTheme);
        builder.setSong(R.raw.musicapartidalibre);
    }

    public void constructLevel1(ConcreteBuilderLevel builder){
        builder.setGameParameters(GameMode.LEVELS, 3);
        builder.setScoreParameters(MinScore.EASY,MaxFails.EASY);
        builder.setTableParameters(DeckSize.SMALL,RevealTime.MEDIUM,DeckTheme.EMOJI);
        builder.setChronometerParameters(GameDuration.MEDIUM, Chronometer.DESCENDING);
        builder.setSong(R.raw.niveltranquilo);
    }

    public void constructLevel2(ConcreteBuilderLevel builder){
        builder.setGameParameters(GameMode.LEVELS, 4);
        builder.setScoreParameters(MinScore.MEDIUM,MaxFails.EASY);
        builder.setTableParameters(DeckSize.SMALL,RevealTime.SHORT,DeckTheme.CARS);
        builder.setChronometerParameters(GameDuration.MEDIUM, Chronometer.DESCENDING);
        builder.setSong(R.raw.nivelintensito);
    }

    public void constructLevel3(ConcreteBuilderLevel builder){
        builder.setGameParameters(GameMode.LEVELS, 5);
        builder.setScoreParameters(MinScore.MEDIUM,MaxFails.MEDIUM);
        builder.setTableParameters(DeckSize.MEDIUM,RevealTime.SHORT,DeckTheme.EMOJI);
        builder.setChronometerParameters(GameDuration.MEDIUM, Chronometer.DESCENDING);
        builder.setSong(R.raw.nivel3);
    }

    public void constructLevel4(ConcreteBuilderLevel builder){
        builder.setGameParameters(GameMode.LEVELS, 6);
        builder.setScoreParameters(MinScore.HARD,MaxFails.MEDIUM);
        builder.setTableParameters(DeckSize.MEDIUM,RevealTime.SHORT,DeckTheme.CARS);
        builder.setChronometerParameters(GameDuration.SHORT, Chronometer.DESCENDING);
        builder.setSong(R.raw.nivel3);
    }

    public void constructLevel5(ConcreteBuilderLevel builder){
        builder.setGameParameters(GameMode.LEVELS, 7);
        builder.setScoreParameters(MinScore.HARD,MaxFails.HARD);
        builder.setTableParameters(DeckSize.BIG,RevealTime.SHORT,DeckTheme.EMOJI);
        builder.setChronometerParameters(GameDuration.SHORT, Chronometer.DESCENDING);
        builder.setSong(R.raw.nivel3);
    }

    //Close call challenge
    public void constructChallenge1(ConcreteBuilderLevel builder) {
        builder.setGameParameters(GameMode.CHALLENGE, 8);
        builder.setScoreParameters(MinScore.NONE,MaxFails.EASY);
        builder.setTableParameters(DeckSize.SMALL,RevealTime.MEDIUM,DeckTheme.EMOJI);
        builder.setChronometerParameters(GameDuration.CLOSECHALLENGE, Chronometer.DESCENDING);
        builder.setSong(R.raw.desafiotiempo);
    }

    //Perfect Challenge
    public void constructChallenge2(ConcreteBuilderLevel builder) {
        builder.setGameParameters(GameMode.CHALLENGE, 9);
        builder.setScoreParameters(MinScore.NONE,MaxFails.IMPOSSIBLE);
        builder.setTableParameters(DeckSize.BIG,RevealTime.LONG,DeckTheme.EMOJI);
        builder.setChronometerParameters(GameDuration.INFINITE, Chronometer.NONE);
        builder.setSong(R.raw.desafioperfecto2);
    }

    //Mixed decks challenge
    public void constructChallenge3(ConcreteBuilderLevel builder) {
        builder.setGameParameters(GameMode.CHALLENGE, 10);
        builder.setScoreParameters(MinScore.NONE,MaxFails.EASY);
        builder.setTableParameters(DeckSize.BIG,RevealTime.MEDIUM,DeckTheme.EMOJI);
        builder.setChronometerParameters(GameDuration.SHORT, Chronometer.DESCENDING);
        builder.setSong(R.raw.desafiodosbarajas);
    }
}
