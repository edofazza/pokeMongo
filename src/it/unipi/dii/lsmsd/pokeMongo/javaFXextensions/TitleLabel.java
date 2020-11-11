package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions;

import javafx.scene.control.Label;

public class TitleLabel extends Label {
    public TitleLabel(String title) {
        super();
        setText(title);
        relocate(475, 50);
        getStyleClass().add("TitleLabel");
    }
}
