package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.FilterPokemonResultButton;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PokemonResultPanePokedex extends Pane {
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
