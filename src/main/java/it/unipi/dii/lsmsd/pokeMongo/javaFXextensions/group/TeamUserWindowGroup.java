package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.group;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.PokemonTeamForUserSelectedWindow;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManager;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.Group;
import javafx.scene.control.Label;

public class TeamUserWindowGroup extends Group {
    User user;

    // TODO: retrieve the data from the user
    public TeamUserWindowGroup(User user) {
        Logger.vvlog("Creating TeamUserWindowGroup");

        this.user = user;

        // retrieve the team
        TeamManager teamManager = TeamManagerFactory.buildManager();
        user.addTeam(teamManager.getUserTeam(user));

        displayTeamName();

        displayPokemons();
    }

    private void displayTeamName() {
        Label teamLabel = new Label(user.getTeamName());
        teamLabel.setStyle("-fx-font-size: 23; -fx-font-family: 'Arial Black'; -fx-text-fill: blue;");
        teamLabel.relocate(227, 5);

        getChildren().addAll(teamLabel);
    }

    private void displayPokemons() {
        for (int i = 0; i < 3; i++) {
            PokemonTeamForUserSelectedWindow p0 = new PokemonTeamForUserSelectedWindow(user.getFromTeam(i), 10, 50+i*(35+90));
            getChildren().add(p0);
        }
        for (int i = 0; i < 3; i++) {
            PokemonTeamForUserSelectedWindow p = new PokemonTeamForUserSelectedWindow(user.getFromTeam(i+3), 320, 50+i*(35+90));
            getChildren().add(p);
        }
    }
}
