package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.TitleLabel;

/**
 * This class should be used to be extended by scenes that want the title <em>PokeMongo</em>
 */
public class PokeSceneWithTitle extends PokeScene {
    /**
     * Creates a <code>TitleLabel</code> with text <em>PokeMongo</em> and adds it to the scene
     */
    public PokeSceneWithTitle() {
        TitleLabel title = new TitleLabel("PokeMongo");

        sceneNodes.getChildren().add(title);
    }
}
