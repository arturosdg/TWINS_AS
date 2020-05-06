package es.imposoft.twins.plantilla;

public class ScoreLevels extends AbstractScore {

    private int score;
    private boolean correct;

    public ScoreLevels() {
        score = super.getScore();
        correct = super.isCorrect();
    }

    void assignPoints() {
        if (isCorrect()) {
            toAdd = 8; //si acierta
        } else {
            toAdd = -2; //si falla
        }
    }

    /**cuando acierta dos seguidas INDEPENDIENTES se le suman 15 puntos mas y si falla dos seguidas
     *INDEPENDIENTES se le restan 5 puntos mas*/

    void calculateAndSetScore(){
        int res = getScore();

        res += toAdd;
        if (!isCorrect()) {
            if (!lastCorrect && successesFollowed < 0) {
                res += -5;
                successesFollowed = 0;
            } else {
                successesFollowed = -1;
            }
            lastCorrect = false;

        } else {

            if (lastCorrect && successesFollowed > 0) {
                res += 15;
                successesFollowed = 0;
            } else {
                successesFollowed = 1;
            }
            lastCorrect = true;
        }

        setScore(res);
    }
}
