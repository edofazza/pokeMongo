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

    /**
     *
     * @param x the x axis position
     * @param y the y axis position
     * @param width the width you want to set
     * @param height the height you want to set
     */
    public FriendsScrollPane(int x, int y, int width, int height) {
        setPrefSize(width,height);
        relocate(x, y);

        root = new VBox();
        root.setSpacing(5);
        setContent(root);
    }

    /**
     * Inserts all types of suggested users
     * @param pattern pattern given in input by the user, used for retrieve all the other users that starts with that
     *                pattern
     */
    public void insertSuggestions(String pattern) {
        root.getChildren().clear();

        if (pattern.equals(""))
            return;

        // RETRIEVE USERNAMES FROM NEO4J, THEN RETRIEVE USERS FROM MONGO
        UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
        List<String> username = userNetworkManager.getUserBySearch(pattern);

        List<User> friendsUser = (UserRankerFactory.buildRanker()).bestFriendsTeams(username);

        // ADD IN ROOT
        for (User user : friendsUser) {
            RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
            root.getChildren().add(rankingSingleUserResult);
        }
    }

    /**
     * Inserts the users suggested by favorite pokemon
     */
    public void insertSuggestionsByPokemon() {
        // RETRIEVE USERNAMES FROM NEO4J, THEN RETRIEVE USERS FROM MONGO
        UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
        List<String> username = userNetworkManager.getSuggestedUserByFavoritesPokemon(CurrentUI.getUser());

        List<User> friendsUser = (UserRankerFactory.buildRanker()).bestFriendsTeams(username);

        // ADD IN ROOT
        for (User user : friendsUser) {
            RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
            root.getChildren().add(rankingSingleUserResult);
        }
    }

    /**
     * Inserts the users suggested by the user we're following
     */
    public void insertSuggestionsByFriends() {
        // RETRIEVE USERNAMES FROM NEO4J, THEN RETRIEVE USERS FROM MONGO
        UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
        List<String> username = userNetworkManager.getSuggestedUser(CurrentUI.getUser());
        List<User> friendsUser = (UserRankerFactory.buildRanker()).bestFriendsTeams(username);

        // ADD IN ROOT
        for (User user : friendsUser) {
            RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
            root.getChildren().add(rankingSingleUserResult);
        }
    }
}
