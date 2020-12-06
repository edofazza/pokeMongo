package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.FriendsScrollPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.FriendsSearchForUserPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.RankingScollPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.layout.Pane;

public class Friends extends PokeSceneWithHeaderAndBackButton {
    public Friends() {
        Logger.log("SHOWING FRIENDS PAGE");

        displayFriendList();
        displaySearchUser();
        displayRecommendedUser();

        setSceneMusic("friends.mp3");
    }

    private void displayFriendList() {
        FieldRelatedLabel friendsLabel = new FieldRelatedLabel("FRIENDS", 100, 70);

        RankingScollPane friendsRanking = new RankingScollPane(100, 100, 320, 470, RankingTypes.FRIENDS);

        sceneNodes.getChildren().addAll(friendsLabel, friendsRanking);
    }

    private void displaySearchUser() {
        FriendsSearchForUserPane friendsSearchForUserPane = new FriendsSearchForUserPane(470, 70, 650, 250);

        sceneNodes.getChildren().add(friendsSearchForUserPane);
    }

    private void displayRecommendedUser() {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: #efefef;");
        pane.relocate(470, 330);
        pane.setPrefSize(650, 250);

        sceneNodes.getChildren().add(pane);
    }
}
