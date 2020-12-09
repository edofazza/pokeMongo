package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class SubPostPane extends Pane {
    private Post post;

    public SubPostPane(Post post) {
        setPrefSize(340, 85);
        relocate(55, 0);
        setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-border-color: grey; -fx-border-radius: 15;");

        this.post = post;

        displayUsername();
        displayDate();
        displayContent();
    }

    private void displayUsername() {
        Label username = new Label("USER: " + post.getAuthorUsername());
        username.relocate(5, 5);

        getChildren().add(username);
    }

    private void displayDate() {
        Label date = new Label(post.getFormattedDate());
        date.relocate(210, 5);

        getChildren().add(date);
    }

    private void displayContent() {
        TextArea content = new TextArea(post.getContent());
        content.setPrefSize(332, 30);
        content.relocate(5, 24);
        content.setWrapText(true);
        content.setEditable(false);

        getChildren().add(content);
    }

}
