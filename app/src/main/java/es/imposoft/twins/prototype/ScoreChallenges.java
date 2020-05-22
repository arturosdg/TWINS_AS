package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;

public class ScoreChallenges extends AbstractScore {

    public ScoreChallenges() {
        super();
    }

    void assignPoints() {
        if (isCorrect()) {
            toAdd = 0; //si acierta
        } else {
            toAdd = -3; //si falla
        }
    }

    void calculateAndSetScore(Card card){
        score = getScore();
        score += toAdd;
        if (!isCorrect()) {
            lastCorrect = false;
            successesFollowed = 0;
        } else {
            if (lastCorrect)
                score += Math.pow(2, successesFollowed);
            successesFollowed++;
            lastCorrect = true;
        }
        setScore(score);
    }

}
