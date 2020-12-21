package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.exceptions.DuplicateUserException;

import java.util.List;

public interface UserNetworkManager {

    /**
     * Deletes a user from the network
     * @param u user to delete
     * @return true if the user i successfully deleted
     */
    boolean deleteUser(User u);


    /**
     * Deletes a user from the network
     * @param username name of the user to delete
     * @return true if the user i successfully deleted
     */
    boolean deleteUser(String username);


    /**
     * Inserts a user into the network
     * @param u user to add
     * @return true if a user is successfully inserted
     * @throws DuplicateUserException if the user already exists, this exception is thrown
     */
    boolean addUser(User u) throws DuplicateUserException;


    /**
     * Adds a follow relationship between two different users
     * @param from user that follows to
     * @param to user followed by from
     * @return true if the relationship is successfully inserted
     */
    boolean addFollow(User from, User to);


    /**
     * Removes a follow relationship between two different users from the network
     * @param from User that unfollows to
     * @param to User unfollowed by from
     * @return true if the relationship is successfully deleted
     */
    boolean removeFollow(User from, User to);


    /**
     * Gets names of every person that follows a user
     * @param target user whose followers' names we are interested in
     * @return List of names
     */
    List<String> getFollowersUsernames(User target);

    /**
     * Gets names of every person followed by a user
     * @param target user whose followed people's names we are interested in
     * @return List of names
     */
    List<String> getFollowing(User target);

    /**
     * Changes the registered country of a user in the network
     * @param target user whose information is updated
     * @param newCountry name of the country that will overwrite old country value
     * @return true if the country is successfully updated
     */
    boolean updateCountry(User target, String newCountry);


    /**
     * Adds a Like relationship between a User and a Pokemon
     * @param trainer user that expresses like relationship towards liked
     * @param liked pokemon liked by trainer
     * @return true if the relationship is successfully inserted
     */
    boolean addLikeToPokemon(User trainer, String liked);


    /**
     * Removes a Like relationship between a User and a Pokemon
     * @param trainer user that no more likes unliked
     * @param unliked pokemon no longer liked by trainer
     * @return true if the relationship is successfully removed
     */
    boolean removeLikeToPokemon(User trainer, String unliked);

    /**
     * Says if a particular User is following another user
     * @param from name of the follower user
     * @param to name of the favorite pokemon
     * @return true i from is following to
     */
    boolean isFavorite(String from, String to);


    /**
     * Retrieves all the names of the Pokemon a user likes
     * @param u user to consider
     * @return List of names of liked Pokemon
     */
    List<String> getLikedPokemonNames(User u);


    /**
     * Suggests a new friend to follow to a user based on favorites pokemon
     * @param u user to which suggestions are addressed
     * @return list of names of suggested users
     */
    List<String> getSuggestedUserByFavoritesPokemon(User u);

    /**
     * Suggests a new friend to follow to a user
     * @param u user to which suggestions are addressed
     * @return list of names of suggested users
     */
    List<String> getSuggestedUser(User u);

    /**
     * Retrieves all the users whose name starts with a specified pattern
     * @param pattern a string representing the initial part of the names we are looking for. NO REGULAR EXPRESSION IS ALLOWED
     * @return list of names of resulting users
     */
    List<String> getUserBySearch(String pattern);

    /**
     * Says if a particular User is following another user
     * @param from name of the follower user
     * @param to name of the followed user
     * @return true i from is following to
     */
    boolean isFollowing(String from, String to);

}
