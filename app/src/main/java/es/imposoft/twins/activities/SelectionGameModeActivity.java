package es.imposoft.twins.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.R;
import es.imposoft.twins.builders.ConcreteBuilderLevel;
import es.imposoft.twins.director.Director;
import es.imposoft.twins.gametypes.Game;
import es.imposoft.twins.singleton.MusicService;


public class SelectionGameModeActivity extends AppCompatActivity {
    Context context;
    DeckTheme deckTheme;
    Game game;
    Bundle windowInfo;
    private Director director;
    private ConcreteBuilderLevel levelBuilder;
    private MusicService musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_selectgamemode);

        windowInfo = getIntent().getExtras();
        deckTheme = (DeckTheme) windowInfo.get("THEME");
        director = new Director(deckTheme);
        levelBuilder = new ConcreteBuilderLevel();

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_selectgamemode);

        musicService = MusicService.getInstance(getApplicationContext());
        musicService.startGameMusic(R.raw.menusong);
    }

    public static void fixBackgroundRepeat(View view) {
        Drawable background = view.getBackground();
        if (background != null) {
            if (background instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) background;
                bitmapDrawable.mutate(); // make sure that we aren't sharing state anymore
                bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            }
        }
    }


    public void openLevelsLayout(View view) {
        Intent intent = new Intent(this, SelectLevelActivity.class);
        intent.putExtra("THEME", deckTheme);
        startActivity(intent);
        this.finish();
    }

    public void openChallengeLayout(View view) {
        Intent intent = new Intent(this, SelectChallengeActivity.class);
        intent.putExtra("THEME", deckTheme);
        startActivity(intent);
        this.finish();
    }

    public void playCasualGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        director.constructCasualGame(levelBuilder);
        game = levelBuilder.getResult();

        Gson gson = new Gson();
        String newGame = gson.toJson(game);
        intent.putExtra("GAME", newGame);

        startActivity(intent);
        this.finish();
    }

    public void playStandardGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        director.constructStandardGame(levelBuilder);
        game = levelBuilder.getResult();

        Gson gson = new Gson();
        String newGame = gson.toJson(game);
        intent.putExtra("GAME", newGame);

        startActivity(intent);
        this.finish();
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("THEME", deckTheme);
        startActivity(intent);
        this.finish();
    }

}
