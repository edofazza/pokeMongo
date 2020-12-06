package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.*;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.RankingTypes;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class RankingScollPane extends ScrollPane {
    private VBox root;
    private RankingTypes rankingTypes;

    public RankingScollPane(int x, int y, int width, int height, RankingTypes rt) {
        rankingTypes = rt;

        setPrefSize(width,height);
        relocate(x, y);

        root = new VBox();
        root.setSpacing(5);
        setContent(root);

        changeCountry("");
        getFriendsRanking();
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
                RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
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

    public void getFriendsRanking(){

        //get friends usernames
        if(rankingTypes == RankingTypes.FRIENDS){
            List<String> friendsUsernames = (new UserNetworkManagerOnNeo4j()).getFollowing(CurrentUI.getUser());
            List<User> friendsUser = (new UserManagerOnMongoDb()).bestFriendsTeams(friendsUsernames);

            for (User user : friendsUser) {
                RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
                root.getChildren().add(rankingSingleUserResult);
            }
        }
        
    }
}
