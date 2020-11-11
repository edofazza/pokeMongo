package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class Settings extends PokeSceneWithHeader {
    public Settings() {
        displayBackButton();
        displayConfirmButton();
    }

    private void setCSS(Button b) { //TODO: remove it with a final string or css file
        b.setStyle("-fx-font-size: 15px; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold; " +
                "-fx-background-color: transparent; " +
                "-fx-border-color: #000000;");
    }

    private void displayBackButton() {
        Button backButton = new Button("BACK");
        backButton.relocate(200, 600);

        setCSS(backButton);

        backButton.setOnAction((ActionEvent ev)-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }

    private void displayConfirmButton() {
        Button submitButton = new Button("CONFIRM");
        submitButton.relocate(1000, 600);

        setCSS(submitButton);

        sceneNodes.getChildren().add(submitButton);
    }
}
