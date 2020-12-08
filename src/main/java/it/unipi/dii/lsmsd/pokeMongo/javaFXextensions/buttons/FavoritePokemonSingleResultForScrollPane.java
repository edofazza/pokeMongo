package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import javafx.scene.control.Button;

public class FavoritePokemonSingleResultForScrollPane extends Button {
    private String pokemonName;

    public FavoritePokemonSingleResultForScrollPane(String name) {
        super(name);
        this.pokemonName = name;

        setPrefSize(200, 24);

        getStyleClass().add("FavoritePokemonSingleResultForScrollPane");
    }
}
