package es.imposoft.twins.card;

public class SpecialCardDecorator3 extends CardDecorator {
    public SpecialCardDecorator3(Card card) {
        super(card);
    }

    @Override
    public int getPoints() {
        return setPointsExtended(super.getPoints());
    }
    private int setPointsExtended(int points) {
        return points + 7;
    }
}

