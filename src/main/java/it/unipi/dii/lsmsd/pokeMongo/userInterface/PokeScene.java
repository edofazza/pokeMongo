package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.MusicButton;
import javafx.scene.Group;

/**
 * <em>PokeScene</em> is the root class of the scene classes, a new scene must
 * extends this one or one of its children. The class contains all the fundamental
 * attributes, that are needed in all the scene classes.
 */
public class PokeScene {
    protected static Group sceneNodes = new Group();
    protected static MusicPlayer mp = null;
    private static boolean musicOn = true;
    private static String currentTitle;
    private static MusicButton musicButton;

    /**
     * Constructor. It just create the music button (the only element presents
     * in all the scenes). It starts also the music.
     */
    public PokeScene() {
        musicButton =  new MusicButton(1250, 670);
        musicButton.setImage(!musicOn);

        musicButton.setOnAction(event -> handleMusic());

        sceneNodes.getChildren().add(musicButton);
    }

    /**
     * Get the sceneNodes attribute.
     * @return A <em>Group</em> containing all the <em>Node</em> in the scene
     */
    protected Group getNodes() {
        return sceneNodes;
    }

    /**
     * Set the music of the scene.
     * @param title title of the song asked to be played
     */
    protected void setSceneMusic(String title) {
        currentTitle = title;

        if (!musicOn)
            return;

        if (mp == null)
            mp = new MusicPlayer(title);

        mp.changeSong(title);
    }

    /**
     * <em>handleMusic()</em> handles the status of the music button
     * (music on or music off) and the image related to it.
     */
    private void handleMusic() {
        if (musicOn)
            mp.stopMusic();
        else
            mp = new MusicPlayer(currentTitle);

        musicButton.setImage(musicOn);

        musicOn = !musicOn;
    }
}
