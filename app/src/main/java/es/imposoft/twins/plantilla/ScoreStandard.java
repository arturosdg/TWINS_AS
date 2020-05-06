package es.imposoft.twins.plantilla;

public class ScoreStandard extends AbstractScore {

    public ScoreStandard() { super(); }

    public int updateScore(boolean correct) {
        setCorrect(correct);
        assignPoints();
        calculateAndSetScore();
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
            if (lastCorrect) {
                score += Math.pow(2, successesFollowed);
            }
            successesFollowed++;
            lastCorrect = true;
        }

        setScore(score);
    }

}