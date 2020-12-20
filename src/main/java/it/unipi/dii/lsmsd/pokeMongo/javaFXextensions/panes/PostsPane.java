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

/**
 * Contains all the nodes that created the Post part of the <code>PokemonWindowGroup</code>
 */
public class PostsPane extends Pane {
    private TextArea postArea;
    private String pokemonName;

    PostsPresentScrollPane postsPresentScrollPane;

    /**
     *
     * @param x the x axis position
     * @param y the y axis position
     * @param width the width you want to set
     * @param height the height you want to set
     * @param pokemonName the name of the pokemon this PostsPane is related
     */
    public PostsPane(int x, int y, int width, int height, String pokemonName) {
        setPrefSize(width, height);
        relocate(x, y);
        setStyle("-fx-background-color: #eaeaea;");

        this.pokemonName = pokemonName;

        displayPostsPresent();

        if (!CurrentUI.getUser().isAdmin()) {
            displayTextArea();
            displayButtonPost();
        }
    }

    /**
     * Displays the post present
     */
    public void displayPostsPresent() {
        postsPresentScrollPane = new PostsPresentScrollPane(15, 15, 400, 365, pokemonName);

        getChildren().add(postsPresentScrollPane);
    }

    /**
     * Displays a text area where the user can use to publish a new post
     */
    private void displayTextArea() {
        postArea = new TextArea();
        postArea.setPrefSize(400, 50);
        postArea.relocate(15, 400);
        postArea.setWrapText(true);

        getChildren().add(postArea);
    }

    /**
     * Displays a button for publishing what's inside the <code>postArea</code>
     */
    private void displayButtonPost() {
        Button postButton = new Button("POST");
        postButton.relocate(363, 463);
        postButton.setStyle("-fx-background-color: white; -fx-border-color: black;");
        postButton.setOnAction(e -> post());

        getChildren().add(postButton);
    }

    /**
     * Action of the POST button. It checks if <code>postArea</code> is empty, if it is then returns. Otherwise
     * the function add it to the ScrollPane where all the post are present and in the db
     */
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

            getChildren().remove(postsPresentScrollPane);
            displayPostsPresent();
        } catch (DuplicatePostException e) {
            e.printStackTrace();
        }
    }
}
