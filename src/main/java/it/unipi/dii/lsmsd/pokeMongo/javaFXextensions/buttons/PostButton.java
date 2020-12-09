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

    private static boolean canComment = false;
    private static boolean answersPresent = false;

    private static PostButton postButtonAnswer;

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

            if(answersPresent)
                addReplies();

            canComment = true;
            addCommentPane();

        } else if (text.equals("Uncomment")) {
            setTextButton("Comment");
            canComment = false;

            if(answersPresent)
                addReplies();

        } else if (text.startsWith("Answers")) {
            setTextButton("Show less");

            addReplies();
            answersPresent = true;

            if (canComment)
                addCommentPane();

        } else if (text.equals("Show less")) {
            setTextButton("Answers (" + numberOfAnswers + ")");

            answersPresent = false;

            if (canComment)
                addCommentPane();

        }

    }

    private void refresh() {
        subPostsVBox.getChildren().clear();

        if(answersPresent)
            addReplies();
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
            SubPostPane subPostPane = new SubPostPane(p);
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

    public void increment() {
        numberOfAnswers++;
    }
}
