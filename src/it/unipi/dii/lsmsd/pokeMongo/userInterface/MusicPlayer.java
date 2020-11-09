package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class MusicPlayer {
    private final String musicLocation = "music/";
    private AudioClip mediaPlayer;

    public MusicPlayer(String title) {
        setSong(title);
    }

    protected void changeSong(String title) {
        mediaPlayer.stop();     // in order to be garbage collected
        /*Media sound = new Media(new File(musicLocation + title).toURI().toString());
        mediaPlayer = new AudioClip(sound.getSource());
        mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
        mediaPlayer.play();*/
        setSong(title);
    }

    private void setSong(String title) {
        Media sound = new Media(new File(musicLocation + title).toURI().toString());
        mediaPlayer = new AudioClip(sound.getSource());
        mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
        mediaPlayer.play();
    }
}
