package es.imposoft.twins.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.List;

import es.imposoft.twins.SucceededLevel;
import es.imposoft.twins.components.Deck;
import es.imposoft.twins.R;
import es.imposoft.twins.builders.ConcreteBuilderLevel;
import es.imposoft.twins.components.GameMode;
import es.imposoft.twins.director.Director;
import es.imposoft.twins.gametypes.Game;

public class SelectLevelActivity extends AppCompatActivity {
    private final int MAX_LEVELS = 5;
    int level;
    private Button[] levelButtons = new Button[MAX_LEVELS];
    Context context;

    Deck deck;
    private Director director;
    private ConcreteBuilderLevel levelBuilder;
    Game game;

    SucceededLevel succeededLevels;
    List<Integer> levels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);

        Bundle windowInfo = getIntent().getExtras();

        deck = (Deck) windowInfo.get("THEME");

        director = new Director(deck);
        levelBuilder =  new ConcreteBuilderLevel();


        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_selectlevel);
        /* adapt the image to the size of the display */
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                getResources(),R.drawable.backgroundlevels),size.x,size.y,true);
        /* fill the background ImageView with the resized image */
        ImageView iv_background = (ImageView) findViewById(R.id.iv_background);
        iv_background.setImageBitmap(bmp);


        succeededLevels = new SucceededLevel(GameMode.LEVELS.ordinal());
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        succeededLevels.loadSuccedeedLevels(sp);
        if(succeededLevels != null) levels = succeededLevels.getSuccedeedLevels();
        if(succeededLevels != null) succeededLevels.saveSucceededLevels(sp);

        fillArray();
        initializeLevelButtons();
        setPlayableLevels();
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, SelectionGameModeActivity.class);
        intent.putExtra("THEME", deck);
        startActivity(intent);
        this.finish();
    }

    public void goToLevel(View view) {
        Intent intent = new Intent(this, GameActivity.class);

        createLevelSelected(view.getId());
        game = levelBuilder.getResult();

        Gson gson = new Gson();
        String newGame = gson.toJson(game);
        intent.putExtra("GAME",newGame);
        intent.putExtra("LEVEL", level);
        String glevels = gson.toJson(succeededLevels);

        startActivity(intent);
        this.finish();

    }

    private void fillArray(){
        int levelButton = 0;
        for(int i = 0; i < MAX_LEVELS; i++) {
            levelButton = getResources().getIdentifier("button_level" + (i+1),"id", getPackageName());
            levelButtons[i] = findViewById(levelButton);
        }
    }

    private void createLevelSelected(int id) {
        if(id == levelButtons[0].getId()) { director.constructLevel1(levelBuilder); level = 1; }
        else if (id == levelButtons[1].getId()) { director.constructLevel2(levelBuilder); level = 2; }
        else if (id == levelButtons[2].getId()) { director.constructLevel3(levelBuilder); level = 3; }
        else if (id == levelButtons[3].getId()) { director.constructLevel3(levelBuilder); level = 4; }
        else  { director.constructLevel3(levelBuilder); level = 5; }
    }

    private void setPlayableLevels() {
        if(levels != null) {
            for (int i = 0; i <= levels.size(); i++) {
                levelButtons[ i ].setClickable(true);
            }
            for (Integer i : levels) {
                levelButtons[ i - 1 ].setClickable(true);
                levelButtons[ i - 1 ].setBackground(getDrawable(R.drawable.emoji8));
            }
        }

    }

    private void initializeLevelButtons() {
        for (int i = 1; i < levelButtons.length; i++)
            levelButtons[i].setClickable(false);
    }
}
