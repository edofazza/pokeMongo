package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.FilterPokemonResultButton;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Specific <code>Pane</code> for instantiating a single result
 */
public class PokemonResultPanePokedex extends Pane {
    /**
     * Adds to the pane the elements passed as arguments
     * @param sprite the url for the pokemon sprite
     * @param name the name of the pokemon
     * @param id the pokedex id
     */
    public PokemonResultPanePokedex(String sprite, String name, String id) {  // TODO: id can be an int, change it in case
        ImageView img = new ImageView(sprite);
        img.setFitWidth(60);
        img.setFitHeight(60);

        FilterPokemonResultButton pokemonName = new FilterPokemonResultButton(name, 90, 12);

        Label pokemonId = new Label("Pokedex ID: " + id);
        pokemonId.relocate(190, 18);

        getChildren().addAll(img, pokemonName, pokemonId);

        getStyleClass().add("PokemonResultPane");
    }
}
