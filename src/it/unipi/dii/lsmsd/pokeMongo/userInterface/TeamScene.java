package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.SubmissionButton;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
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
                "-fx-background-image: url("+ imgLocation+"trash.png);" +
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
        teamNameTF.setAlignment(Pos.CENTER);

        teamNameTF.setStyle("-fx-font-size: 50px; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold; " +
                "-fx-background-color: transparent; " +
                "-fx-border-color: #FFFFFF;" +
                "-fx-text-fill: #015478;" +
                "-fx-max-width: 350;");

        sceneNodes.getChildren().add(teamNameTF);
    }

    private void displayBackButton() {
        SubmissionButton backButton = new SubmissionButton("BACK", 200, 600);

        backButton.setOnAction((ActionEvent ev)-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    private void displaySaveButton() {
        SubmissionButton saveButton = new SubmissionButton("SAVE", 1000, 600);

        // TODO: ADD ACTION

        sceneNodes.getChildren().add(saveButton);
    }

    // ACTIONS
    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }
}
