package es.imposoft.twins.card;

public class SpecialCardDecorator2 extends CardDecorator {
    public SpecialCardDecorator2(Card card) {
        super(card);
    }

    @Override
    public int getPoints() {
        return setPointsExtended(super.getPoints());
    }
    private int setPointsExtended(int points) {
        return points + 5;
    }
}
