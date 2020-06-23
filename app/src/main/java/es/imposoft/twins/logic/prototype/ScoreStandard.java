package es.imposoft.twins.logic.prototype;

import es.imposoft.twins.logic.card.Card;

public class ScoreStandard extends AbstractScore {

    public ScoreStandard() {
        super();
    }

    void calculateAndSetScore(Card card){
        score = getScore();
        if (!isCorrect()) {
            score += -5;
        } else {
            score += (card.getPoints() * 3);
        }
        setScore(score);
    }

}
