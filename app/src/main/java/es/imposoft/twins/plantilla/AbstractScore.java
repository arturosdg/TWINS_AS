package es.imposoft.twins.plantilla;

public abstract class AbstractScore {

    int score;
    private boolean correct;
    boolean lastCorrect;
    int successesFollowed;
    int toAdd;

    AbstractScore() {
        score = 0;
        correct = false;
        lastCorrect = false;
        successesFollowed = 0;
        toAdd = 0;
    }

    public abstract int updateScore(boolean lastCorrect);

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
