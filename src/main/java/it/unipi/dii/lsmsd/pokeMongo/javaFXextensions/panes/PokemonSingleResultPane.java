package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.FilterPokemonResultButton;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Specific <code>Pane</code> for instantiating a single result
 */
public class PokemonSingleResultPane extends Pane {
    private Pokemon pokemon;
    /**
     * Adds to the pane the elements passed as arguments
     * @param pokemon
     * @param sprite the url for the pokemon sprite
     * @param name the name of the pokemon
     * @param text the text to be displayed after the name
     */
    public PokemonSingleResultPane(Pokemon pokemon, String sprite, String name, String text) {  // TODO: id can be an int, change it in case
        this.pokemon = pokemon;
        ImageView img = new ImageView(CurrentUI.getImage(sprite));
        img.setFitWidth(60);
        img.setFitHeight(60);

        FilterPokemonResultButton pokemonName = new FilterPokemonResultButton(pokemon, 90, 12);

        Label pokemonId = new Label(text);
        pokemonId.relocate(190, 18);

        getChildren().addAll(img, pokemonName, pokemonId);

        getStyleClass().add("PokemonResultPane");
    }
}
