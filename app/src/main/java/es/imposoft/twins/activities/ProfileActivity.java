package es.imposoft.twins.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import es.imposoft.twins.R;
import es.imposoft.twins.database.Scoreboard;
import es.imposoft.twins.database.SucceededChallenge;
import es.imposoft.twins.singleton.MusicService;

public class ProfileActivity extends AppCompatActivity {

    Context context;
    MusicService musicService;
    Intent intent;
    GoogleSignInClient signInClient;
    GoogleSignInAccount signedInAccount;
    GoogleSignInOptions signInOptions;

    SucceededChallenge challenges;
    Scoreboard scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);

        //Launch the music service
        Intent intent = new Intent(this, BackgroundMusicActivity.class);
        startService(intent);

        //Start the music
        musicService = MusicService.getInstance(getApplicationContext());
        musicService.startGameMusic(R.raw.menusong);

        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        signInClient = GoogleSignIn.getClient(this, signInOptions);
        signedInAccount = GoogleSignIn.getLastSignedInAccount(this);

        //Set the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        challenges = new SucceededChallenge(signedInAccount.getEmail());
        scores = new Scoreboard();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        challenges.loadChallenges(sharedPreferences);
        int challengesSucceeded = challenges.getChallenges();

        //Initialize the user parametres
        ImageView userProfileImage = findViewById(R.id.userProfilePic);
        Glide.with(this).load(signedInAccount.getPhotoUrl()).into(userProfileImage);
        TextView userName = findViewById(R.id.textview_userName);
        TextView userEmail = findViewById(R.id.userEmail);
        TextView challenges = findViewById(R.id.challenges);
        TextView score = findViewById(R.id.score);
        userName.setText(signedInAccount.getDisplayName());
        userEmail.setText(signedInAccount.getEmail());
        challenges.setText(String.valueOf(challengesSucceeded));
        score.setText(String.valueOf(scores.getTotalScore()));
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
