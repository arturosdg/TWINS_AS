package es.imposoft.twins.card;

public class SpecialCardDecorator extends CardDecorator {
    public SpecialCardDecorator(Card card) {
        super(card);
    }

    @Override
    public void setPoints(int points) {
        super.setPoints(setPointsExtended(points));
    }
    private int setPointsExtended(int points) {
        return points * 2;
    }
}
