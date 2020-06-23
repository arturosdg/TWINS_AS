package es.imposoft.twins;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import es.imposoft.twins.logic.singleton.MusicService;

import static org.junit.Assert.*;

public class MusicTest {
    @Before
    public void restartMusicService(){
        //Arrange
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MusicService musicService = MusicService.getInstance(appContext);

        musicService.stopMusic();
        musicService.stopExtraSound();
    }

    @Test
    public void testMusicLoads() {
        //Arrange
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MusicService musicService = MusicService.getInstance(appContext);

        //Act
        musicService.startGameMusic(R.raw.menusong);

        //Assert
        assertTrue("Song loads correctly", musicService.getMusicPlayer().isPlaying());
    }

    @Test
    public void testSoundLoads() {
        //Arrange
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MusicService musicService = MusicService.getInstance(appContext);

        //Act
        musicService.startExtraSound(R.raw.menusong);

        //Assert
        assertTrue("Sound loads correctly", musicService.getSoundPlayer().isPlaying());
    }

    @Test
    public void testMusicDisabledDoesntLoad() {
        //Arrange
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MusicService musicService = MusicService.getInstance(appContext);

        //Act
        musicService.disableMusic();
        musicService.startGameMusic(R.raw.menusong);

        //Assert
        assertFalse("Song loads but it shouldn't", musicService.getMusicPlayer().isPlaying());
    }

    @Test
    public void testSoundDisabledDoesntLoad() {
        //Arrange
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MusicService musicService = MusicService.getInstance(appContext);

        //Act
        musicService.disableMusic();
        musicService.startExtraSound(R.raw.menusong);

        //Assert
        assertFalse("Song loads but it shouldn't", musicService.getSoundPlayer().isPlaying());
    }

    @Test
    public void testDisablingAndEnablingMusicWorks() {
        //Arrange
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MusicService musicService = MusicService.getInstance(appContext);

        //Act
        musicService.disableMusic();
        musicService.enableMusic();
        musicService.startGameMusic(R.raw.menusong);

        //Assert
        assertTrue("Song doesn't load and it should", musicService.getMusicPlayer().isPlaying());
    }
}
