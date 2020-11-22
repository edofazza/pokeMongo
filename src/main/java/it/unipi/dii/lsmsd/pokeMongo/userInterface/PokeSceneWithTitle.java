package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.TitleLabel;

public class PokeSceneWithTitle extends PokeScene {
    public PokeSceneWithTitle() {
        TitleLabel title = new TitleLabel("PokeMongo");

        sceneNodes.getChildren().add(title);
    }
}
