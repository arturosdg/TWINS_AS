package es.imposoft.twins.card;

public class ConcreteCardDecorator extends CardDecorator {

    public ConcreteCardDecorator(Card card) {
        super(card);
    }

    @Override
    public void setPoints(int points) {
        super.setPoints(setPointsExtended(points));
    }
    private int setPointsExtended(int points) {
        return points/2 + 5;
    }
}
