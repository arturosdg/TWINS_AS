package es.imposoft.twins.plantilla;

public class ScoreFree extends AbstractScore {

    private int score;
    private boolean correct;

    public ScoreFree() {
        super();
    }

    void assignPoints() {
        if (isCorrect()) {
            toAdd = 15; //si acierta
        } else {
            toAdd = -5; //si falla
        }
    }

    void calculateAndSetScore(){
        int res = getScore();
        res += toAdd;
        setScore(res);
    }
}
