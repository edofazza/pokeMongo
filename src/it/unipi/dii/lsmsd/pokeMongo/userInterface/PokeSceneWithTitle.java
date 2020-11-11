package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.TitleLabel;
import javafx.scene.control.Label;

public class PokeSceneWithTitle extends PokeScene {
    public PokeSceneWithTitle() {
        TitleLabel title = new TitleLabel("PokeMongo");
        /*Label title = new Label("PokeMongo");
        title.relocate(475, 50);
        title.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 50px; -fx-font-weight: bold; -fx-text-fill: #a30014;");*/

        sceneNodes.getChildren().add(title);
    }
}
