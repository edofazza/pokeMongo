package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Label;

/**
 * Create the standard label to be used in the filter pane.
 */
public class FilterLabel extends Label {

    /**
     * Construct the FieldRelatedLabel and set the class style related
     * to it (<code>FilterLabel</code>)
     * @param text what will be written in the Label
     * @param x the x axis position
     * @param y the y axis position
     */
    public FilterLabel(String text, int x, int y) {
        super(text);
        Logger.vvlog("Creating FilterLabel '" + text + "' at (" + x + ", " + y + ")");

        relocate(x, y);

        getStyleClass().add("FilterLabel");
    }
}
