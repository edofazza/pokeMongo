package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PokemonResultPanePokedex extends Pane {
    public PokemonResultPanePokedex(String sprite, String name, String id) {  // TODO: id can be an int, change it in case
        ImageView img = new ImageView(sprite);
        img.setFitWidth(60);
        img.setFitHeight(60);

        Button pokemonName = new Button(name);
        pokemonName.relocate(90, 12);
        pokemonName.setStyle("-fx-background-color: transparent; -fx-font-family: Arial;");
        pokemonName.setOnAction(e -> System.out.println("goofy"));

        Label pokemonId = new Label("Pokedex ID: " + id);
        pokemonId.relocate(190, 18);

        getChildren().addAll(img, pokemonName, pokemonId);

        getStyleClass().add("PokemonResultPane");
    }
}
