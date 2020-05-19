package es.imposoft.twins.card;

import android.graphics.Bitmap;
import android.widget.Button;

public class CardDecorator implements Card {

    private Card card;
    private int frontName;

    public CardDecorator(Card card) { this.card = card; }

    public Bitmap getFrontImage() {
        return card.getFrontImage();
    }

    public void setFrontImage(Bitmap frontImage) {
        card.setFrontImage(frontImage);
    }

    public void setBackImage(Bitmap backImage) {
        card.setBackImage(backImage);
    }

    public Button getCardButton() {
        return card.getCardButton();
    }

    @Override
    public void setFrontName(int name) {
        this.frontName = name;
    }

    @Override
    public int getFrontName() {
        return frontName;
    }

    public void setPaired() {
        card.setPaired();
    }

    public void turnCard() {
        card.turnCard();
    }

    public void turnVisibleCards() {
        card.turnVisibleCards();
    }

    public boolean equals(Card concreteCard) {
        System.out.println(this.getFrontName() + " a " + concreteCard.getFrontName());
        return this.getFrontName() == concreteCard.getFrontName();
    }

    public int getPoints() {
        return card.getPoints();
    }

    @Override
    public void setPoints(int points) {
        card.setPoints(points);
    }

    @Override
    public boolean isSpecial() {
        return card.isSpecial();
    }

    @Override
    public void setSpecial(boolean special) {
        card.setSpecial(special);
    }
}
