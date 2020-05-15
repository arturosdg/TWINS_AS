package es.imposoft.twins.Card;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;

public class CardDecorator implements Card {

    private Card card;

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

    public void setPaired() {
        card.setPaired();
    }

    public void turnCard() {
        card.turnCard();
    }

    public void turnVisibleCards() {
        card.turnVisibleCards();
    }

    public boolean equals(ConcreteCard concreteCard) {
        return card.equals(concreteCard);
    }

    public int getPoints() {
        return card.getPoints();
    }

    @Override
    public void setPoints(int points) {
        card.setPoints(points);
    }
}
