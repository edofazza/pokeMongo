package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.DeletePostButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.PostButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.vBox.SubPostsVBox;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class PostPane extends Pane {
    private Post post;
    private int numberOfAnswers;
    private VBox fatherRoot;

    /**
     *
     * @param post the post this pane is related
     * @param numberOfAnswers number of answer of a post
     * @param fatherRoot the VBox this PostPane is attached
     */
    public PostPane(Post post, int numberOfAnswers, VBox fatherRoot) {
        setMinHeight(90);
        setPrefWidth(380);
        setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-border-color: grey; -fx-border-radius: 15;");

        this.post = post;
        this.numberOfAnswers = numberOfAnswers;
        this.fatherRoot = fatherRoot;

        displayUsername();
        displayDate();

        if (post.getAuthorUsername().equals(CurrentUI.getUser().getUsername()) || CurrentUI.getUser().isAdmin())
            displayDeleteButton();

        displayContent();
        displayButtons();
    }

    /**
     * Displays the username of the author
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
        DeletePostButton deletePostButton = new DeletePostButton("Delete", 160, 0, this);

        getChildren().add(deletePostButton);
    }

    /**
     * Displays the published date
     */
    private void displayDate() {
        Label date = new Label(post.getFormattedDate());
        date.relocate(250, 5);

        getChildren().add(date);
    }

    /**
     * Displays the content of the post
     */
    private void displayContent() {
        TextArea content = new TextArea(post.getContent());
        content.setPrefSize(368, 30);
        content.relocate(5, 24);
        content.setWrapText(true);
        content.setEditable(false);

        getChildren().add(content);
    }

    /**
     * Displays the two <code>PostButton</code> for commenting and viewing the answers
     */
    private void displayButtons() {
        SubPostsVBox subPostsVBox = new SubPostsVBox(20, 95);
        PostButton answers = new PostButton("Answers (" + numberOfAnswers + ")" , 90, 58, subPostsVBox, post, numberOfAnswers);
        PostButton comment = new PostButton("Comment", 5, 58, subPostsVBox, post, numberOfAnswers, answers);
        answers.setCommentButton(comment);

        getChildren().addAll(comment, answers, subPostsVBox);
    }

    /**
     * Removes the PostPane from the <code>fatherRoot</code>
     */
    public void removeItself() {
        fatherRoot.getChildren().remove(this);
    }

    /**
     *
     * @return <code>post</code>
     */
    public Post getPost() {
        return post;
    }
}
