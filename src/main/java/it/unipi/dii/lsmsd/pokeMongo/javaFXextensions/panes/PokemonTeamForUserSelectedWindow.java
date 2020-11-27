package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import javafx.scene.layout.Pane;

public class PokemonTeamForUserSelectedWindow extends Pane {
    public PokemonTeamForUserSelectedWindow(int x, int y) {
        relocate(x, y);
        setPrefSize(260, 90);

        setStyle("-fx-background-color: #ded8d8;");
    }
}
