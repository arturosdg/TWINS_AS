package es.imposoft.twins.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import es.imposoft.twins.singleton.MusicService;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.R;

public class MainActivity extends AppCompatActivity {
    Context context;
    DeckTheme cardTheme;
    MusicService ms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Basic Android stuff
        context = getApplicationContext();
        super.onCreate(savedInstanceState);

        //Launch the music service
        Intent intent = new Intent(this, BackgroundMusicActivity.class);
        startService(intent);

        //Start the music
        ms = MusicService.getInstance(getApplicationContext());
        ms.startGameMusic(R.raw.menusong);

        //Set default card theme
        cardTheme = DeckTheme.EMOJI;

        //Set the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_startgame);
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
                        if (returnInfo.containsKey("CARD"))
                            chosenCard = (Integer) returnInfo.get("CARD");
                        switch (chosenCard){
                            case 1:
                                cardTheme = DeckTheme.EMOJI;
                                break;
                            case 2:
                                cardTheme = DeckTheme.CARS;
                                break;
                        }
                        int chosenVolume = -1;
                        if (returnInfo.containsKey("SOUND"))
                            chosenVolume = (int) returnInfo.get("SOUND");
                        switch (chosenVolume){
                            case 1:
                                ms.enableMusic();
                                break;
                            case 2:
                                ms.disableMusic();
                                break;
                        }
                    }
            }
        }
    }
}
