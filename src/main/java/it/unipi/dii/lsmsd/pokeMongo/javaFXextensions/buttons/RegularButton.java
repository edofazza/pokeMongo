package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import javafx.scene.control.Button;

/**
 * Regular Button for every needs
 */
public class RegularButton extends Button {

    /**
     * Instantiates a Button and associates it with a class style
     * @param text what will be written in the Button
     * @param x the x axis position
     * @param y the y axis position
     */
    public RegularButton(String text, int x, int y) {
        super();
        setText(text);
        relocate(x, y);
        getStyleClass().add("SubmissionButton");
    }
}
