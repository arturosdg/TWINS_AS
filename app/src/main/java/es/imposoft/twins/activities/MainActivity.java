package es.imposoft.twins.activities;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
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

    @SuppressLint("WrongViewCast")
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

        signInButton = findViewById(R.id.sign_in_button);

        if (signInClient == null) { findViewById(R.id.sign_out_button).setVisibility(View.GONE); }
        else {
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        }


        //signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).requestEmail().build();
        signInClient = GoogleSignIn.getClient(this, signInOptions);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startSignInIntent();
            }
        });
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
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            //GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            /*assert result != null;
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
            }*/
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

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            signedInAccount = task.getResult(ApiException.class);
            Toast.makeText(this, "Bienvenido " + signedInAccount.getDisplayName(), Toast.LENGTH_SHORT).show();
        } catch (ApiException e) {
            new AlertDialog.Builder(this).setMessage(getString(R.string.signin_other_error))
                    .setNeutralButton(android.R.string.ok, null).show();
        }
    }

    private void startSignInIntent() {
        intent = signInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    public void signOut(View view) {
        signInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // at this point, the user is signed out.
                    }
                });
        findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
        findViewById(R.id.sign_out_button).setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signedInAccount != null) {
            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        }
        super.onStart();
    }
}
