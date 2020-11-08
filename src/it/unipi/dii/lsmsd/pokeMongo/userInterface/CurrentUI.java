package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class CurrentUI {
    private PokeScene nodeWindow;

    public Scene initScene() {
        Group root = new Group();

        /*   LogIn   */
        nodeWindow = new LogIn();
        root.getChildren().addAll(nodeWindow.getNodes());

        playMusic();

        return new Scene(root, 1280, 700);
    }

    private void playMusic() {
        String musicFile = "music/opening.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        AudioClip mediaPlayer = new AudioClip(sound.getSource());
        mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
        mediaPlayer.play();
    }
}
