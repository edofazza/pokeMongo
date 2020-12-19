package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.UsernameLinkTeamButton;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class RankingSingleUserResult extends Pane {

    public RankingSingleUserResult(User user) {
        Logger.vvlog("Creating RankingSingleUserResult for " + user.getUsername() + ": points = " + user.getPoints());
        UsernameLinkTeamButton usernameButton = new UsernameLinkTeamButton(user, 10, 12);

        double currentPoint = user.getPoints()*100;
        double roundedPoints = Math.round(currentPoint);
        Label pointsLabel = new Label(Double.toString(roundedPoints/100));
        pointsLabel.relocate(220, 18);

        getChildren().addAll(usernameButton, pointsLabel);

        getStyleClass().add("PokemonResultPane");
    }
}
