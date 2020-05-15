package es.imposoft.twins;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicService {
    private static MusicService instance;
    public String value;
    public Context context;
    public MediaPlayer player;

    private MusicService(Context context) {
        this.context = context;
    }

    public static MusicService getInstance(Context context) {
        if (instance == null) {
            instance = new MusicService(context);
        }
        return instance;
    }

    public void startMusic(){
        player = MediaPlayer.create(context, R.raw.twinscancion);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        player.start();
    }

    public void stopMusic(){
        if(player.isPlaying()) {
            player.stop();
        }
    }
}
