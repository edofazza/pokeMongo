package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import javafx.scene.layout.Pane;

public class RecommendedUserPane extends Pane {
    FriendsScrollPane recommendedByFriends;
    FriendsScrollPane recommendedByPokemon;

    public RecommendedUserPane(int x, int y, int width, int height) {
        setStyle("-fx-background-color: #efefef;");
        relocate(x, y);
        setPrefSize(width, height);

        displayRecommendedByFriends();
        displayrecommendedByPokemon();
    }

    public void displayRecommendedByFriends() {
        FieldRelatedLabel byFriend = new FieldRelatedLabel("RECOMMENDED BY FRIENDS", 2, 10);

        recommendedByFriends = new FriendsScrollPane(2, 35, 320, 200);
        recommendedByFriends.insertSuggestionsByFriends();

        getChildren().addAll(byFriend, recommendedByFriends);
    }

    public void displayrecommendedByPokemon() {
        FieldRelatedLabel byPokemon = new FieldRelatedLabel("RECOMMENDED BY POKEMON", 328, 10);

        recommendedByPokemon = new FriendsScrollPane(328, 35, 320, 200);
        recommendedByPokemon.insertSuggestionsByPokemon();

        getChildren().addAll(byPokemon, recommendedByPokemon);
    }
}
