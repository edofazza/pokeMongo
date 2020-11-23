package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels;

import javafx.scene.control.Label;

public class FilterLabel extends Label {
    public FilterLabel(String text, int x, int y) {
        super(text);
        relocate(x, y);

        getStyleClass().add("FilterLabel");
    }
}
