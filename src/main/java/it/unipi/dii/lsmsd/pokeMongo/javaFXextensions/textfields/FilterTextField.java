package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import javafx.scene.control.TextField;

public class FilterTextField extends TextField {
    public FilterTextField(int x, int y) {
        relocate(x, y);

        getStyleClass().add("FilterTextField");
    }
}
