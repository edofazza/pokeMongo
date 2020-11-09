package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.scene.Group;

public class PokeScene {
    protected static Group sceneNodes = new Group();
    protected static MusicPlayer mp = null;

    public Group getNodes() {
        return sceneNodes;
    }

    public void setSceneMusic(String title) {
        if (mp == null)
            mp = new MusicPlayer(title);

        mp.changeSong(title);
    }
}
