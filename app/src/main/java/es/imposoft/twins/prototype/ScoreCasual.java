package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;
import es.imposoft.twins.card.ConcreteCard;

public class ScoreCasual extends AbstractScore {

    public ScoreCasual() { super(); }

    public int updateScore(boolean correct, Card card) {
        setCorrect(correct);
        assignPoints();
        calculateAndSetScore();
        if(score<0){
            score = 0;
        }
        return score;
    }

    void assignPoints() {
        if (isCorrect()) {
            toAdd = 15; //si acierta
        } else {
            toAdd = -5; //si falla
        }
    }

    void calculateAndSetScore(){
        score = getScore();
        score += toAdd;
        setScore(score);
    }
}
