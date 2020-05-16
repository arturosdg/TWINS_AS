package es.imposoft.twins;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

public class MusicService {
    private static MusicService instance;
    public Context context;
    public MediaPlayer player;
    public MediaPlayer auxPlayer;
    public boolean isEnabled;
    public int lastSong;

    private MusicService(Context context) {
        this.context = context;
        player = MediaPlayer.create(context, R.raw.twinscancion);
        isEnabled = true;
    }

    public static MusicService getInstance(Context context) {
        if (instance == null) {
            instance = new MusicService(context);
        }
        return instance;
    }

    public void stopMusic() {
        if (player.isPlaying()) player.stop();
    }

    public void disableMusic(){
        if(isEnabled){
            isEnabled = false;
            stopMusic();
        }
    }

    public void enableMusic(){
        if(!isEnabled){
            isEnabled = true;
            startGameMusic(lastSong);
        }
    }

    public void startGameMusic(int musicLink) {
        if(!player.isPlaying() & isEnabled) {
            lastSong = musicLink;
            player = MediaPlayer.create(context, musicLink);
            player.setLooping(true); // Set looping
            player.setVolume(50, 50);
            player.start();
        }
    }

    public void startExtraSound(int testUri){
        auxPlayer = MediaPlayer.create(context,testUri);
        auxPlayer.setLooping(false);
        auxPlayer.setVolume(50, 50);
        auxPlayer.start();
    }
}
