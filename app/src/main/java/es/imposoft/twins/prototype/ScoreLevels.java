package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;
import es.imposoft.twins.card.CardDecorator;
import es.imposoft.twins.card.SpecialCardDecorator;

public class ScoreLevels extends AbstractScore {

    CardDecorator cardDecorator;

    public ScoreLevels() { super(); }

    public int updateScore(boolean correct, Card card) {
        setCorrect(correct);
        assignPoints();
        calculateAndSetScore();
        if(score<0){
            score = 0;
        }
        if(correct) score += card.getPoints();
        return score;
    }

    void assignPoints() {
        if (isCorrect()) {
            toAdd = 8; //si acierta
        } else {
            toAdd = -2; //si falla
        }
    }

    /**cuando acierta dos seguidas INDEPENDIENTES se le suman 15 puntos mas y si falla dos seguidas
     *INDEPENDIENTES se le restan 5 puntos mas*/

    void calculateAndSetScore(){
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
        }

        setScore(score + cardDecorator.getPoints());
    }
}
