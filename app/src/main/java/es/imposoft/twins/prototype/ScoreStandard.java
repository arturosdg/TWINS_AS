package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;

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
