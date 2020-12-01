package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.TextField;

/**
 * Specific TextField for the filter pane
 */
public class FilterTextField extends TextField {

    /**
     * Instantiates a TextField and associates it with a class style
     * @param x the x axis position
     * @param y the y axis position
     */
    public FilterTextField(int x, int y) {
        relocate(x, y);
        Logger.vvlog("Creating FilterTextField at (" + x + ", " + y + ")");

        getStyleClass().add("FilterTextField");
    }
}
