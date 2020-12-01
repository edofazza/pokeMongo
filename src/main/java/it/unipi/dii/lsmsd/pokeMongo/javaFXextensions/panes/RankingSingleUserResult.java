package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.UsernameLinkTeamButton;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class RankingSingleUserResult extends Pane {

    // TODO: points type should be decided
    public RankingSingleUserResult(String username, String teamName, double points) {
        Logger.vvlog("Creating RankingSingleUserResult for " + username + ": points = " + points);
        UsernameLinkTeamButton usernameButton = new UsernameLinkTeamButton(username, 10, 12);

        Label teamNameLabel = new Label(teamName);
        teamNameLabel.relocate(100, 18);

        Label pointsLabel = new Label(Double.toString(points));
        pointsLabel.relocate(220, 18);

        getChildren().addAll(usernameButton, teamNameLabel, pointsLabel);

        getStyleClass().add("PokemonResultPane");
    }
}
