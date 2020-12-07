package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.dataAnalysis.UserRanker;
import it.unipi.dii.lsmsd.pokeMongo.dataAnalysis.UserRankerFactory;

import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManagerFactory;
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

        // RETRIEVE USERNAMES FROM NEO4J, THEN RETRIEVE USERS FROM MONGO
        UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
        List<String> username = userNetworkManager.getUserBySearch(pattern);
        System.out.println(username.get(0));
        List<User> friendsUser = (UserRankerFactory.buildRanker()).bestFriendsTeams(username);

        System.out.println("//////////////// " + friendsUser.size());
        // ADD IN ROOT
        for (User user : friendsUser) {
            RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
            root.getChildren().add(rankingSingleUserResult);
        }
    }
}
