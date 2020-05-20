package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;
import es.imposoft.twins.card.ConcreteCard;

public class ScoreCasual extends AbstractScore {

    public ScoreCasual() { super(); }

    void assignPoints() {
        if (isCorrect()) {
            toAdd = 15; //si acierta
        } else {
            toAdd = -5; //si falla
        }
    }

    void calculateAndSetScore(Card card){
        score = getScore();
        score += toAdd;
        setScore(score);
    }
}
