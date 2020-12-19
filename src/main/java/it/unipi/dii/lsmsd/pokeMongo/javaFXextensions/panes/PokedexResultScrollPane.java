package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Specific ScrollPane for handling all the results obtained by the filtering action
 * in the pokedex
 */
public class PokedexResultScrollPane extends ScrollPane {
    private static VBox root;

    /**
     * Relocates the pane, sets the prefSize of it and then instantiate a <code>VBox</code>
     * for handling the results obtained by the filtering action in a vertical way
     * @param x the x axis position
     * @param y the y axis position
     */
    public PokedexResultScrollPane(int x, int y) {
        setPrefSize(320, 280);
        relocate(x, y);

        root = new VBox();
        root.setSpacing(10);
        setContent(root);
    }

    /**
     * Adds the results obtained to the VBox. The results are all <code>PokemonResultPanePokedex</code>
     */
    public static void addResult(ArrayList<Pokemon> result) {
        for (Pokemon p: result) {
            PokemonSingleResultPane prpp = new PokemonSingleResultPane(p);
            root.getChildren().add(prpp);
        }
    }

    public static void clearVBox() {
        root.getChildren().clear();
    }
}
