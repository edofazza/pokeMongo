package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class FriendsScrollPane extends ScrollPane {
    private VBox root;

    public FriendsScrollPane(int x, int y, int width, int height) {
        setPrefSize(width,height);
        relocate(x, y);

        root = new VBox();
        root.setSpacing(5);
        setContent(root);
    }
}
