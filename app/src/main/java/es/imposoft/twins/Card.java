package es.imposoft.twins;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Button;

public class Card {
    public Bitmap frontImage;
    public Bitmap backImage;
    public Button cardButton;
    public boolean turned;
    public Context contextScene;

    public Card(Button button, Context context){
        contextScene = context;
        Bitmap bitBack = BitmapFactory.decodeResource(contextScene.getResources(), R.drawable.unknown);
        Bitmap bitFront = BitmapFactory.decodeResource(contextScene.getResources(), R.drawable.boo);
        backImage = bitBack;
        frontImage = bitFront;
        cardButton = button;
        turned = false;
    }

    public Bitmap getFrontImage(){
        return frontImage;
    }

    public Drawable getBackImage(){
        Drawable drawImageBack = new BitmapDrawable(contextScene.getResources(), backImage);
        return drawImageBack;
    }

    public boolean isTurned(){
        return turned;
    }

    public void turnCard(){ turned = !turned;}
}
