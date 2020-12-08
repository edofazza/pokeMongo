package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;


public class PostPane extends Pane {
    private Post post;

    public PostPane(Post post, int numberOfAnswers) {
        setMinHeight(80);
        setPrefWidth(380);
        setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-border-color: grey; -fx-border-radius: 15;");

        this.post = post;

        displayUsername();
        displayDate();
        displayContent();
    }

    private void displayUsername() {
        Label username = new Label("USER:" + post.getAuthorUsername());
        //Label username = new Label("USER: goofy");
        username.relocate(5, 5);

        getChildren().add(username);
    }

    private void displayDate() {
        Label date = new Label(post.getPublishDate().toString());
        //Label date = new Label((new Date()).toString());
        date.relocate(200, 5);

        getChildren().add(date);
    }

    private void displayContent() {
        //TextArea content = new TextArea("I like Digimon more than Pokemon");
        TextArea content = new TextArea(post.getContent());
        content.setPrefSize(368, 30);
        content.relocate(5, 24);
        content.setWrapText(true);
        content.setEditable(false);

        getChildren().add(content);
    }
}
