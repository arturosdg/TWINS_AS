package es.imposoft.twins.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import es.imposoft.twins.R;
import es.imposoft.twins.components.DeckTheme;
import es.imposoft.twins.singleton.MusicService;

public class ProfileActivity extends AppCompatActivity {

    Context context;
    MusicService ms;
    Intent intent;
    GoogleSignInClient signInClient;
    GoogleSignInAccount signedInAccount;
    GoogleSignInOptions signInOptions;

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

        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        signInClient = GoogleSignIn.getClient(this, signInOptions);
        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);

        //Set the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        //Initialize the user parametres
        ImageView userProfileImage = findViewById(R.id.userProfilePic);
        Glide.with(this).load(signedInAccount.getPhotoUrl()).into(userProfileImage);
        TextView userName = findViewById(R.id.userName);
        TextView userEmail = findViewById(R.id.userEmail);
        userName.setText(signedInAccount.getDisplayName());
        userEmail.setText(signedInAccount.getEmail());
    }

    public void goBack(View view) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void logOut(View view) {
        signInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // at this point, the user is signed out.

                    }
                });
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
