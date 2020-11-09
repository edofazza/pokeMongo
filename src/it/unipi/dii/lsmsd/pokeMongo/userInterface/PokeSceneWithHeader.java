package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PokeSceneWithHeader extends PokeScene {
    private final String imgLocation = "file:img/";
    protected int numberOfPokeballs = 10;

    public PokeSceneWithHeader() {
        displayUsername();
        displayPokeBallsImage();
        displayPokeBallsLabelNumber();
    }

    private void displayUsername() {
        Label username = new Label("username");
        username.relocate(10,10);
        username.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        sceneNodes.getChildren().add(username);
    }

    private void displayPokeBallsImage() {
        ImageView pokeball = new ImageView(imgLocation + "pokeball.png");
        pokeball.setFitWidth(40);
        pokeball.setFitHeight(40);
        pokeball.relocate(1100, 10);

        sceneNodes.getChildren().add(pokeball);
    }

    private void displayPokeBallsLabelNumber() {
        Label pokeNumber = new Label("x" + Integer.toString(numberOfPokeballs));
        pokeNumber.relocate(1150,20);
        pokeNumber.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        sceneNodes.getChildren().add(pokeNumber);
    }
}
