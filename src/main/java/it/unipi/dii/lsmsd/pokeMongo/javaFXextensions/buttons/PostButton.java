package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.SubPostInsertCommentPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.SubPostPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.vBox.SubPostsVBox;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.Button;
import java.util.List;

/**
 * To use this class you have to create two object, one related to the answer and the other to the comment part. First you
 * create the answer PostButton, then the comment one giving it as input. Done that, you have to let the answer PostButton
 * to know which is the comment PostButton it has to interact with (<code>setCommentButton</code>)
 */
public class PostButton extends Button {
    private String text;
    private SubPostsVBox subPostsVBox;
    private Post currentPost;
    private int numberOfAnswers;

    private boolean canComment = false;
    private boolean answersPresent = false;

    private PostButton postButtonAnswer;
    private PostButton postButtonComment;

    /**
     *
     * @param text what's should be written in it
     * @param x the x axis position
     * @param y the y axis potion
     * @param subPostsVBox the SubPostsVBox is related to
     * @param currentPost the current post this button is related to
     * @param numberOfAnswers the number of answers related to the post.
     */
    public PostButton(String text, int x, int y, SubPostsVBox subPostsVBox, Post currentPost, int numberOfAnswers) {
        super(text);
        relocate(x, y);
        setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #70709d;");

        this.text = text;
        this.subPostsVBox = subPostsVBox;
        this.currentPost = currentPost;
        this.numberOfAnswers = numberOfAnswers;

        this.postButtonAnswer = this;

        setOnAction(e -> fillVBox());
    }

    /**
     *
     * @param text what's should be written in it
     * @param x the x axis position
     * @param y the y axis potion
     * @param subPostsVBox the SubPostsVBox is related to
     * @param currentPost the current post this button is related to
     * @param numberOfAnswers the number of answers related to the post.
     * @param postButton a PostButton that refers to an Answer PostButton
     */
    public PostButton(String text, int x, int y, SubPostsVBox subPostsVBox, Post currentPost, int numberOfAnswers, PostButton postButton) {
        this(text, x, y, subPostsVBox, currentPost, numberOfAnswers);

        this.postButtonAnswer = postButton;
        this.postButtonComment = this;
    }

    /**
     * Fills the <code>subPostsVBox</code> with the appropriate elements
     */
    private void fillVBox() {
        subPostsVBox.getChildren().clear();

        if (text.equals("Comment")) {
            setTextButton("Uncomment");

            if(postButtonAnswer.getAnswersPresent())
                addReplies();

            canComment = true;
            addCommentPane();

        } else if (text.equals("Uncomment")) {
            setTextButton("Comment");
            canComment = false;

            if(postButtonAnswer.getAnswersPresent())
                addReplies();

        } else if (text.startsWith("Answers")) {
            setTextButton("Show less");

            addReplies();
            answersPresent = true;

            if (postButtonComment.getCanComment())
                addCommentPane();

        } else if (text.equals("Show less")) {
            setTextButton("Answers (" + numberOfAnswers + ")");

            answersPresent = false;

            if (postButtonComment.getCanComment())
                addCommentPane();

        }

    }

    /**
     * Called by the Answer for refreshing the comments.
     */
    // Called by answer (using Comment)
    private void refresh() {
        subPostsVBox.getChildren().clear();

        if(answersPresent)
            addReplies();
        else {
            setTextButton("Answers (" + numberOfAnswers + ")");
        }

        if (postButtonComment.getCanComment())
            addCommentPane();
    }

    /**
     * Adds a <code>SubPostInsertCommentPane</code> in the <code>subPostsVBox</code>
     */
    private void addCommentPane() {
        if (CurrentUI.getUser().isAdmin())
            return;

        SubPostInsertCommentPane subPostInsertCommentPane = new SubPostInsertCommentPane(currentPost, postButtonAnswer);
        subPostsVBox.getChildren().addAll(subPostInsertCommentPane);
    }

    /**
     * Inserts a the replies for the Post this button is related to in the <code>subPostsVBox</code>
     */
    private void addReplies() {
        PostManager postManagerFactory = PostManagerFactory.buildManager();
        List<Post> subpostList = postManagerFactory.getPostsByPost(currentPost);
        for (Post p: subpostList) {
            SubPostPane subPostPane;
            if (postButtonAnswer == null)
                subPostPane = new SubPostPane(p, subPostsVBox, this);
            else
                subPostPane = new SubPostPane(p, subPostsVBox, postButtonAnswer);

            subPostsVBox.getChildren().addAll(subPostPane);
        }
    }

    /**
     * Changes the text of the button
     * @param text the new text
     */
    public void setTextButton(String text) {
        this.text = text;
        setText(text);
    }

    /**
     * Increment the number of answers and then refreshes the Pane related to the subposts
     */
    public void newAnswerPosted() {
        increment();
        refresh();
    }

    /**
     * Decrements the number of answers and then refreshes the Pane related to the subposts
     */
    public void answerRemoved() {
        decrement();
        refresh();
    }

    /**
     * Increments the number of answers
     */
    private void increment() {
        numberOfAnswers++;
    }

    /**
     * Decrements the number of answers
     */
    private void decrement() {
        numberOfAnswers--;
    }

    /**
     * Check if the <code>canComment</code> boolean is true
     * @return true if the comment button is selected
     */
    public boolean getCanComment() {
        return canComment;
    }

    /**
     * Check if the <code>answersPresent</code> boolean is true
     * @return true if the comment button is selected
     */
    public boolean getAnswersPresent() {
        return answersPresent;
    }

    /**
     * Called by the Answer one in order to know which is the Comment PostButton it has to communicate with
     * @param postButtonComment the PostButton related to the Comment features
     */
    public void setCommentButton(PostButton postButtonComment) {
        this.postButtonComment = postButtonComment;
    }
}
