package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import javafx.scene.layout.Pane;

/**
 * Pane that displays the two types of recommended users
 */
public class RecommendedUserPane extends Pane {
    FriendsScrollPane recommendedByFriends;
    FriendsScrollPane recommendedByPokemon;

    /**
     *
     * @param x the x axis position
     * @param y the y axis position
     * @param width the width you want to set
     * @param height the height you want to set
     */
    public RecommendedUserPane(int x, int y, int width, int height) {
        setStyle("-fx-background-color: #efefef;");
        relocate(x, y);
        setPrefSize(width, height);

        displayRecommendedByFriends();
        displayrecommendedByPokemon();
    }

    /**
     * Displays a ScrollPane with the friend recommended users
     */
    public void displayRecommendedByFriends() {
        FieldRelatedLabel byFriend = new FieldRelatedLabel("RECOMMENDED BY FRIENDS", 2, 10);

        recommendedByFriends = new FriendsScrollPane(2, 35, 320, 200);
        recommendedByFriends.insertSuggestionsByFriends();

        getChildren().addAll(byFriend, recommendedByFriends);
    }

    /**
     * Displays a ScrollPane with the pokemon recommended users
     */
    public void displayrecommendedByPokemon() {
        FieldRelatedLabel byPokemon = new FieldRelatedLabel("RECOMMENDED BY POKEMON", 328, 10);

        recommendedByPokemon = new FriendsScrollPane(328, 35, 320, 200);
        recommendedByPokemon.insertSuggestionsByPokemon();

        getChildren().addAll(byPokemon, recommendedByPokemon);
    }
}
