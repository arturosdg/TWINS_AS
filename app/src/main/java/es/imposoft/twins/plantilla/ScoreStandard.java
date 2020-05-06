package es.imposoft.twins.plantilla;

public class ScoreStandard extends AbstractScore {

    private int score;
    private boolean correct;

    public ScoreStandard() {
        score = super.getScore();
        correct = super.isCorrect();
    }

    void assignPoints() {
        if (isCorrect()) {
            toAdd = 10; //si acierta
        } else {
            toAdd = -3; //si falla
        }
    }

    void calculateAndSetScore(){
        int res = getScore();

        res += toAdd;
        if (!isCorrect()) {
            lastCorrect = false;
            successesFollowed = 0;
        } else {
            if (lastCorrect) {
                res += Math.pow(2, successesFollowed);
            }
            successesFollowed++;
            lastCorrect = true;
        }

        setScore(res);
    }

}
