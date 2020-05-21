package es.imposoft.twins.singleton;

import android.content.Context;
import android.media.MediaPlayer;

import es.imposoft.twins.R;

public class MusicService {
    private static MusicService instance;
    public Context context;
    public MediaPlayer player;
    public MediaPlayer auxPlayer;
    public boolean isEnabled;
    public int lastSong;

    private MusicService(Context context) {
        this.context = context;
        player = MediaPlayer.create(context, R.raw.menusong);
        auxPlayer = MediaPlayer.create(context, R.raw.clocksound);
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

    public void stopExtraSound(){if (auxPlayer.isPlaying()) auxPlayer.stop();}

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
        if(!auxPlayer.isPlaying() & isEnabled) {
            auxPlayer = MediaPlayer.create(context, testUri);
            auxPlayer.setLooping(false);
            auxPlayer.setVolume(20, 20);
            auxPlayer.start();
        }
    }
}
