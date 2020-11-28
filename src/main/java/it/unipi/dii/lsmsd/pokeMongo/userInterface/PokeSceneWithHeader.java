package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.imageviews.BackgroundImage;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;

/**
 * This class should be used to be extended by scenes that want
 * the header containing the username and the number of pokeball hold (this one only for normal user).
 */
public class PokeSceneWithHeader extends PokeScene {
    protected final String imgLocation = "file:img/";
    private FieldRelatedLabel pokeNumber;

    /**
     * Calls a series of function to add the appropriate <code>Node</code> to the scene.
     */
    public PokeSceneWithHeader() {
        displayUsername();

        if (!CurrentUI.isAdmin()) {
            displayPokeBallsImage();
            displayPokeBallsLabelNumber();
        }
    }

    /**
     *  Adds the username to the scene using a <code>FieldRelatedLabel</code>.
     */
    private void displayUsername() {
        FieldRelatedLabel username = new FieldRelatedLabel(CurrentUI.getUsername(), 10, 10);

        sceneNodes.getChildren().add(username);
    }

    /**
     * Adds an image representing a pokeball (called only if the user is a normal user).
     */
    private void displayPokeBallsImage() {
        BackgroundImage pokeball = new BackgroundImage("pokeball.png", 40, 1100, 10);

        sceneNodes.getChildren().add(pokeball);
    }

    /**
     * Adds to the scene the number of pokemon hold by the user (called only if the user is a normal user).
     */
    private void displayPokeBallsLabelNumber() {
        pokeNumber = new FieldRelatedLabel("x" + CurrentUI.getNumberOfPokeball(), 1150, 20);

        sceneNodes.getChildren().add(pokeNumber);
    }

    /**
     * Update number of pokeballs
     */
    public void updatePokeBallsLabelNumber() {
        pokeNumber.setText("x" + CurrentUI.getNumberOfPokeball());
    }
}
