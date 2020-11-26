package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels;

import javafx.scene.control.Label;

/**
 * Creates a standard Label for the title. Useful to reduce the code.
 */
public class TitleLabel extends Label {

    /**
     * Constructs the standard Label for the title
     * @param title what will be written in the Label
     */
    public TitleLabel(String title) {
        super();
        setText(title);
        relocate(475, 50);
        getStyleClass().add("TitleLabel");
    }
}
