package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class PostsPane extends Pane {
    public PostsPane(int x, int y, int width, int height) {
        setPrefSize(width, height);
        relocate(x, y);
        setStyle("-fx-background-color: #eaeaea;");

        displayTextArea();
        displayButtonPost();
    }

    private void displayTextArea() {
        TextArea postArea = new TextArea();
        postArea.setPrefSize(400, 50);
        postArea.relocate(15, 400);
        postArea.setWrapText(true);

        getChildren().add(postArea);
    }

    private void displayButtonPost() {
        Button postButton = new Button("POST");
        postButton.relocate(363, 463);
        postButton.setStyle("-fx-background-color: white; -fx-border-color: black;");

        getChildren().add(postButton);
    }
}
