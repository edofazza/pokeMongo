package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.TextField;

public class OnlyCharactersTextField extends TextField {
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
