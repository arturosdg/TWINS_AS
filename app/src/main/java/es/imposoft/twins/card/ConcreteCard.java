package es.imposoft.twins.card;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;

import es.imposoft.twins.R;

public class ConcreteCard implements Card {

    private Bitmap frontImage, backImage;
    private Button cardButton;
    private boolean isVisible, isPaired, isSpecial;
    private Context contextScene;
    private int points;
    private int frontName;

    public ConcreteCard() {
        isVisible = false;
        isPaired = false;
        isSpecial = false;
        points = 2;
    }

    public ConcreteCard(Button button, Context context) {
        contextScene = context;
        backImage = BitmapFactory.decodeResource(contextScene.getResources(), R.drawable.background2);
        frontImage = BitmapFactory.decodeResource(contextScene.getResources(), R.drawable.cars0);
        cardButton = button;
        isVisible = false;
        isPaired = false;
        isSpecial = false;
        points = 2;
    }

    public Bitmap getFrontImage() { return frontImage; }

    public void setFrontName(int name){
        this.frontName = name;
    }

    public int getFrontName(){
        return frontName;
    }

    public void setFrontImage(Bitmap frontImage) {
        this.frontImage = frontImage;
    }

    public void setBackImage(Bitmap backImage) {
        this.backImage = backImage;
    }

    public Button getCardButton() {
        return cardButton;
    }

    public void setPaired() {
        isPaired = true;
        cardButton.setClickable(false);
    }

    public void turnCard() {
        if (!isVisible) {
            cardButton.setBackground(new BitmapDrawable(contextScene.getResources(), frontImage));
        } else {
            cardButton.setBackground(new BitmapDrawable(contextScene.getResources(), backImage));
        }
        isVisible = !isVisible;
    }

    public void turnVisibleCards() {
        if (isVisible && !isPaired) {
            cardButton.setBackground(new BitmapDrawable(contextScene.getResources(), backImage));
            isVisible = !isVisible;
        }
    }

    public boolean equals(Card concreteCard) {
        return this.getFrontName() == concreteCard.getFrontName();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setSpecial(boolean special) {
        this.isSpecial = special;
    }

    public boolean isSpecial() {
        return isSpecial;
    }
}
