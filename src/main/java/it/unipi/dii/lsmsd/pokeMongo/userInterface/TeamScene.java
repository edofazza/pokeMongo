package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.PokemonPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.TeamNameTextField;
import javafx.event.ActionEvent;

public class TeamScene extends PokeSceneWithHeader {
    private TeamNameTextField teamNameTF;

    // TODO: SEE IF IT IS REALLY HELPFUL
    private PokemonPane[] pokePaneArray = new PokemonPane[6];

    public TeamScene() {
        displayTeamName();

        displayPokemon();

        displayBackButton();
        displaySaveButton();

        setSceneMusic("pokemon_center.mp3");
    }

    // TODO: fare il trash button come una classe e attribuirgli il css
    private void displayPokemon() {
        for (int i = 0; i < 3; ++i){
            PokemonPane pp = new PokemonPane(150, 180 + i*110);
            pokePaneArray[i] = pp;

            sceneNodes.getChildren().add(pp);
        }

        for (int i = 0; i < 3; ++i){
            PokemonPane pp = new PokemonPane(700, 180 + i*110);
            pokePaneArray[i+3] = pp;

            sceneNodes.getChildren().add(pp);
        }
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
