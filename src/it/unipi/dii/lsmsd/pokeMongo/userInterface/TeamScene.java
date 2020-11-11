package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TeamScene extends PokeSceneWithHeader {
    private TextField teamNameTF;

    public TeamScene() {
        displayTeamName();

        displayPokemon();

        displayBackButton();
        displaySaveButton();

        setSceneMusic("pokemon_center.mp3");
    }

    private void displayPokemon() {
        Button trashcanButton = new Button();
        trashcanButton.setPrefWidth(30);
        trashcanButton.setPrefHeight(30);
        trashcanButton.setStyle("-fx-border-color: transparent;" +
                "-fx-background-color: transparent;" +
                "-fx-background-image: url(file:img/trash.png);" +
                "-fx-background-size: 20px 20x;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center;");

        trashcanButton.relocate(200,200);

        // TODO: create correct action
        trashcanButton.setOnAction((ActionEvent ev)-> System.out.println("pippo"));

        sceneNodes.getChildren().add(trashcanButton);
    }

    private void displayTeamName() {
        teamNameTF = new TextField(CurrentUI.getTeamName());
        teamNameTF.relocate(450, 50);

        teamNameTF.setStyle("-fx-font-size: 50px; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold; " +
                "-fx-background-color: transparent; " +
                "-fx-border-color: #FFFFFF;" +
                "-fx-text-fill: #015478;" +
                "-fx-max-width: 350;");

        sceneNodes.getChildren().add(teamNameTF);
    }

    private void setBackButtonCSS(Button b) { //TODO: remove it with a final string or css file
        b.setStyle("-fx-font-size: 15px; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold; " +
                "-fx-background-color: transparent; " +
                "-fx-border-color: #000000;");
    }

    private void displayBackButton() {
        Button backButton = new Button("BACK");
        backButton.relocate(200, 600);

        setBackButtonCSS(backButton);

        backButton.setOnAction((ActionEvent ev)-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    private void displaySaveButton() {
        Button saveButton = new Button("SAVE");
        saveButton.relocate(1000, 600);

        setBackButtonCSS(saveButton);

        // TODO: ADD ACTION

        sceneNodes.getChildren().add(saveButton);
    }

    // ACTIONS
    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }
}
