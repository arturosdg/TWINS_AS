package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;

public class ScoreStandard extends AbstractScore {

    public ScoreStandard() {
        super();
    }

    public int updateScore(boolean correct, Card card) {
        setCorrect(correct);
        assignPoints();
        calculateAndSetScore(card);
        if(score<0){
            score = 0;
        }
        if(correct) score += card.getPoints();
        return score;
    }

    void assignPoints() {
        if (isCorrect()) {
            toAdd = 10; //si acierta
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
            score += card.getPoints();
        }
        setScore(score);
    }

}
