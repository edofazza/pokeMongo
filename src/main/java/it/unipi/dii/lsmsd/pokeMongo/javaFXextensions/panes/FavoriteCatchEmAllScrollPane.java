package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.FavoritePokemonSingleResultForScrollPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class FavoriteCatchEmAllScrollPane extends ScrollPane {
    private VBox root;

    public FavoriteCatchEmAllScrollPane(int x, int y, int width, int height, CatchEmAllTextField selectedPokemon) {
        relocate(x, y);
        setPrefSize(width, height);

        root = new VBox();
        root.setSpacing(5);
        setContent(root);

        retrieveFavoritePokemons(selectedPokemon);
    }

    private void retrieveFavoritePokemons(CatchEmAllTextField selectedPokemon) {
        List<String> pokemonNames = (UserNetworkManagerFactory.buildManager()).getLikedPokemonNames(CurrentUI.getUser());

        for (String name: pokemonNames) {
            FavoritePokemonSingleResultForScrollPane favPokemon = new FavoritePokemonSingleResultForScrollPane(name, selectedPokemon);

            root.getChildren().add(favPokemon);
        }
    }
}
