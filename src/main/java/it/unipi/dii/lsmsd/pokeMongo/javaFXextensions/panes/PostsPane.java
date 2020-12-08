package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class PostsPane extends Pane {
    private TextArea postArea;

    public PostsPane(int x, int y, int width, int height) {
        setPrefSize(width, height);
        relocate(x, y);
        setStyle("-fx-background-color: #eaeaea;");

        displayPostsPresent();
        displayTextArea();
        displayButtonPost();
    }

    public void displayPostsPresent() {
        PostsPresentScrollPane postsPresentScrollPane = new PostsPresentScrollPane(15, 15, 400, 365);

        getChildren().add(postsPresentScrollPane);
    }

    private void displayTextArea() {
        postArea = new TextArea();
        postArea.setPrefSize(400, 50);
        postArea.relocate(15, 400);
        postArea.setWrapText(true);

        getChildren().add(postArea);
    }

    private void displayButtonPost() {
        Button postButton = new Button("POST");
        postButton.relocate(363, 463);
        postButton.setStyle("-fx-background-color: white; -fx-border-color: black;");
        postButton.setOnAction(e -> post());

        getChildren().add(postButton);
    }

    private void post() {
        if(postArea.getText().equals(""))
            return;

        // PUBLISH POST ON NEO4J
    }
}
