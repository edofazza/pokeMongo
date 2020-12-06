package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.PokemonPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.TeamNameTextField;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManagerOnNeo4j;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerOnMongoDb;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

import java.util.ArrayList;

/**
 * This class is used to display the <code>Node</code> concerning the Team.
 */
public class TeamScene extends PokeSceneWithHeaderAndBackButton {
    private TeamNameTextField teamNameTF;
    private FieldRelatedLabel points;

    private PokemonPane[] pokePaneArray = new PokemonPane[6];

    /**
     * Calls a series of function in order to add to the scene all the elements needed.
     * It also sets the music.
     */
    public TeamScene() {
        Logger.log("SHOWING TEAM PAGE");

        CurrentUI.getUser().addTeam(retrieveTeam());

        displayTeamName();

        displayPokemon();

        displayPoints();

        displaySaveButton();

        setSceneMusic("pokemon_center.mp3");
    }

    /**
     * Adds to the scene the six <code>PokemonPane</code> forming the six Pokemon of the team.
     */
    // TODO: fare il trash button come una classe e attribuirgli il css
    private void displayPokemon() {
        for (int i = 0; i < 3; ++i){
            PokemonPane pp = new PokemonPane(150, 180 + i*110, CurrentUI.getUser().getFromTeam(i));
            pokePaneArray[i] = pp;

            sceneNodes.getChildren().add(pp);
        }

        for (int i = 0; i < 3; ++i){
            PokemonPane pp = new PokemonPane(700, 180 + i*110, CurrentUI.getUser().getFromTeam(i+3));
            pokePaneArray[i+3] = pp;

            sceneNodes.getChildren().add(pp);
        }
    }

    /**
     * Adds to the scene the Team Name
     */
    private void displayTeamName() {
        teamNameTF = new TeamNameTextField(CurrentUI.getTeamName(), 450, 50);

        sceneNodes.getChildren().add(teamNameTF);
    }

    /**
     * Adds to the scene the points of the team
     */
    private void displayPoints() {
        points = new FieldRelatedLabel("Points: " + CurrentUI.getUser().getPoints(), 570, 590);

        sceneNodes.getChildren().add(points);
    }

    /**
     * Adds to the scene a <code>RegularButton</code> for saving the changes.
     */
    private void displaySaveButton() {
        RegularButton saveButton = new RegularButton("SAVE", 1000, 600);
        saveButton.setOnAction(e -> saveButtonAction());

        sceneNodes.getChildren().add(saveButton);
    }

    private void saveButtonAction() {
        // save the team name
        UserManagerOnMongoDb userManagerOnMongoDb = new UserManagerOnMongoDb();
        userManagerOnMongoDb.changeTeamName(CurrentUI.getUser(), teamNameTF.getText());

        // remove pokemon removed
        TeamManagerOnNeo4j teamManagerOnNeo4j = new TeamManagerOnNeo4j();
        for (int i = 0; i < 6; ++i) {
            if (pokePaneArray[i].isChanged()) {
                CurrentUI.getUser().removeFromTeam(i);
                teamManagerOnNeo4j.deletePokemonFromTeamBySlot(CurrentUI.getUser(), i);
            }
        }

        // save the points
        userManagerOnMongoDb.updatePoints(CurrentUI.getUser(), CurrentUI.getUser().getPoints());

        points.setText("Points: " + CurrentUI.getUser().getPoints());
    }

    private Pokemon[] retrieveTeam() {
        TeamManagerOnNeo4j teamManagerOnNeo4j = new TeamManagerOnNeo4j();
        return teamManagerOnNeo4j.getUserTeam(CurrentUI.getUser());
    }
}
