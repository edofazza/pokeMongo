package it.unipi.dii.lsmsd.pokeMongo.dataAnalysis;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;

import java.util.List;

public interface UserRanker {
    List<User> bestWorldTeams();

    List<User> bestFriendsTeams(User current);

    List<User> bestCountryTeams(String country);
}
