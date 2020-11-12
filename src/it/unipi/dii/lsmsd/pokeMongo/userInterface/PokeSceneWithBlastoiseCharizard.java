package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.imageviews.BackgroundImage;

public class PokeSceneWithBlastoiseCharizard extends PokeSceneWithTitle {

    public PokeSceneWithBlastoiseCharizard() {
        BackgroundImage blastoise = new BackgroundImage("blastoise.png", 350, 80, 300);
        BackgroundImage charizard = new BackgroundImage("charizard.png", 400, 800, 300);

        sceneNodes.getChildren().add(blastoise);
        sceneNodes.getChildren().add(charizard);
    }
}
