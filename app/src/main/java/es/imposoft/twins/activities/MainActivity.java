package es.imposoft.twins.activities;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import es.imposoft.twins.singleton.MusicService;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.R;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;
    Context context;
    DeckTheme cardTheme;
    MusicService ms;
    GoogleSignInOptions signInOptions;
    GoogleSignInClient signInClient;
    GoogleSignInAccount signedInAccount;
    SignInButton signInButton;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Basic Android stuff
        context = getApplicationContext();
        super.onCreate(savedInstanceState);

         signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;

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

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);

        if (signInClient == null) { findViewById(R.id.sign_out_button).setVisibility(View.GONE); }
        else {
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        }
    }

    public void playByGameModes(View view) {
        intent = new Intent(this, SelectionGameModeActivity.class);
        intent.putExtra("THEME", cardTheme);
        startActivity(intent);
        this.finish();
    }

    public void onOptionsPressed(View view){
        intent = new Intent(MainActivity.this, PopupActivity.class);
        intent.putExtra("TYPE", PopupActivity.WindowType.OPTIONS);
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Method executed from the popup window

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // The signed in account is stored in the result.
                signedInAccount = result.getSignInAccount();
            } else {
                String message = result.getStatus().getStatusMessage();
                if (message == null || message.isEmpty()) {
                    message = getString(R.string.signin_other_error);
                }
                new AlertDialog.Builder(this).setMessage(message)
                        .setNeutralButton(android.R.string.ok, null).show();
            }
        }
        else {
            if (data != null) {
                switch ((Integer) data.getExtras().get("WINDOW")) {
                    case 2:
                        //Called from the options menu
                        if (resultCode == RESULT_OK) {
                            Bundle returnInfo = data.getExtras();
                            int chosenCard = -1;
                            if (returnInfo.containsKey("CARD"))
                                chosenCard = (Integer) returnInfo.get("CARD");
                            switch (chosenCard) {
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
                            switch (chosenVolume) {
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

    private void startSignInIntent() {
        signInClient = GoogleSignIn.getClient(this,
                signInOptions);
        intent = signInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    private void signOut() {
        signInClient = GoogleSignIn.getClient(this,
                signInOptions);
        signInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // at this point, the user is signed out.
                    }
                });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.sign_in_button) {
            // start the asynchronous sign in flow
            startSignInIntent();
            //if(signInClient != null) findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        } else if (view.getId() == R.id.sign_out_button) {
            // sign out.
            signOut();
            // show sign-in button, hide the sign-out button
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
        }
    }
}
