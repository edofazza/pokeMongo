package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import com.google.common.annotations.VisibleForTesting;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import java.io.File;

/**
 * Class dedicated to handle the music in the scene.
 */
public class MusicPlayer {
    private final String musicLocation = "music/";
    private AudioClip mediaPlayer;

    /**
     * Simply call <code>setSong(String title)</code>.
     * @param title contains the filename of the mp3 to load
     */
    @VisibleForTesting
    public MusicPlayer(String title) {
        setSong(title);
    }

    /**
     * Stop the music and then call <code>setSong(String title)</code>, in order to change the music.
     * @param title contains the filename of the mp3 to load
     */
    @VisibleForTesting
    protected void changeSong(String title) {

        Logger.vlog("CHANGING SONG");
        mediaPlayer.stop();     // in order to be garbage collected
        setSong(title);
    }

    /**
     * Create a new <code>Media</code> and attribute it to <code>mediaPlayer</code>. It also sets the
     * <code>mediaPlayer</code> cycle count to indefinite, and plays it.
     * @param title contains the filename of the mp3 to load
     */
    @VisibleForTesting
    private void setSong(String title) {
        Logger.vlog("Setting song: " + title);
        Media sound = new Media(new File(musicLocation + title).toURI().toString());
        mediaPlayer = new AudioClip(sound.getSource());
        mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
        mediaPlayer.play();
    }

    /**
     * Stop the <code>mediaPlayer</code>.
     */
    @VisibleForTesting
    public void stopMusic() {
        Logger.vlog("Stopping song");
        mediaPlayer.stop();
    }
}
