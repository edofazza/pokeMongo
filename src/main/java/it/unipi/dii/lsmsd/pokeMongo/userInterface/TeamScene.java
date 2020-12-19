package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.PokemonPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.TeamNameTextField;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
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
        double currentPoints = CurrentUI.getUser().getPoints();
        currentPoints = Math.round(currentPoints*100);
        currentPoints = currentPoints/100;

        points = new FieldRelatedLabel("Points: " + currentPoints, 570, 590);

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

    /**
     * Action of the SAVE button. It simply save the information of the team at the moment of the click
     */
    private void saveButtonAction() {
        // save the team name
        UserManager userManager = UserManagerFactory.buildManager();
        userManager.changeTeamName(CurrentUI.getUser(), teamNameTF.getText());

        CurrentUI.getUser().setTeamName(teamNameTF.getText());

        // remove pokemon removed
        TeamManager teamManager = TeamManagerFactory.buildManager();
        for (int i = 0; i < 6; ++i) {
            if (pokePaneArray[i].isChanged()) {
                CurrentUI.getUser().removeFromTeam(i);
                teamManager.deletePokemonFromTeamBySlot(CurrentUI.getUser(), i);
            }
        }

        // save the points
        userManager.updatePoints(CurrentUI.getUser(), CurrentUI.getUser().getPoints());

        points.setText("Points: " + CurrentUI.getUser().getPoints());
    }

    /**
     * This function queries the db in order to get information about the team of the current user
     * @return an array of Pokemons
     */
    private Pokemon[] retrieveTeam() {
        TeamManager teamManager = TeamManagerFactory.buildManager();
        return teamManager.getUserTeam(CurrentUI.getUser());
    }
}
