package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerOnMongoDb;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManagerOnNeo4j;
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
        UserNetworkManagerOnNeo4j userNetworkManagerOnNeo4j = new UserNetworkManagerOnNeo4j();
        List<String> username = userNetworkManagerOnNeo4j.getUserBySearch(pattern);
        System.out.println(username.get(0));
        List<User> friendsUser = (new UserManagerOnMongoDb()).bestFriendsTeams(username);

        System.out.println("//////////////// " + friendsUser.size());
        // ADD IN ROOT
        for (User user : friendsUser) {
            RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
            root.getChildren().add(rankingSingleUserResult);
        }
    }
}
