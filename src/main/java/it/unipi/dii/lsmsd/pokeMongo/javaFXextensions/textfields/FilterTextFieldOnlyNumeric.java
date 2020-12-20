package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

public class FilterTextFieldOnlyNumeric extends FilterTextField {

    /**
     * Constructs a particular FilterTextFields that takes only decimal values
     * @param x the x axis position
     * @param y the y axis position
     */
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
