package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.SubPostInsertCommentPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.vBox.SubPostsVBox;
import javafx.scene.control.Button;;

public class PostButton extends Button {
    private String text;
    private SubPostsVBox subPostsVBox;
    private Post currentPost;
    private int numberOfAnswers;

    private static boolean canComment = false;

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

            canComment = true;
            addCommentPane();

        } else if (text.equals("Uncomment")) {
            text = "Comment";
            setText(text);
            canComment = false;

        } else if (text.startsWith("Answers")) {
            text = "Show less";
            setText(text);

            

            if (canComment)
                addCommentPane();

        } else if (text.startsWith("Show less")) {
            text = "Answers (" + numberOfAnswers + ")";
            setText(text);

            if (canComment)
                addCommentPane();

        }

    }

    private void addCommentPane() {
        SubPostInsertCommentPane subPostInsertCommentPane = new SubPostInsertCommentPane(currentPost);
        subPostsVBox.getChildren().addAll(subPostInsertCommentPane);
    }

}
