package es.imposoft.twins.singleton;

import android.content.Context;
import android.media.MediaPlayer;

import es.imposoft.twins.R;

public class MusicService {
    private static MusicService instance;
    private Context context;
    private MediaPlayer musicPlayer;
    private MediaPlayer soundPlayer;
    private boolean isEnabled;
    private int lastSong;

    private MusicService(Context context) {
        this.context = context;
        musicPlayer = MediaPlayer.create(context, R.raw.menusong);
        soundPlayer = MediaPlayer.create(context, R.raw.clocksound);
        isEnabled = true;
    }

    public static MusicService getInstance(Context context) {
        if (instance == null) {
            instance = new MusicService(context);
        }
        return instance;
    }

    public void stopMusic() {
        if (musicPlayer.isPlaying()) musicPlayer.stop();
    }

    public void stopExtraSound(){if (soundPlayer.isPlaying()) soundPlayer.stop();}

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

    //Plays the game music
    public void startGameMusic(int newSong) {
        if(!musicPlayer.isPlaying() & isEnabled) {
            lastSong = newSong;
            musicPlayer = MediaPlayer.create(context, newSong);
            musicPlayer.setLooping(true);
            musicPlayer.setVolume(35, 35);
            musicPlayer.start();
        }
    }

    //Plays a sound on top of the music
    public void startExtraSound(int testUri){
        if(!soundPlayer.isPlaying() & isEnabled) {
            soundPlayer = MediaPlayer.create(context, testUri);
            soundPlayer.setLooping(false);
            soundPlayer.setVolume(75, 75);
            soundPlayer.start();
        }
    }
}
