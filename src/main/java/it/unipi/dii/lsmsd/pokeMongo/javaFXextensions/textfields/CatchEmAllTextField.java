package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.TextField;

/**
 * This particular TextField used to display resized Pokemon's image in the background
 */
public class CatchEmAllTextField extends TextField {
    /**
     * Instantiates a TextField and associates it with a class style
     * @param hint placeholder (prompt text)
     * @param x the x axis position
     * @param y the y axis position
     */
    public CatchEmAllTextField(String hint, int x, int y) {
        Logger.vvlog("Creating CatchEmAllTextField '" + hint + "' at (" + x + ", " + y + ")");

        setPromptText(hint);
        relocate(x, y);

        getStyleClass().add("CatchEmAllTextField");
    }
}
