package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.FilterPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.PokedexResultScrollPane;

/**
 * Class scene related to the Podekex
 */
public class Pokedex extends PokeSceneWithHeaderAndBackButton {
    /**
     * Calls a series of function to set up the scene. It sets also the music.
     */
    public Pokedex() {
        displayFilterPane();

        displayResultScrollPane();

        setSceneMusic("pokedex_oak_s_lab.mp3");
    }

    /**
     * Creates a <code>FilterPane</code> and adds it to the scene
     */
    private void displayFilterPane() {
        FilterPane filterPane = new FilterPane(140, 60);

        sceneNodes.getChildren().add(filterPane);
    }

    /**
     * reates a <code>PokedexResultScrollPane</code> and adds it to the scene
     */
    private void displayResultScrollPane() {
        PokedexResultScrollPane sp = new PokedexResultScrollPane(490, 280);

        sceneNodes.getChildren().add(sp);
    }
}
