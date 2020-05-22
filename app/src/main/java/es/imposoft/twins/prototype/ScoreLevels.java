package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;

public class ScoreLevels extends AbstractScore {

    public ScoreLevels() { super(); }

    /**cuando acierta dos seguidas INDEPENDIENTES se le suman 15 puntos mas y si falla dos seguidas
     *INDEPENDIENTES se le restan 5 puntos mas*/

    void calculateAndSetScore(Card card){
        score = getScore();
        if (!isCorrect()) {
            score += -12;
        } else {
            score += (card.getPoints() * 4);
        }
        setScore(score);
    }
}
