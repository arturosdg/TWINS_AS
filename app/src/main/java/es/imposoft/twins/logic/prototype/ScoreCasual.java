package es.imposoft.twins.logic.prototype;

import es.imposoft.twins.logic.card.Card;

public class ScoreCasual extends AbstractScore {

    public ScoreCasual() { super(); }

    void calculateAndSetScore(Card card){
        score = getScore();
        if (!isCorrect()) {
            score += -4;
        } else {
            score += (card.getPoints() * 5);
        }
        setScore(score);
    }
}
