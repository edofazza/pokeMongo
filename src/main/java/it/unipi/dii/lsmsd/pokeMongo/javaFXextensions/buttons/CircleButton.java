package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.RadioButton;

public class CircleButton extends RadioButton {
    /**
     * Instantiates a <code>RadioButton</code> and associates it with a class style
     * (<code>CircleButton</code>)
     * @param x the x axis position
     * @param y the y axis position
     */
    public CircleButton(int x, int y) {
        Logger.vvlog("Creating circle button at (" + x + ", " + y + ")");
        relocate(x, y);
        getStyleClass().add("CircleButton");
    }
}
