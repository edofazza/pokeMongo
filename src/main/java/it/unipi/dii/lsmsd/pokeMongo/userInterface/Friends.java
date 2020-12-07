package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.FriendsSearchForUserPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.RankingScollPane;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.RecommendedUserPane;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

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
        RecommendedUserPane recommendedUserPane = new RecommendedUserPane(470, 330, 650, 250);

        sceneNodes.getChildren().add(recommendedUserPane);
    }
}
