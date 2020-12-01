package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

public class FilterTextFieldOnlyNumeric extends FilterTextField {
    public FilterTextFieldOnlyNumeric(int x, int y) {
        super(x, y);

        Logger.vvlog("Creating FilterTextFieldOnlyNumeric at (" + x + ", " + y + ")");

        textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
