package es.imposoft.twins.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

import es.imposoft.twins.MusicService;

public class BackgroundMusicActivity extends AppCompatActivity {
    private static BackgroundMusicActivity instance;

    private static WeakReference<Context> sContextReference;
    MusicService musicService;

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
