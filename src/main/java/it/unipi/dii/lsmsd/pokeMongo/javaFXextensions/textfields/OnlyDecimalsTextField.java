package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import javafx.scene.control.TextField;

public class OnlyDecimalsTextField extends TextField {
    public OnlyDecimalsTextField(int x, int y) {
        relocate(x, y);

        textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
