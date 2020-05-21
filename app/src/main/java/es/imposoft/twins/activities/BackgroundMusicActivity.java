package es.imposoft.twins.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import es.imposoft.twins.singleton.MusicService;

public class BackgroundMusicActivity extends AppCompatActivity {
    public MusicService musicService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getApplicationContext();
        startSingleton(context);
    }

    public void startSingleton(Context context){
        musicService = MusicService.getInstance(context);
    }
}
