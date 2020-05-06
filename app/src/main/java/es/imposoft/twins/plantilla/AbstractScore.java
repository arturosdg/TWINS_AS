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
