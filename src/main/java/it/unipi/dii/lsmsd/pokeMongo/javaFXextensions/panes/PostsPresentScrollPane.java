package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PostsPresentScrollPane extends ScrollPane {
    private VBox root;

    public PostsPresentScrollPane(int x, int y, int width, int height) {
        relocate(x, y);
        setPrefSize(width, height);

        loadPosts();
    }

    private void loadPosts() {
        Pane postTest = new Pane();

    }
}
