package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.TeamNameTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class TeamScene extends PokeSceneWithHeader {
    private TeamNameTextField teamNameTF;

    public TeamScene() {
        displayTeamName();

        displayPokemon();

        displayBackButton();
        displaySaveButton();

        setSceneMusic("pokemon_center.mp3");
    }

    // TODO: fare il trash button come una classe e attribuirgli il css
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
        teamNameTF = new TeamNameTextField(CurrentUI.getTeamName(), 450, 50);

        sceneNodes.getChildren().add(teamNameTF);
    }

    private void displayBackButton() {
        RegularButton backButton = new RegularButton("BACK", 200, 600);

        backButton.setOnAction((ActionEvent ev)-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    private void displaySaveButton() {
        RegularButton saveButton = new RegularButton("SAVE", 1000, 600);

        // TODO: ADD ACTION

        sceneNodes.getChildren().add(saveButton);
    }

    // ACTIONS
    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }
}
