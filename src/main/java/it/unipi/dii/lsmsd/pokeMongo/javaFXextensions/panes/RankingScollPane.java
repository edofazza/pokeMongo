package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.dataAnalysis.PokemonRanker;
import it.unipi.dii.lsmsd.pokeMongo.dataAnalysis.TeamRankerFactory;
import it.unipi.dii.lsmsd.pokeMongo.dataAnalysis.UserRanker;
import it.unipi.dii.lsmsd.pokeMongo.dataAnalysis.UserRankerFactory;
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

    /**
     *
     * @param x the x axis position
     * @param y the y axis position
     * @param width the width you want to set
     * @param height the height you want to set
     * @param rt a <code>RankingTypes</code>
     */
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

    /**
     * Clears <code>root</code>
     */
    private void clearResults() {
        root.getChildren().clear();
    }

    /**
     * To be called everytime the country changes.
     */
    public void changeCountry(String country) {
        clearResults();

        // IN CASE OF BESTTEAM
        if (rankingTypes == RankingTypes.BESTTEAM){
            UserRanker userRanker = UserRankerFactory.buildRanker();
            List<User> userList = null;

            if (country.equals("")) { // WORLD
                userList = userRanker.bestWorldTeams();
            } else {
                userList = userRanker.bestCountryTeams(country);
            }

            for (User user : userList) {
                RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
                root.getChildren().add(rankingSingleUserResult);
            }
        }

        // IN CASE OF BESTPOKEMON
        if (rankingTypes == RankingTypes.BESTPOKEMON){
            PokemonRanker pokemonRanker = TeamRankerFactory.buildRanker();

            ArrayList<Pokemon> pokemonArrayList = null;

            if (country.equals(""))  // WORLD
                pokemonArrayList = pokemonRanker.getBestPokemon();
            else
                pokemonArrayList = pokemonRanker.getBestPokemon(country);

            for (Pokemon p: pokemonArrayList) {
                RankingPokemonSingleResultPane prpp = new RankingPokemonSingleResultPane(p);
                root.getChildren().add(prpp);
            }
        }
    }


    /**
     * Sets the Friends Ranking information
     */
    public void getFriendsRanking(){

        //get friends usernames
        if(rankingTypes == RankingTypes.FRIENDS){
            List<String> friendsUsernames = (UserNetworkManagerFactory.buildManager()).getFollowing(CurrentUI.getUser());
            List<User> friendsUser = (UserRankerFactory.buildRanker()).bestFriendsTeams(friendsUsernames);

            for (User user : friendsUser) {
                RankingSingleUserResult rankingSingleUserResult = new RankingSingleUserResult(user);
                root.getChildren().add(rankingSingleUserResult);
            }
        }
        
    }
}
