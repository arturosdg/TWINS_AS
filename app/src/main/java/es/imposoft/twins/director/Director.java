package es.imposoft.twins.director;

import es.imposoft.twins.Deck;
import es.imposoft.twins.builders.ConcreteBuilderLevel;
import es.imposoft.twins.components.Chronometer;

public class Director {
    private Deck deck;
    public Director(Deck deck){
        this.deck = deck;
    }
    public void constructStandardGame(ConcreteBuilderLevel builder){
        builder.setCardTheme(deck);
        builder.setMinScore(0);
        builder.setCardAmount(16);
        builder.setRevealTime(3);
        builder.setChronometer(45, Chronometer.DESCENDING);
    }

    public void constructCasualGame(ConcreteBuilderLevel builder){
        builder.setCardTheme(deck);
        builder.setMinScore(0);
        builder.setCardAmount(16);
        builder.setRevealTime(3);
        builder.setChronometer(90, Chronometer.NONE);
    }

    public void constructLevel1(ConcreteBuilderLevel builder){
        builder.setMinScore(5);
        builder.setCardAmount(16);
        builder.setRevealTime(3);
        builder.setChronometer(90, Chronometer.DESCENDING);
    }

    public void constructLevel2(ConcreteBuilderLevel builder){
        builder.setMinScore(5);
        builder.setCardAmount(16);
        builder.setRevealTime(3);
        builder.setChronometer(90, Chronometer.DESCENDING);
    }

    public void constructLevel3(ConcreteBuilderLevel builder){
        builder.setMinScore(5);
        builder.setCardAmount(16);
        builder.setRevealTime(3);
        builder.setChronometer(90, Chronometer.DESCENDING);
    }
}
