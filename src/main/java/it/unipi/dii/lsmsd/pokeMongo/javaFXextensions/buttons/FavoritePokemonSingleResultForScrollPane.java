package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import javafx.scene.control.Button;

public class FavoritePokemonSingleResultForScrollPane extends Button {
    private String pokemonName;
    private CatchEmAllTextField selectedPokemon;

    /**
     *
     * @param name name of the pokemon
     * @param selectedPokemon the <code>CatchEmAllTextField</code> we want to interact with
     */
    public FavoritePokemonSingleResultForScrollPane(String name, CatchEmAllTextField selectedPokemon) {
        super(name);
        this.pokemonName = name;
        this.selectedPokemon = selectedPokemon;

        setPrefSize(200, 24);

        setOnAction(e -> addToTextField());

        getStyleClass().add("FavoritePokemonSingleResultForScrollPane");
    }

    /**
     * Action taken when the button is pressed. Inserts the name of the favorite pokemon in the
     * <code>CatchEmAllTextField</code>
     */
    private void addToTextField() {
        selectedPokemon.setText(pokemonName);
    }
}
