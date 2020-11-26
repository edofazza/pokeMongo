package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.imageviews.BackgroundImage;

/**
 * This class should be used to be extended by scenes that want Blastoice and Charizard
 * in the background.
 */
public class PokeSceneWithBlastoiseCharizard extends PokeSceneWithTitle {

    /**
     * Instantiates the two <code>BackgroundImage</code> one for the image of Blastoice and the other one
     * for the image of Charizard. Adds them to the scene.
     */
    public PokeSceneWithBlastoiseCharizard() {
        BackgroundImage blastoise = new BackgroundImage("blastoise.png", 350, 80, 300);
        BackgroundImage charizard = new BackgroundImage("charizard.png", 400, 800, 300);

        sceneNodes.getChildren().add(blastoise);
        sceneNodes.getChildren().add(charizard);
    }
}
