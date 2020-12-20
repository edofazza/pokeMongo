package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.TextField;

public class OnlyDecimalsTextField extends TextField {

    /**
     * Constructs a particular TextField that takes only decimal values
     * @param x the x axis position
     * @param y the y axis position
     */
    public OnlyDecimalsTextField(int x, int y) {
        relocate(x, y);

        Logger.vvlog("Creating OnlyDecimalsTextField at (" + x + ", " + y + ")");

        textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
