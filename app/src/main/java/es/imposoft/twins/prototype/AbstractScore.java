package es.imposoft.twins.prototype;

import es.imposoft.twins.card.Card;

public abstract class AbstractScore {

    int score;
    boolean correct;

    public AbstractScore(){
        score = 0;
        correct = false;
    }

    public int updateScore(boolean correct, Card card) {
        setCorrect(correct);
        calculateAndSetScore(card);
        if(score<0){
            score = 0;
        }
        return score;
    }

    abstract void calculateAndSetScore(Card card);

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
