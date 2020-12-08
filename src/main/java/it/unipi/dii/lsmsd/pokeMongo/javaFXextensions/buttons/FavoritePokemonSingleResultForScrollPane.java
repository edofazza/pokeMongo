package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import javafx.scene.control.Button;

public class FavoritePokemonSingleResultForScrollPane extends Button {
    private String pokemonName;
    private CatchEmAllTextField selectedPokemon;

    public FavoritePokemonSingleResultForScrollPane(String name, CatchEmAllTextField selectedPokemon) {
        super(name);
        this.pokemonName = name;
        this.selectedPokemon = selectedPokemon;

        setPrefSize(200, 24);

        setOnAction(e -> addToTextField());

        getStyleClass().add("FavoritePokemonSingleResultForScrollPane");
    }

    private void addToTextField() {
        selectedPokemon.setText(pokemonName);

    }
}
