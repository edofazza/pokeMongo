package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.userInterface.RankingTypes;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class RankingScollPane extends ScrollPane {
    private VBox root;
    private RankingTypes rankingTypes;

    public RankingScollPane(int x, int y, RankingTypes rt) {
        rankingTypes = rt;

        setPrefSize(320, 350);
        relocate(x, y);

        root = new VBox();
        root.setSpacing(5);
        setContent(root);

        addDefaultResult();
    }

    private void addDefaultResult() {
        switch (rankingTypes) {
            case BESTPOKEMON:
                /*PokemonSingleResultPane prpp = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Hold by: " + "71");
                PokemonSingleResultPane prpp1 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Hold by: " + "70");
                PokemonSingleResultPane prpp2 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Hold by: "  + "12");
                PokemonSingleResultPane prpp3 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Hold by: "  + "37");
                PokemonSingleResultPane prpp4 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Hold by: "  + "27");
                PokemonSingleResultPane prpp5 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Hold by: "  + "17");
                PokemonSingleResultPane prpp6 = new PokemonSingleResultPane("file:img/sprites/7.png", "Squirtle", "Hold by: "  + "7");

                root.getChildren().addAll(prpp, prpp1, prpp2, prpp3, prpp4, prpp5, prpp6);*/
                break;
            case BESTTEAM:
                RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult("Ducange", "Jail Team", 2000);
                RankingSingleUserResult rankingSingleUserResult1 = new RankingSingleUserResult("Gionatan", "Control rotella", 2000);

                root.getChildren().addAll(rankingSingleUserResult, rankingSingleUserResult1);
                break;
            case FRIENDS:
                RankingSingleUserResult rankingSingleUserResult10 = new RankingSingleUserResult("Ducange", "Jail Team", 2000);
                RankingSingleUserResult rankingSingleUserResult11 = new RankingSingleUserResult("Gionatan", "Control rotella", 2000);

                root.getChildren().addAll(rankingSingleUserResult10, rankingSingleUserResult11);
                break;
        }
    }

    private void clearResults() {
        root.getChildren().clear();
    }

    /**
     * To be called everytime that country changes.
     */
    public void changeCountry() {
        clearResults();

        // TODO: add the result by country
    }
}
