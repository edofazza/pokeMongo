package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class FavoriteCatchEmAllScrollPane extends ScrollPane {
    private VBox root;

    public FavoriteCatchEmAllScrollPane(int x, int y, int width, int height) {
        relocate(x, y);
        setPrefSize(width, height);

        root = new VBox();
        root.setSpacing(5);
        setContent(root);

        retrieveFavoritePokemons();
    }

    private void retrieveFavoritePokemons() {
        List<String> pokemonNames = (UserNetworkManagerFactory.buildManager()).getLikedPokemonNames(CurrentUI.getUser());

        for (String name: pokemonNames) {
            Button button = new Button(name);
            button.setStyle("-fx-background-color: white; -fx-border-color: #4aa7e9; -fx-text-alignment: center;" +
                    " -fx-font-weight: bold; -fx-text-fill: #4aa7e9;");
            button.setPrefSize(200, 24);

            root.getChildren().add(button);
        }
    }
}
