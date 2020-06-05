package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;

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
