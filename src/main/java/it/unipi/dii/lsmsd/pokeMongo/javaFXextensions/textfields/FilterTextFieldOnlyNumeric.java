package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

public class FilterTextFieldOnlyNumeric extends FilterTextField {
    public FilterTextFieldOnlyNumeric(int x, int y) {
        super(x, y);

        textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
