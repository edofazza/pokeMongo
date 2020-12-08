package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.SubPostInsertCommentPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.vBox.SubPostsVBox;
import javafx.scene.control.Button;;

public class PostButton extends Button {
    private String text;
    private SubPostsVBox subPostsVBox;
    private Post currentPost;

    public PostButton(String text, int x, int y, SubPostsVBox subPostsVBox, Post currentPost) {
        super(text);
        relocate(x, y);
        setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #70709d;");

        this.text = text;
        this.subPostsVBox = subPostsVBox;

        setOnAction(e -> fillVBox());
    }

    private void fillVBox() {
        subPostsVBox.getChildren().clear();
        switch (text) {
            case "Comment":
                text = "Uncomment";
                setText(text);

                SubPostInsertCommentPane subPostInsertCommentPane = new SubPostInsertCommentPane(currentPost);

                subPostsVBox.getChildren().addAll(subPostInsertCommentPane);
                break;
            case "Uncomment":
                text = "Comment";
                setText(text);
                break;

        }
    }
}
