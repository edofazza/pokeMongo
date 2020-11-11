package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels;

import javafx.scene.control.Label;

public class FieldRelatedLabel extends Label {
    public FieldRelatedLabel(String text, int x, int y) {
        super();
        setText(text);
        relocate(x, y);
        getStyleClass().add("FieldRelatedLabel");
    }
}
