package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Label;

/**
 * Create a Label useful to use with a TextField
 */
public class FieldRelatedLabel extends Label {

    /**
     * Construct the FieldRelatedLabel and set the class style related
     * to it (<code>FieldRelatedLabel</code>)
     * @param text what will be written in the Label
     * @param x the x axis position
     * @param y the y axis position
     */
    public FieldRelatedLabel(String text, int x, int y) {
        super();
        Logger.vvlog("Creating FieldRelatedLabel '" + text + "' at (" + x + ", " + y + ")");

        setText(text);
        relocate(x, y);
        getStyleClass().add("FieldRelatedLabel");
    }
}
