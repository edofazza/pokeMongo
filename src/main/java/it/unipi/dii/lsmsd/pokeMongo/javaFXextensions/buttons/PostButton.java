package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.SubPostInsertCommentPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.SubPostPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.vBox.SubPostsVBox;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManagerFactory;
import javafx.scene.control.Button;
import java.util.List;

public class PostButton extends Button {
    private String text;
    private SubPostsVBox subPostsVBox;
    private Post currentPost;
    private int numberOfAnswers;

    private boolean canComment = false;
    private boolean answersPresent = false;

    private PostButton postButtonAnswer;
    private PostButton postButtonComment;

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

    public PostButton(String text, int x, int y, SubPostsVBox subPostsVBox, Post currentPost, int numberOfAnswers, PostButton postButton) {
        this(text, x, y, subPostsVBox, currentPost, numberOfAnswers);

        this.postButtonAnswer = postButton;

        if (postButton == null)
            System.out.println("goofy");
    }

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

    // Called by answer (using Comment)
    private void refresh() {
        subPostsVBox.getChildren().clear();

        if(answersPresent)
            addReplies();
        else {
            setTextButton("Answers (" + numberOfAnswers + ")");
        }

        if (canComment)
            addCommentPane();
    }

    private void addCommentPane() {
        SubPostInsertCommentPane subPostInsertCommentPane = new SubPostInsertCommentPane(currentPost, postButtonAnswer);
        subPostsVBox.getChildren().addAll(subPostInsertCommentPane);
    }

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

    public void setTextButton(String text) {
        this.text = text;
        setText(text);
    }

    public void newAnswerPosted() {
        increment();
        refresh();
    }

    public void answerRemoved() {
        decrement();
        refresh();
    }

    private void increment() {
        numberOfAnswers++;
    }

    private void decrement() {
        numberOfAnswers--;
    }

    public boolean getCanComment() {
        return canComment;
    }

    public boolean getAnswersPresent() {
        return answersPresent;
    }

    public void setCommentButton(PostButton postButtonComment) {
        this.postButtonComment = postButtonComment;
    }
}
