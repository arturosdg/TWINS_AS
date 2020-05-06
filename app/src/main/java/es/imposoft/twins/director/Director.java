package es.imposoft.twins.director;

import es.imposoft.twins.components.Deck;
import es.imposoft.twins.builders.ConcreteBuilderLevel;
import es.imposoft.twins.components.Chronometer;
import es.imposoft.twins.plantilla.*;

public class Director {
    private AbstractScore scoreManager;
    private Deck deck;
    public Director(Deck deck){
        this.deck = deck;
    }
    public void constructStandardGame(ConcreteBuilderLevel builder){
        builder.setCardTheme(deck);
        builder.setMinScore(0);
        builder.setCardAmount(16);
        builder.setRevealTime(3);
        builder.setChronometer(45, Chronometer.NORMAL);
        builder.setScoreManager(scoreManager = new ScoreStandard());
    }

    public void constructCasualGame(ConcreteBuilderLevel builder){
        builder.setCardTheme(deck);
        builder.setMinScore(0);
        builder.setCardAmount(16);
        builder.setRevealTime(3);
        builder.setChronometer(90, Chronometer.NONE);
        builder.setScoreManager(scoreManager = new ScoreFree());
    }

    public void constructLevel1(ConcreteBuilderLevel builder){
        builder.setCardTheme(deck);//Deck.EMOJI
        builder.setMinScore(5);
        builder.setCardAmount(16);
        builder.setRevealTime(3);
        builder.setChronometer(60, Chronometer.DESCENDING);
        builder.setScoreManager(scoreManager = new ScoreLevels());
    }

    public void constructLevel2(ConcreteBuilderLevel builder){
        builder.setCardTheme(deck);//Deck.CARS
        builder.setMinScore(5);//25
        builder.setCardAmount(16);//20
        builder.setRevealTime(2);
        builder.setChronometer(50, Chronometer.DESCENDING);
        builder.setScoreManager(scoreManager = new ScoreLevels());
    }

    public void constructLevel3(ConcreteBuilderLevel builder){
        builder.setCardTheme(deck);//Deck.EMOJI
        builder.setMinScore(5);//55
        builder.setCardAmount(16);//24
        builder.setRevealTime(1);
        builder.setChronometer(40, Chronometer.DESCENDING);
        builder.setScoreManager(scoreManager = new ScoreLevels());
    }
}
