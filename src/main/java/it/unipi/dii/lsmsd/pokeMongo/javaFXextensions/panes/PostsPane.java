package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.exceptions.DuplicatePostException;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.time.LocalDateTime;

public class PostsPane extends Pane {
    private TextArea postArea;
    private String pokemonName;

    public PostsPane(int x, int y, int width, int height, String pokemonName) {
        setPrefSize(width, height);
        relocate(x, y);
        setStyle("-fx-background-color: #eaeaea;");

        this.pokemonName = pokemonName;

        displayPostsPresent();
        displayTextArea();
        displayButtonPost();
    }

    public void displayPostsPresent() {
        PostsPresentScrollPane postsPresentScrollPane = new PostsPresentScrollPane(15, 15, 400, 365, pokemonName);

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

        LocalDateTime localDateTime = LocalDateTime.now();

        // PUBLISH POST ON NEO4J
        PostManager postManagerFactory =  PostManagerFactory.buildManager();
        try {
            postManagerFactory.insertPost(
                    new Post(CurrentUI.getUser().getUsername(),
                            postArea.getText(),
                            localDateTime,
                            pokemonName));
        } catch (DuplicatePostException e) {
            e.printStackTrace();
        }
    }
}
