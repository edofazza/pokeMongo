package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox;

import java.io.IOException;

public class TypeForFilteringComboBox extends TypeComboBox {
    public TypeForFilteringComboBox(int x, int y) throws IOException {
        super(x, y);

        setStyle("-fx-background-color: white; -fx-font-size: 10; -fx-pref-width: 84; -fx-pref-height: 10;");
    }
}
