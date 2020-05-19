package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;
import es.imposoft.twins.card.ConcreteCard;

public abstract class AbstractScore {

    int score;
    private boolean correct;
    protected boolean lastCorrect;
    protected int successesFollowed;
    protected int toAdd;

    public AbstractScore(){
        score = 0;
        correct = false;
        lastCorrect = false;
        successesFollowed = 0;
        toAdd = 0;
    }

    public int updateScore(boolean correct, Card card) {
        setCorrect(correct);
        assignPoints();
        calculateAndSetScore();
        if(score<0){
            score = 0;
        }
        return score;
    }

    abstract void calculateAndSetScore();

    abstract void assignPoints();

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    boolean isCorrect() {
        return correct;
    }

    void setCorrect(boolean correct) {
        this.correct = correct;
    }

}
