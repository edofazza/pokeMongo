package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.SubPostInsertCommentPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.SubPostPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.vBox.SubPostsVBox;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManagerFactory;
import javafx.scene.control.Button;;import java.time.LocalDateTime;
import java.util.List;

public class PostButton extends Button {
    private String text;
    private SubPostsVBox subPostsVBox;
    private Post currentPost;
    private int numberOfAnswers;

    private static boolean canComment = false;
    private static boolean answersPresent = false;

    public PostButton(String text, int x, int y, SubPostsVBox subPostsVBox, Post currentPost, int numberOfAnswers) {
        super(text);
        relocate(x, y);
        setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #70709d;");

        this.text = text;
        this.subPostsVBox = subPostsVBox;
        this.currentPost = currentPost;
        this.numberOfAnswers = numberOfAnswers;

        setOnAction(e -> fillVBox());
    }

    private void fillVBox() {
        subPostsVBox.getChildren().clear();

        if (text.equals("Comment")) {
            text = "Uncomment";
            setText(text);

            if(answersPresent)
                addReplies();

            canComment = true;
            addCommentPane();

        } else if (text.equals("Uncomment")) {
            text = "Comment";
            setText(text);
            canComment = false;

            if(answersPresent)
                addReplies();

        } else if (text.startsWith("Answers")) {
            text = "Show less";
            setText(text);

            addReplies();
            answersPresent = true;

            if (canComment)
                addCommentPane();

        } else if (text.startsWith("Show less")) {
            text = "Answers (" + numberOfAnswers + ")";
            setText(text);

            answersPresent = false;

            if (canComment)
                addCommentPane();

        }

    }

    private void addCommentPane() {
        SubPostInsertCommentPane subPostInsertCommentPane = new SubPostInsertCommentPane(currentPost);
        subPostsVBox.getChildren().addAll(subPostInsertCommentPane);
    }

    private void addReplies() {
        PostManager postManagerFactory = PostManagerFactory.buildManager();
        List<Post> subpostList = postManagerFactory.getPostsByPost(currentPost);
        for (Post p: subpostList) {
            SubPostPane subPostPane = new SubPostPane(p);
            subPostsVBox.getChildren().addAll(subPostPane);
        }
    }

}
