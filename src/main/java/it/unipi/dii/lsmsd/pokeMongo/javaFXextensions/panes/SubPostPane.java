package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.DeleteSubPostButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.PostButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.vBox.SubPostsVBox;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class SubPostPane extends Pane {
    private Post post;
    private SubPostsVBox rootFather;
    private PostButton postButton;

    /**
     *
     * @param post the post this subpost is related to
     * @param rootFather the <code>SubPostsVBox</code>
     * @param p the <code>PostButton</code> this subpost is related to
     */
    public SubPostPane(Post post, SubPostsVBox rootFather, PostButton p) {
        setPrefSize(340, 85);
        relocate(55, 0);
        setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-border-color: grey; -fx-border-radius: 15;");

        this.post = post;
        this.rootFather = rootFather;
        this.postButton = p;

        displayUsername();

        if (post.getAuthorUsername().equals(CurrentUI.getUser().getUsername()) || CurrentUI.getUser().isAdmin())
            displayDeleteButton();

        displayDate();
        displayContent();
    }

    /**
     * Display the username of the author
     */
    private void displayUsername() {
        Label username = new Label("USER: " + post.getAuthorUsername());
        username.relocate(5, 5);

        getChildren().add(username);
    }

    /**
     * Only if the user logged is the author or an admin. This method add a delete button to the <code>PostPane</code>
     */
    private void displayDeleteButton() {
        DeleteSubPostButton deleteSubPostButton = new DeleteSubPostButton("Delete", 140, 0, this);

        getChildren().add(deleteSubPostButton);
    }

    /**
     * Displays the published date
     */
    private void displayDate() {
        Label date = new Label(post.getFormattedDate());
        date.relocate(210, 5);

        getChildren().add(date);
    }

    /**
     * Displays the content of the post
     */
    private void displayContent() {
        TextArea content = new TextArea(post.getContent());
        content.setPrefSize(332, 30);
        content.relocate(5, 24);
        content.setWrapText(true);
        content.setEditable(false);

        getChildren().add(content);
    }

    /**
     * Removes the PostPane from the <code>fatherRoot</code>
     */
    public void removeItself() {
        postButton.answerRemoved();
        rootFather.getChildren().remove(this);
    }

    /**
     *
     * @return <code>post</code>
     */
    public Post getPost() {
        return post;
    }
}
