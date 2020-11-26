package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.group.PokemonWindowGroup;
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
     * @param name what will be written in the Button
     * @param x the x axis position
     * @param y the y axis position
     */
    public FilterPokemonResultButton(String name, int x, int y) {
        super(name);
        relocate(x, y);
        setOnAction(e -> createNewWindow(name)); // TODO: popup window

        getStyleClass().add("FilterPokemonResultButton");
    }

    /**
     * Open a new window with the information concerning a pokemon.
     * @param name contains the name of the pokemon in order to use it as the title for the Stage
     */
    private void createNewWindow(String name) {
        PokemonWindowGroup root = new PokemonWindowGroup(
                "file:img/portraits/7.png",
                "file:img/sprites/7.png",
                "Squirtle",
                "water",
                "1,4",
                "20",
                "0.75",
                "25",
                "Squirtle is a small Pokémon that resembles a light-blue turtle. While it typically walks on its two short legs, it has been shown to run on all fours in Super Smash Bros. Brawl. It has large, purplish or reddish eyes and a slightly hooked upper lip. Each of its hands and feet have three pointed digits. The end of its long tail curls inward. Its body is encased by a tough shell that forms and hardens after birth. This shell is brown on the top, pale yellow on the bottom, and has a thick white ridge between the two halves." +
                        "\nSquirtle's shell is a useful tool. It can withdraw into the shell for protection or to sleep. The grooved, rounded shape helps to reduce water resistance, allowing the Pokémon to swim at high speeds. Squirtle can spray foamy water from its mouth with great accuracy. Squirtle is scarce in the wild, although it can be found around small ponds and lakes.");

        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setTitle(name);
        stage.setScene(scene);
        stage.show();
    }
}
