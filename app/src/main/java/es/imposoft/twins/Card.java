package es.imposoft.twins;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;

public class Card {

    public Bitmap frontImage, backImage;
    public Button cardButton;
    public boolean visible, isPaired;
    public Context contextScene;


    public Card(Button button, Context context) {
        contextScene = context;

        Bitmap bitBack = BitmapFactory.decodeResource(contextScene.getResources(), R.drawable.background2),
               bitFront = BitmapFactory.decodeResource(contextScene.getResources(), R.drawable.cars0);
        backImage = bitBack;
        frontImage = bitFront;

        cardButton = button;
        visible = false;
        isPaired = false;
    }

    public Bitmap getBackImage() { return backImage; }

    public Bitmap getFrontImage() { return frontImage; }

    public void setFrontImage(Bitmap frontImage) {
        this.frontImage = frontImage;
    }

    public void setBackImage(Bitmap backImage) {
        this.backImage = backImage;
    }

    public Button getCardButton() {
        return cardButton;
    }

    public boolean isVisible() { return visible; }

    public boolean getPaired() { return isPaired; }

    public void setPaired() {
        isPaired = true;
        cardButton.setClickable(false);
    }

    public void turnCard() {
        if (!visible) {
            cardButton.setBackground(new BitmapDrawable(contextScene.getResources(),frontImage));
        } else {
            cardButton.setBackground(new BitmapDrawable(contextScene.getResources(),backImage));
        }
        visible = !visible;
    }
    public void turnVisibleCards() {
        if(visible && !isPaired)  {
            cardButton.setBackground(new BitmapDrawable(contextScene.getResources(),backImage));
            visible = !visible;
        }
    }

    public boolean equals(Card card) {
        return this.getFrontImage().sameAs(card.getFrontImage());
    }


}
