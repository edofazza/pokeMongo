package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import javafx.scene.control.TextField;

/**
 * Specific TextField for the Team name in the Team scene.
 */
public class TeamNameTextField extends TextField {

    /**
     * Instantiates a TextField and associates it with a class style
     * @param text placeholder
     * @param x the x axis position
     * @param y the y axis position
     */
    public TeamNameTextField(String text, int x, int y) {
        super(text);
        relocate(x, y);
        getStyleClass().add("TeamNameTextField");
    }
}
