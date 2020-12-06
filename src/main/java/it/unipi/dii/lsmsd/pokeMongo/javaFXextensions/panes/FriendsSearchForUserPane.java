package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import javafx.scene.layout.Pane;

public class FriendsSearchForUserPane extends Pane {
    FriendsScrollPane friendsScrollPaneFiltered;

    public FriendsSearchForUserPane(int x, int y, int width, int height) {
        setStyle("-fx-background-color: #efefef;");
        relocate(x, y);
        setPrefSize(width, height);

        FieldRelatedLabel textFieldTitle = new FieldRelatedLabel("SEARCH USERS", 75, 100);

        CatchEmAllTextField userSearchInput = new CatchEmAllTextField("Type for searching", 50, 130);
        userSearchInput.setOnKeyReleased(e->loadUserInfoByUsername(userSearchInput.getText()));

        friendsScrollPaneFiltered = new FriendsScrollPane(300, 30, 320, 200);

        getChildren().addAll(textFieldTitle, userSearchInput, friendsScrollPaneFiltered);
    }

    public void loadUserInfoByUsername(String pattern) {
        friendsScrollPaneFiltered.insertSuggestions(pattern);
    }
}
