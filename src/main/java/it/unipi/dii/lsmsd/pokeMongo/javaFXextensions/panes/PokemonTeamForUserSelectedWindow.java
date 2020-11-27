package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.FilterPokemonResultButton;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PokemonTeamForUserSelectedWindow extends Pane {

    // TODO: GET THE POKEMON TO SHOW AND THE VALUE RELATED TO IT
    public PokemonTeamForUserSelectedWindow(int x, int y) {
        relocate(x, y);
        setPrefSize(260, 90);

        setStyle("-fx-border-color: #ded8d8; " +
                "-fx-background-color: transparent;" +
                "-fx-border-radius: 10;");

        displayPokemonSprite();
        displayPokemonName();
        displayPokemonPoints();
    }

    private void displayPokemonSprite() {
        ImageView sprite = new ImageView(CurrentUI.getImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png"));
        sprite.setFitHeight(90);
        sprite.setFitWidth(90);

        getChildren().add(sprite);
    }

    private void displayPokemonName() {
        FilterPokemonResultButton pokemon = new FilterPokemonResultButton("Squirtle", 95, 28);

        getChildren().add(pokemon);
    }

    private void displayPokemonPoints() {

    }
}
