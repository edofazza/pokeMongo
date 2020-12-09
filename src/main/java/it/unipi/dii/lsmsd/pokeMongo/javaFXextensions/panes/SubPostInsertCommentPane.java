package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.exceptions.DuplicatePostException;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.PostButton;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.time.LocalDateTime;

public class SubPostInsertCommentPane extends Pane {
    private Post currentPost;
    private TextArea writePost;

    private PostButton postButtonAnswer;

    public SubPostInsertCommentPane(Post currentPost, PostButton postButtonAnswer) {
        this.currentPost = currentPost;
        this.postButtonAnswer = postButtonAnswer;

        writePost = new TextArea();
        writePost.setPrefSize(290, 30);
        writePost.setWrapText(true);

        Button postButton = new Button("POST");
        postButton.relocate(300, 7);
        postButton.setStyle("-fx-background-color: white; -fx-border-color: black;");
        postButton.setOnAction(e -> post());

        getChildren().addAll(writePost, postButton);
    }

    private void post() {
        if(writePost.getText().equals(""))
            return;

        LocalDateTime localDateTime = LocalDateTime.now();

        // PUBLISH POST ON NEO4J
        PostManager postManagerFactory =  PostManagerFactory.buildManager();
        try {
            postManagerFactory.insertResponse( new Post(
                    CurrentUI.getUser().getUsername(),
                    writePost.getText(),
                    localDateTime,
                    currentPost.getPokemonName()
            ), currentPost);

            postButtonAnswer.newAnswerPosted();
        } catch (DuplicatePostException e) {
            e.printStackTrace();
        }
    }
}
