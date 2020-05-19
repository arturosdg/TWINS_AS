package es.imposoft.twins.card;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;

import es.imposoft.twins.R;

public class ConcreteCard implements Card {

    Bitmap frontImage, backImage;
    Button cardButton;
    boolean visible, isPaired, special;
    Context contextScene;
    int points;
    private String frontName;


    public ConcreteCard(Button button, Context context) {
        contextScene = context;
        Bitmap bitBack = BitmapFactory.decodeResource(contextScene.getResources(), R.drawable.background2),
                bitFront = BitmapFactory.decodeResource(contextScene.getResources(), R.drawable.cars0);
        backImage = bitBack;
        frontImage = bitFront;
        cardButton = button;
        visible = false;
        isPaired = false;
        special = false;

        points = 10;
    }

    public Bitmap getFrontImage() {
        return frontImage;
    }

    public void setFrontName(String name){
        this.frontName = name;
    }

    public String getFrontName(){
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
        if (!visible) {
            cardButton.setBackground(new BitmapDrawable(contextScene.getResources(), frontImage));
        } else {
            cardButton.setBackground(new BitmapDrawable(contextScene.getResources(), backImage));
        }
        visible = !visible;
    }

    public void turnVisibleCards() {
        if (visible && !isPaired) {
            cardButton.setBackground(new BitmapDrawable(contextScene.getResources(), backImage));
            visible = !visible;
        }
    }

    public boolean equals(Card concreteCard) {
        System.out.println(this.getFrontName() + " a " + concreteCard.getFrontName());
        return this.getFrontName().equals(concreteCard.getFrontName());
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public boolean isSpecial() {
        return special;
    }
}
