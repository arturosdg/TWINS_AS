package es.imposoft.twins.Card;

public class SpecialCardDecorator extends CardDecorator {
    public SpecialCardDecorator(Card card) {
        super(card);
    }

    @Override
    public void setPoints(int points) {
        super.setPoints(points);
    }
}
