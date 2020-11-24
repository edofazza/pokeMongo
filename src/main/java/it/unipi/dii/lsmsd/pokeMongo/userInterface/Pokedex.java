package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.FilterPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.PokedexResultScrollPane;

public class Pokedex extends PokeSceneWithHeaderAndBackButton {
    public Pokedex() {
        displayFilterPane();

        displayResultScrollPane();

        setSceneMusic("pokedex_oak_s_lab.mp3");
    }

    private void displayFilterPane() {
        FilterPane filterPane = new FilterPane(140, 60);

        sceneNodes.getChildren().add(filterPane);
    }

    private void displayResultScrollPane() {
        PokedexResultScrollPane sp = new PokedexResultScrollPane(490, 280);

        sceneNodes.getChildren().add(sp);
    }
}
