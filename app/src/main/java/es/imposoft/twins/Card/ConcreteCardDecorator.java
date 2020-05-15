package es.imposoft.twins.Card;

public class ConcreteCardDecorator extends CardDecorator {

    public ConcreteCardDecorator(Card card) {
        super(card);
    }

    @Override
    public void setPoints(int points) {
        super.setPoints(points);
    }
}
