package es.imposoft.twins;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Button;

public class Card {

    public Bitmap frontImage, backImage;
    public Button cardButton;
    public boolean visible;
    public Context contextScene;


    public Card(Button button, Context context) {
        contextScene = context;
        Bitmap bitBack = BitmapFactory.decodeResource(contextScene.getResources(), R.drawable.unknown),
               bitFront = BitmapFactory.decodeResource(contextScene.getResources(), R.drawable.boo);
        backImage = bitBack;
        frontImage = bitFront;
        cardButton = button;
        visible = false;
    }

    public Drawable getBackImage() {
        return new BitmapDrawable(contextScene.getResources(),backImage);
    }
    public Drawable getFrontImage() {
        return new BitmapDrawable(contextScene.getResources(),frontImage);
    }

    public void setFrontImage(Bitmap frontImage) {
        this.frontImage = frontImage;
    }

    public Button getCardButton() {
        return cardButton;
    }

    public boolean isVisible() { return visible; }

    public void turnCard() {
        if (!visible) {
            cardButton.setBackground(new BitmapDrawable(contextScene.getResources(),frontImage));
        } else {
            cardButton.setBackground(new BitmapDrawable(contextScene.getResources(),backImage));
        }
        visible = !visible;
    }

}
