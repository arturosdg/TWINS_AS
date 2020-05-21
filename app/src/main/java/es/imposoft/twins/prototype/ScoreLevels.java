package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;

public class ScoreLevels extends AbstractScore {

    public ScoreLevels() { super(); }

    void assignPoints() {
        if (isCorrect()) {
            toAdd = 8; //si acierta
        } else {
            toAdd = -2; //si falla
        }
    }

    /**cuando acierta dos seguidas INDEPENDIENTES se le suman 15 puntos mas y si falla dos seguidas
     *INDEPENDIENTES se le restan 5 puntos mas*/

    void calculateAndSetScore(Card card){
        score = getScore();

        score += toAdd;
        if (!isCorrect()) {
            if (!lastCorrect && successesFollowed < 0) {
                score += -5;
                successesFollowed = 0;
            } else {
                successesFollowed = -1;
            }
            lastCorrect = false;

        } else {

            if (lastCorrect && successesFollowed > 0) {
                score += 15;
                successesFollowed = 0;
            } else {
                successesFollowed = 1;
            }
            lastCorrect = true;
            score += (card.getPoints() * 2);
        }
        setScore(score);
    }
}
