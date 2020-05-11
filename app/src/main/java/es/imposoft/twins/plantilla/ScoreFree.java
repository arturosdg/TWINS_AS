package es.imposoft.twins.plantilla;

public class ScoreFree extends AbstractScore {

    public ScoreFree() { super(); }

    public int updateScore(boolean correct) {
        setCorrect(correct);
        assignPoints();
        calculateAndSetScore();
        if(score<0){
            score = 0;
        }
        return score;
    }

    void assignPoints() {
        if (isCorrect()) {
            toAdd = 15; //si acierta
        } else {
            toAdd = -5; //si falla
        }
    }

    void calculateAndSetScore(){
        score = getScore();
        score += toAdd;
        setScore(score);
    }
}
