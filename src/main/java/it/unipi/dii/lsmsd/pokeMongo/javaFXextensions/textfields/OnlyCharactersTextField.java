package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.TextField;

public class OnlyCharactersTextField extends TextField {
    /**
     * Constructs a particular TextField that takes only character values
     * @param x the x axis position
     * @param y the y axis position
     */
    public OnlyCharactersTextField(int x, int y) {
        relocate(x, y);
        Logger.vvlog("Creating OnlyCharactersTextField at (" + x + ", " + y + ")");

        textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[a-zA-Z]+$")) {
                setText(newValue.replaceAll("\\d", ""));
            }
        });
    }
}
