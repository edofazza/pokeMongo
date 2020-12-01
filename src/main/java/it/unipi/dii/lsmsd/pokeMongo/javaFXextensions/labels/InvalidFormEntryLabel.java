package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Label;

/**
 * Creates a standard Invalid Form Entry Label. Useful to reduce the code.
 */
public class InvalidFormEntryLabel extends Label {

    /**
     * Constructs the standard Invalid Form Entry Label and sets the class style related
     * to it (<code>InvalidFormEntry</code>)
     * @param text what will be written in the Label
     * @param x the x axis position
     * @param y the y axis position
     * @param visible boolean saying if the label is visible or not
     */
    public InvalidFormEntryLabel(String text, int x, int y, boolean visible) {
        super(text);
        Logger.vvlog("Creating InvalidFormEntryLabel '" + text + "' at (" + x + ", " + y + ")");

        relocate(x, y);
        setVisible(visible);

        getStyleClass().add("InvalidFormEntry");
    }
}
