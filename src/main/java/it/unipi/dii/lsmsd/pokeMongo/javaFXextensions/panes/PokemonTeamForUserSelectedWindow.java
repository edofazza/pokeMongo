package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.FilterPokemonResultButton;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.text.DecimalFormat;

/**
 * Pane related to a single pokemon in the <code>TeamUserWindowGroup</code>
 */
public class PokemonTeamForUserSelectedWindow extends Pane {
    Pokemon pokemon;

    /**
     *
     * @param pokemon the pokemon this pane is related
     * @param x the x axis position
     * @param y the y axis position
     */
    public PokemonTeamForUserSelectedWindow(Pokemon pokemon, int x, int y) {
        relocate(x, y);
        setPrefSize(260, 90);

        setStyle("-fx-border-color: #ded8d8; " +
                "-fx-background-color: transparent;" +
                "-fx-border-radius: 10;");

        this.pokemon = pokemon;

        displayPokemonSprite();
        displayPokemonName();
        displayPokemonPoints();
    }

    /**
     * Displays the spirite of <code>pokemon</code>
     */
    private void displayPokemonSprite() {
        if (pokemon == null)
            return;

        ImageView sprite = new ImageView();
        CurrentUI.getImage(pokemon.getSprite())
                .thenAccept(k -> sprite.setImage(k)); // not fx thread
        sprite.setFitHeight(90);
        sprite.setFitWidth(90);

        getChildren().add(sprite);
    }

    /**
     * Displays the name of the pokemon
     */
    private void displayPokemonName() {
        if (pokemon == null)
            return;

        FilterPokemonResultButton pokemonNameButton = new FilterPokemonResultButton(pokemon, 95, 28);

        getChildren().add(pokemonNameButton);
    }

    /**
     * Displays the points of the pokemon
     */
    private void displayPokemonPoints() {
        if (pokemon == null)
            return;

        double currentPoint = (255 - pokemon.getCapture_rate())*100;
        double points = Math.round(currentPoint);
        Label pokemonpoints = new Label("Pts: " + ((double)points/100));
        pokemonpoints.relocate(197, 32);

        getChildren().add(pokemonpoints);
    }
}
