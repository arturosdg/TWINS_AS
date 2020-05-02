package es.imposoft.twins.director;

import es.imposoft.twins.builders.ConcreteBuilderLevel;
import es.imposoft.twins.components.Chronometer;

public class Director {
    public void constructStandardGame(ConcreteBuilderLevel builder){
        builder.setMinScore(0);
        builder.setCardAmount(16);
        builder.setRevealTime(3);
        builder.setChronometer(90, Chronometer.NORMAL);
    }

    public void constructFreeGame(ConcreteBuilderLevel builder){
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
