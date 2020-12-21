package it.unipi.dii.lsmsd.pokeMongo.dataAnalysis;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;

import java.util.List;

public interface UserRanker {
    /**
     * Ranking of the top teams, i.e. the ones with the highest amount of points
     * @return a list of the users that own those teams
     */
    List<User> bestWorldTeams();

    /**
     * Ranking of the top teams, i.e. the ones with the highest amount of points among a user's friends
     * @param friendsUsernames list of friends of the current user
     * @return a list of the users that own those teams
     */
    List<User> bestFriendsTeams(List<String> friendsUsernames);

    /**
     * Ranking of the top teams, i.e. the ones with the highest amount of points in a country
     * @param country country considered
     * @return a list of the users that own those teams
     */
    List<User> bestCountryTeams(String country);
}
