package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PokeSceneWithHeader extends PokeScene {
    protected final String imgLocation = "file:img/";

    public PokeSceneWithHeader() {
        displayUsername();

        if (!CurrentUI.isAdmin()) {
            displayPokeBallsImage();
            displayPokeBallsLabelNumber();
        }
    }

    /*
     *  displayUsername() mostra il nome utente prendendolo dalla CurrentUI.
     */
    private void displayUsername() {
        FieldRelatedLabel username = new FieldRelatedLabel(CurrentUI.getUsername(), 10, 10);

        sceneNodes.getChildren().add(username);
    }

    private void displayPokeBallsImage() {
        ImageView pokeball = new ImageView(imgLocation + "pokeball.png");
        pokeball.setFitWidth(40);
        pokeball.setFitHeight(40);
        pokeball.relocate(1100, 10);

        sceneNodes.getChildren().add(pokeball);
    }

    private void displayPokeBallsLabelNumber() {
        FieldRelatedLabel pokeNumber = new FieldRelatedLabel("x" + Integer.toString(CurrentUI.getNumberOfPokeball()), 1150, 20);

        sceneNodes.getChildren().add(pokeNumber);
    }

    // TODO: if the number of pokeballs changes reload the correct number, it should be automatic
}
