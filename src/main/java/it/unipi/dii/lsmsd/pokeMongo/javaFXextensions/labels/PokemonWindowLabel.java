package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels;

import javafx.scene.control.Label;

public class PokemonWindowLabel extends Label {
    public PokemonWindowLabel(String text, int x, int y) {
        super(text);
        relocate(x, y);
        setStyle("-fx-font-size: 16; -fx-font-family: Arial;");
    }
}
