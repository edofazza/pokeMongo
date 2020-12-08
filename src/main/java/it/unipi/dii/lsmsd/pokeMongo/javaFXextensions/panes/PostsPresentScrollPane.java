package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManagerFactory;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.List;

public class PostsPresentScrollPane extends ScrollPane {
    private VBox root;
    private String pokemonName;

    public PostsPresentScrollPane(int x, int y, int width, int height, String pokemonName) {
        relocate(x, y);
        setPrefSize(width, height);

        this.pokemonName = pokemonName;

        root = new VBox();
        root.setSpacing(5);
        setContent(root);

        loadPosts();
    }

    private void loadPosts() {
        PostManager postManagerFactory =  PostManagerFactory.buildManager();
        List<Pair<Post, Integer>> postList = postManagerFactory.getPostsByPokemon(pokemonName);

        for (Pair<Post, Integer> p: postList) {
            Post post = p.getKey();
            int numberOfAnswers = p.getValue();

            PostPane postPane = new PostPane(post, numberOfAnswers);

            root.getChildren().add(postPane);
        }

    }
}
