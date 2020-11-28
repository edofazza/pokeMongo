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

        //addResult();
    }

    /**
     * Adds the results obtained to the VBox. The results are all <code>PokemonResultPanePokedex</code>
     */
    // TODO: THE QUERY on mongo db will add element in the VBox
    public static void addResult(ArrayList<Pokemon> result) {
        /*PokemonSingleResultPane prpp = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Pokedex ID: " + "7");
        PokemonSingleResultPane prpp1 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Pokedex ID: " + "7");
        PokemonSingleResultPane prpp2 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Pokedex ID: " + "7");
        PokemonSingleResultPane prpp3 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Pokedex ID: " + "7");
        PokemonSingleResultPane prpp4 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Pokedex ID: " + "7");
        PokemonSingleResultPane prpp5 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Pokedex ID: " + "7");
        PokemonSingleResultPane prpp6 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Pokedex ID: " + "7");

        root.getChildren().addAll(prpp, prpp1, prpp2, prpp3, prpp4, prpp5, prpp6);*/

        for (Pokemon p: result) {
            PokemonSingleResultPane prpp = new PokemonSingleResultPane(p, p.getSprite(), p.getName(), "Pokedex ID: " + p.getId());
            root.getChildren().add(prpp);
        }
    }

    public static void clearVBox() {
        root.getChildren().clear();
    }
}
