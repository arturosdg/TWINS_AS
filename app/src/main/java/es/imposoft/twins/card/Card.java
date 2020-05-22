package es.imposoft.twins.card;

import android.graphics.Bitmap;
import android.widget.Button;

public interface Card {
    Bitmap getFrontImage();
    void setFrontImage(Bitmap frontImage);
    void setBackImage(Bitmap backImage);
    Button getCardButton();
    void setFrontName(int name);
    int getFrontName();
    void setPaired();
    void turnCard();
    void turnVisibleCards();
    boolean equals(Card concreteCard);
    int getPoints();
    void setPoints(int points);
    boolean isSpecial();
    void setSpecial(boolean special);
}
