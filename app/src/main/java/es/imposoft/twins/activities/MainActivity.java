package es.imposoft.twins.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
<<<<<<< Updated upstream
=======
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;

>>>>>>> Stashed changes
import es.imposoft.twins.components.Deck;
import es.imposoft.twins.R;

public class MainActivity extends AppCompatActivity {
    Context context;
    Deck cardTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
<<<<<<< Updated upstream
=======
        //setContentView(R.layout.activity_startgame);
        cardTheme = Deck.EMOJI;



        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
>>>>>>> Stashed changes
        setContentView(R.layout.activity_startgame);
        cardTheme = Deck.EMOJI;
    }

    public void playByGameModes(View view) {
        Intent intent = new Intent(this, SelectionGameModeActivity.class);
        intent.putExtra("THEME", cardTheme);
        if(getIntent().getIntegerArrayListExtra("LEVEL") != null)
            intent.putIntegerArrayListExtra("LEVEL", getIntent().getIntegerArrayListExtra("LEVEL"));
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
                    }
            }
        }
    }
}
