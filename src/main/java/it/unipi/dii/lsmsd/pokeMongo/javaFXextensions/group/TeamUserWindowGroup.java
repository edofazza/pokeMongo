package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.group;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.PokemonTeamForUserSelectedWindow;
import it.unipi.dii.lsmsd.pokeMongo.persistence.*;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * This particular Group is used for creating the elements to be added in the
 * window which shows the information of a team related to a specific user.
 */
public class TeamUserWindowGroup extends Group {
    private User user;
    private Button follow;

    /**
     * Constructs the scene
     * @param user the user the stage is related to
     */
    public TeamUserWindowGroup(User user) {
        Logger.vvlog("Creating TeamUserWindowGroup");

        this.user = user;

        // retrieve the team
        TeamManager teamManager = TeamManagerFactory.buildManager();
        user.addTeam(teamManager.getUserTeam(user));

        if (!CurrentUI.getUser().getUsername().equals(user.getUsername()) && !CurrentUI.getUser().isAdmin())
            displayFollowButton();

        displayTeamName();

        displayPokemons();
    }

    /**
     * Displays the name of the team
     */
    private void displayTeamName() {
        Label teamLabel = new Label(user.getTeamName());
        teamLabel.setStyle("-fx-font-size: 23; -fx-font-family: 'Arial Black'; -fx-text-fill: blue;");
        teamLabel.relocate(227, 5);

        getChildren().addAll(teamLabel);
    }


    /**
     * Displays the following button
     */
    private void displayFollowButton() {
        UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();

        if (userNetworkManager.isFollowing(CurrentUI.getUser().getUsername(), user.getUsername()))
            follow = new Button("UNFOLLOW");
        else
            follow = new Button("FOLLOW");

        follow.setStyle("-fx-background-color: transparent; -fx-border-radius: 10; -fx-border-color: #4aa7e9; -fx-border-width: 2;" +
                " -fx-text-fill: #4aa7e9; -fx-text-alignment: center;");
        follow.setPrefSize(140, 36);
        follow.relocate(395, 5);

        follow.setOnAction(e -> followUnfollow() );

        getChildren().addAll(follow);
    }

    /**
     * Handles the follow or unfollow action
     */
    private void followUnfollow() {
        UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();

        if (!userNetworkManager.isFollowing(CurrentUI.getUser().getUsername(), user.getUsername())) {
            // CHANGE THE LABEL
            follow.setText("UNFOLLOW");

            // ADD FOLLOWING RELATIONSHIP
            userNetworkManager.addFollow(CurrentUI.getUser(), user);
        }
        else {
            follow.setText("FOLLOW");

            userNetworkManager.removeFollow(CurrentUI.getUser(), user);
        }
    }

    /**
     * Display the pokemons of the user this page is related to
     */
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
