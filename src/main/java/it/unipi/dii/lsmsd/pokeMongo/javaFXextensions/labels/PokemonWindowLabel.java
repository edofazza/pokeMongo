package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Label;

/**
 * Creates a standard Label for the pokemon window. Useful to reduce the code.
 */
public class PokemonWindowLabel extends Label {

    /**
     * Constructs the standard Label for the pokemon window and sets the style for it
     * @param text what will be written in the Label
     * @param x the x axis position
     * @param y the y axis position
     */
    public PokemonWindowLabel(String text, int x, int y) {
        super(text);
        Logger.vvlog("Creating PokemonWindowLabel '" + text + "' at (" + x + ", " + y + ")");
        relocate(x, y);
        setStyle("-fx-font-size: 16; -fx-font-family: Arial;");
    }
}
