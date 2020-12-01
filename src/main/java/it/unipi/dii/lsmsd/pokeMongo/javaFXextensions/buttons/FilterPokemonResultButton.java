package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.group.PokemonWindowGroup;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This particular Button is used for the name of the pokemon in the filter result.
 */
public class FilterPokemonResultButton extends Button {

    /**
     * Instantiates a Button and associates it with a class style
     * (<code>FilterPokemonResultButton</code>)
     * @param pokemon what will be written in the Button
     * @param x the x axis position
     * @param y the y axis position
     */
    public FilterPokemonResultButton(Pokemon pokemon, int x, int y) {
        super(pokemon.getName());
        Logger.vvlog("Creating FilterPokemonResultButton at (" + x + ", " + y + ")");
        relocate(x, y);
        setOnAction(e -> createNewWindow(pokemon));

        getStyleClass().add("FilterPokemonResultButton");
    }

    /**
     * Open a new window with the information concerning a pokemon.
     * @param pokemon contains the name of the pokemon in order to use it as the title for the Stage
     */
    private void createNewWindow(Pokemon pokemon) {
        PokemonWindowGroup root = new PokemonWindowGroup(
                pokemon.getPortrait(),
                pokemon.getSprite(),
                pokemon.getName(),
                pokemon.getTypeSingleString(),
                Double.toString(pokemon.getWeight()),
                Double.toString(pokemon.getHeight()),
                Double.toString(pokemon.getCapture_rate()),
                Double.toString(pokemon.getPoints()),
                pokemon.getBiology());

        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setTitle(pokemon.getName());
        stage.setScene(scene);
        stage.show();
    }
}
