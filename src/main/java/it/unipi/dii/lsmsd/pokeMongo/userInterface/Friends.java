package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.FriendsSearchForUserPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.RankingScollPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.RecommendedUserPane;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

public class Friends extends PokeSceneWithHeaderAndBackButton {

    /**
     * Constructor. Calls a series of function in order to display every element.
     */
    public Friends() {
        Logger.log("SHOWING FRIENDS PAGE");

        displayFriendList();
        displaySearchUser();
        displayRecommendedUser();

        setSceneMusic("friends.mp3");
    }

    /**
     * Shows up a Pane with the username of all the trainers considered as friends
     */
    private void displayFriendList() {
        FieldRelatedLabel friendsLabel = new FieldRelatedLabel("FRIENDS", 100, 70);

        RankingScollPane friendsRanking = new RankingScollPane(100, 100, 320, 470, RankingTypes.FRIENDS);

        sceneNodes.getChildren().addAll(friendsLabel, friendsRanking);
    }

    /**
     * Shows up a Pane that lets the user to search for another user
     */
    private void displaySearchUser() {
        FriendsSearchForUserPane friendsSearchForUserPane = new FriendsSearchForUserPane(470, 70, 650, 250);

        sceneNodes.getChildren().add(friendsSearchForUserPane);
    }

    /**
     * Shows up a Pane containing two scroll panes with two different types of recommended users
     */
    private void displayRecommendedUser() {
        RecommendedUserPane recommendedUserPane = new RecommendedUserPane(470, 330, 650, 250);

        sceneNodes.getChildren().add(recommendedUserPane);
    }
}
