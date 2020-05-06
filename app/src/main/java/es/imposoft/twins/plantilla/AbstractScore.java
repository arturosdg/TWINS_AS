package es.imposoft.twins.plantilla;

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

    public int updateScore(boolean correct) {
        setCorrect(correct);
        assignPoints();
        calculateAndSetScore();
        return score;
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
