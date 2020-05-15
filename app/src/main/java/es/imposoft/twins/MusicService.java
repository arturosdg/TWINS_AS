package es.imposoft.twins;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicService {
    private static MusicService instance;
    public String value;
    public Context context;
    public MediaPlayer player;
    public boolean isEnabled;

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

    public void startMusic() {
        if(!player.isPlaying() & isEnabled) {
            player.setLooping(true); // Set looping
            player.setVolume(100, 100);
            player.start();
        }
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
            //TODO restore last song
            player = MediaPlayer.create(context, R.raw.twinscancion);
            startMusic();
        }
    }

    public void startGameMusic() {
        if(!player.isPlaying() & isEnabled) {
            player = MediaPlayer.create(context, R.raw.is_alive_rap);
            player.setLooping(true); // Set looping
            player.setVolume(50, 50);
            player.start();
        }
    }

    public void startCountdownMusci(){
        if(!player.isPlaying() & isEnabled) {
            //TODO put countdown clock music
            player = MediaPlayer.create(context, R.raw.is_alive_rap);
            player.setLooping(true); // Set looping
            player.setVolume(50, 50);
            player.start();
        }
    }

    public void startMatchedSound(){
        if(!player.isPlaying() & isEnabled) {
            //TODO put matched card sound
            player = MediaPlayer.create(context, R.raw.is_alive_rap);
            player.setLooping(true); // Set looping
            player.setVolume(50, 50);
            player.start();
        }
    }
}
