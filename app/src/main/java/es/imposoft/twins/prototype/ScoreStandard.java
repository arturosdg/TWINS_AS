package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;
import es.imposoft.twins.card.CardDecorator;
import es.imposoft.twins.card.SpecialCardDecorator;

public class ScoreStandard extends AbstractScore {
    CardDecorator cardDecorator;

    public ScoreStandard() {
        super();
    }

    public int updateScore(boolean correct, Card card) {
        //if(card.isSpecial()) cardDecorator = new SpecialCardDecorator(card);
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
            toAdd = 10; //si acierta
        } else {
            toAdd = -3; //si falla
        }
    }

    void calculateAndSetScore(){
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
