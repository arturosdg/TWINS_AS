package es.imposoft.twins.plantilla;

public abstract class AbstractScore {

    private int score;
    private boolean correct;

    public AbstractScore() {
        score = 0;
        correct = false;
    }

    protected boolean lastCorrect = false;
    protected int successesFollowed = 0;
    protected int toAdd = 0;

    public void updateScore(boolean correct) {
        setCorrect(correct);
        assignPoints();
        calculateAndSetScore();
    }


    abstract void calculateAndSetScore();

    abstract void assignPoints();


    public int getScore() {
        return score;
    }

    protected void setScore(int score) {
        this.score = score;
    }

    protected boolean isCorrect() {
        return correct;
    }

    protected void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
