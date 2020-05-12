package es.imposoft.twins.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.IOException;

import es.imposoft.twins.components.Deck;
import es.imposoft.twins.R;

public class MainActivity extends AppCompatActivity {
    Context context;
    Deck cardTheme;
    MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);

            song = MediaPlayer.create(MainActivity.this, R.raw.twinscancion);




        cardTheme = Deck.EMOJI;

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_startgame);
        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.background),size.x,size.y,true);
        /* fill the background ImageView with the resized image */
        ImageView iv_background = (ImageView) findViewById(R.id.iv_background);
        iv_background.setImageBitmap(bmp);



    }

    public void playByGameModes(View view) {
        Intent intent = new Intent(this, SelectionGameModeActivity.class);
        intent.putExtra("THEME", cardTheme);
        startActivity(intent);
        this.finish();
    }

    public void onOptionsPressed(View view){
        Intent intent = new Intent(MainActivity.this, PopupActivity.class);
        intent.putExtra("TYPE", PopupActivity.WindowType.OPTIONS);
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Method executed from the popup window

        if (data != null) {
            switch ((Integer) data.getExtras().get("WINDOW")) {
                case 2:
                    //Called from the options menu
                    if (resultCode == RESULT_OK) {
                        Bundle returnInfo = data.getExtras();
                        int chosenCard = -1;
                        if (returnInfo.containsKey("CARD")) {
                            chosenCard = (Integer) returnInfo.get("CARD");
                        }
                        switch (chosenCard){
                            case 1:
                                cardTheme = Deck.EMOJI;
                                break;
                            case 2:
                                cardTheme = Deck.CARS;
                                break;
                        }

                        boolean chosenVolume = true;
                        if (returnInfo.containsKey("SOUND")) {
                            chosenVolume = (Boolean) returnInfo.get("SOUND");
                        }
                        if (chosenVolume) {
                            System.out.println("Test true");

                            if(!song.isPlaying()) song.start();
                        } else if (!chosenVolume) {
                            System.out.println("Test false");
                            song.release();
                        }
                    }
            }
        }
    }
}
