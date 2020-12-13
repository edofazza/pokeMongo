package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.UsernameLinkTeamButton;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class RankingSingleUserResult extends Pane {

    // TODO: points type should be decided
    public RankingSingleUserResult(User user) {
        Logger.vvlog("Creating RankingSingleUserResult for " + user.getUsername() + ": points = " + user.getPoints());
        UsernameLinkTeamButton usernameButton = new UsernameLinkTeamButton(user, 10, 12);

        //Label teamNameLabel = new Label(user.getTeamName());
        //teamNameLabel.relocate(100, 18);

        Label pointsLabel = new Label(Double.toString(user.getPoints()));
        pointsLabel.relocate(220, 18);

        getChildren().addAll(usernameButton, pointsLabel);

        getStyleClass().add("PokemonResultPane");
    }
}
