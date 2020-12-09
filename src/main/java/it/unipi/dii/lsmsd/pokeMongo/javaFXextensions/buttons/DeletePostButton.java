package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.PostPane;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManagerFactory;
import javafx.scene.control.Button;

public class DeletePostButton extends Button {
    private Post post;
    private PostPane postPane;

    public DeletePostButton(String text, int x, int y, Post post, PostPane postPane) {
        super(text);
        relocate(x, y);
        setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #f36666;");

        this.post = post;
        this.postPane = postPane;

        setOnAction(e -> deletePost());
    }

    private void deletePost() {
        PostManager postManager = PostManagerFactory.buildManager();
        postManager.deletePost(post);

        postPane.removeItself();
    }
}
