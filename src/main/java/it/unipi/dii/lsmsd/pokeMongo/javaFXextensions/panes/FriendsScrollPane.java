package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.dataAnalysis.UserRankerFactory;

import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class FriendsScrollPane extends ScrollPane {
    private VBox root;

    public FriendsScrollPane(int x, int y, int width, int height) {
        setPrefSize(width,height);
        relocate(x, y);

        root = new VBox();
        root.setSpacing(5);
        setContent(root);
    }

    public void insertSuggestions(String pattern) {
        root.getChildren().clear();

        if (pattern.equals(""))
            return;

        // RETRIEVE USERNAMES FROM NEO4J, THEN RETRIEVE USERS FROM MONGO
        UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
        List<String> username = userNetworkManager.getUserBySearch(pattern);
        //System.out.println(username.get(0));
        List<User> friendsUser = (UserRankerFactory.buildRanker()).bestFriendsTeams(username);

        // ADD IN ROOT
        for (User user : friendsUser) {
            RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
            root.getChildren().add(rankingSingleUserResult);
        }
    }

    public void insertSuggestionsByPokemon() {
        // RETRIEVE USERNAMES FROM NEO4J, THEN RETRIEVE USERS FROM MONGO
        UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
        List<String> username = userNetworkManager.getSuggestedUserByFavoritesPokemon(CurrentUI.getUser());
        //System.out.println(username.get(0));
        List<User> friendsUser = (UserRankerFactory.buildRanker()).bestFriendsTeams(username);

        // ADD IN ROOT
        for (User user : friendsUser) {
            RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
            root.getChildren().add(rankingSingleUserResult);
        }
    }

    public void insertSuggestionsByFriends() {
        // RETRIEVE USERNAMES FROM NEO4J, THEN RETRIEVE USERS FROM MONGO
        UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
        List<String> username = userNetworkManager.getSuggestedUser(CurrentUI.getUser());
        //System.out.println(username.get(0));
        List<User> friendsUser = (UserRankerFactory.buildRanker()).bestFriendsTeams(username);

        // ADD IN ROOT
        for (User user : friendsUser) {
            RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
            root.getChildren().add(rankingSingleUserResult);
        }
    }
}
