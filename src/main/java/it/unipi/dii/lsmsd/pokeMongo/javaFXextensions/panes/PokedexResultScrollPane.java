package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class PokedexResultScrollPane extends ScrollPane {
    private VBox root;

    public PokedexResultScrollPane(int x, int y) {
        setPrefSize(320, 280);
        relocate(x, y);

        root = new VBox();
        root.setSpacing(10);
        setContent(root);

        addResult();
    }

    // TODO: THE QUERY on mongo db will add element in the VBox
    public void addResult() {
        PokemonResultPanePokedex prpp = new PokemonResultPanePokedex("file:img/sprites/7.png", "Squirtle", "7");
        PokemonResultPanePokedex prpp1 = new PokemonResultPanePokedex("file:img/sprites/7.png", "Squirtle", "7");
        PokemonResultPanePokedex prpp2 = new PokemonResultPanePokedex("file:img/sprites/7.png", "Squirtle", "7");
        PokemonResultPanePokedex prpp3 = new PokemonResultPanePokedex("file:img/sprites/7.png", "Squirtle", "7");
        PokemonResultPanePokedex prpp4 = new PokemonResultPanePokedex("file:img/sprites/7.png", "Squirtle", "7");
        PokemonResultPanePokedex prpp5 = new PokemonResultPanePokedex("file:img/sprites/7.png", "Squirtle", "7");
        PokemonResultPanePokedex prpp6 = new PokemonResultPanePokedex("file:img/sprites/7.png", "Squirtle", "7");

        root.getChildren().addAll(prpp, prpp1, prpp2, prpp3, prpp4, prpp5, prpp6);
    }
}
