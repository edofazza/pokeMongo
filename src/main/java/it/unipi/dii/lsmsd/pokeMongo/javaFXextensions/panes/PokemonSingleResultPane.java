package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.FilterPokemonResultButton;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Specific <code>Pane</code> for instantiating a single result
 */
public class PokemonSingleResultPane extends Pane {
    /**
     * Adds to the pane the elements passed as arguments
     * @param sprite the url for the pokemon sprite
     * @param name the name of the pokemon
     * @param text the text to be displayed after the name
     */
    public PokemonSingleResultPane(String sprite, String name, String text) {  // TODO: id can be an int, change it in case
        ImageView img = new ImageView(sprite);
        img.setFitWidth(60);
        img.setFitHeight(60);

        FilterPokemonResultButton pokemonName = new FilterPokemonResultButton(name, 90, 12);

        Label pokemonId = new Label(text);
        pokemonId.relocate(190, 18);

        getChildren().addAll(img, pokemonName, pokemonId);

        getStyleClass().add("PokemonResultPane");
    }
}
