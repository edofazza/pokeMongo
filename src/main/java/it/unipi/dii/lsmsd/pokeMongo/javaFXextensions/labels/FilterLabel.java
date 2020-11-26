package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels;

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
        relocate(x, y);

        getStyleClass().add("FilterLabel");
    }
}
