package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.FilterPokemonResultButton;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Specific <code>Pane</code> for instantiating a single result
 */
public class RankingPokemonSingleResultPane extends Pane {
    private Pokemon pokemon;
    /**
     * Adds to the pane the elements passed as arguments
     * @param pokemon
     */
    public RankingPokemonSingleResultPane(Pokemon pokemon) {
        this.pokemon = pokemon;
        ImageView img = new ImageView();
        CurrentUI.getImage(pokemon.getSprite()).thenAccept(k -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    img.setImage(k);
                }
            });
        }); // not fx thread exception
        img.setFitWidth(60);
        img.setFitHeight(60);

        FilterPokemonResultButton pokemonName = new FilterPokemonResultButton(pokemon, 70, 12);

        Label pokemonId = new Label("Held by: " + pokemon.getHeld());
        pokemonId.relocate(190, 18);

        getChildren().addAll(img, pokemonName, pokemonId);

        getStyleClass().add("PokemonResultPane");
    }
}
