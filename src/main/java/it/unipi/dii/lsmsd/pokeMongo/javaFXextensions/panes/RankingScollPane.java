package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManagerOnMongoDb;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManagerOnNeo4j;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerOnMongoDb;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.RankingTypes;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

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

        //addDefaultResult();
        changeCountry("");
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
                /*RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult("Ducange", "Jail Team", 2000);
                RankingSingleUserResult rankingSingleUserResult1 = new RankingSingleUserResult("Gionatan", "Control rotella", 2000);

                root.getChildren().addAll(rankingSingleUserResult, rankingSingleUserResult1);*/
                List<User> userList = ( new UserManagerOnMongoDb() ).bestWorldTeams();

                for (User user: userList) {
                    RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user.getUsername(), user.getTeamName(), 2000); // TODO POINTS
                    root.getChildren().add(rankingSingleUserResult);
                }
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
    public void changeCountry(String country) {
        clearResults();

        // IN CASE OF BESTTEAM
        if (rankingTypes == RankingTypes.BESTTEAM){
            UserManagerOnMongoDb userManagerOnMongoDb = new UserManagerOnMongoDb();
            List<User> userList = null;
            // TODO: add the result by country
            if (country.equals("")) { // WORLD
                userList = userManagerOnMongoDb.bestWorldTeams();
            } else {
                userList = userManagerOnMongoDb.bestCountryTeams(country);
            }

            for (User user : userList) {
                RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user.getUsername(), user.getTeamName(), 2000); // TODO POINTS
                root.getChildren().add(rankingSingleUserResult);
            }
        }

        // IN CASE OF BESTPOKEMON
        if (rankingTypes == RankingTypes.BESTPOKEMON){
            TeamManagerOnNeo4j teamManagerOnNeo4j = new TeamManagerOnNeo4j();

            ArrayList<Pokemon> pokemonArrayList = null;

            if (country.equals(""))  // WORLD
                pokemonArrayList = teamManagerOnNeo4j.getBestPokemon();
            else
                pokemonArrayList = teamManagerOnNeo4j.getBestPokemon(country);

            for (Pokemon p: pokemonArrayList) {
                RankingPokemonSingleResultPane prpp = new RankingPokemonSingleResultPane(p);
                root.getChildren().add(prpp);
            }
        }

    }
}
