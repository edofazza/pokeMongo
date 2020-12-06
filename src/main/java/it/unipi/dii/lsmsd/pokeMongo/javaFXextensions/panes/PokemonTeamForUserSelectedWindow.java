package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.FilterPokemonResultButton;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PokemonTeamForUserSelectedWindow extends Pane {
    Pokemon pokemon;
    // TODO: GET THE POKEMON TO SHOW AND THE VALUE RELATED TO IT
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

    private void displayPokemonSprite() {
        if (pokemon == null)
            return;

        ImageView sprite = new ImageView();
        CurrentUI.getImage(pokemon.getSprite())
                .thenAccept(k -> sprite.setImage(k)); //TODO not fx thread
        sprite.setFitHeight(90);
        sprite.setFitWidth(90);

        getChildren().add(sprite);
    }

    private void displayPokemonName() {
        if (pokemon == null)
            return;

        //FilterPokemonResultButton pokemon = new FilterPokemonResultButton("Squirtle", 95, 28);

        //getChildren().add(pokemon);
    }

    private void displayPokemonPoints() {
        Label pokemonpoints = new Label("Pts: " + pokemon.getPoints());
        pokemonpoints.relocate(210, 32);

        getChildren().add(pokemonpoints);
    }
}
