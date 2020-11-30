package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import javafx.scene.control.TextField;

public class OnlyCharactersTextField extends TextField {
    public OnlyCharactersTextField(int x, int y) {
        relocate(x, y);

        textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[a-zA-Z]+$")) {
                setText(newValue.replaceAll("\\d", ""));
            }
        });
    }
}
