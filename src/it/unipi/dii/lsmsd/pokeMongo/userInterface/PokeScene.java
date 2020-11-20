package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.MusicButton;
import javafx.scene.Group;

public class PokeScene {
    protected static Group sceneNodes = new Group();
    protected static MusicPlayer mp = null;
    private static boolean musicOn = true;
    private static String currentTitle;
    private static MusicButton musicButton;

    public PokeScene() {
        musicButton =  new MusicButton(1250, 670);
        musicButton.setImage(!musicOn);

        musicButton.setOnAction(event -> handleMusic());

        sceneNodes.getChildren().add(musicButton);
    }

    protected Group getNodes() {
        return sceneNodes;
    }

    protected void setSceneMusic(String title) {
        currentTitle = title;

        if (!musicOn)
            return;

        if (mp == null)
            mp = new MusicPlayer(title);

        mp.changeSong(title);
    }

    private void handleMusic() {
        if (musicOn)
            mp.stopMusic();
        else
            mp = new MusicPlayer(currentTitle);

        musicButton.setImage(musicOn);

        musicOn = !musicOn;
    }
}
