package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

import java.io.IOException;

/**
 * Particula TypeComboBox used in the Filter Pane
 */
public class TypeForFilteringComboBox extends TypeComboBox {
    /**
     *
     * @param x the x axis position
     * @param y the y axis position
     * @throws IOException
     */
    public TypeForFilteringComboBox(int x, int y) throws IOException {
        super(x, y);
        Logger.vvlog("Creating TypeForFilteringComboBox at (" + x + ", " + y + ")");
        setStyle("-fx-background-color: white; -fx-font-size: 10; -fx-pref-width: 84; -fx-pref-height: 10;");
    }
}
