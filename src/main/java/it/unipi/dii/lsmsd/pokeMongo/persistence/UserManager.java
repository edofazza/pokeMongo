package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;

import java.util.ArrayList;

public interface UserManager {

    /**
     * logins the current user and returns the user data, or null if login failed
     * @param username username already registered by user
     * @param password plain text password of user, encryption is done at mongodb level
     * @return User data or null if login fails
     */
    User login(String username, String password);

    /**
     * logins the current user and returns the user data, or null if login failed
     * @param toLog user to log, with AT LEAST USERNAME and PASSWORD set
     * @return User data or null if login fails
     */
    User login(User toLog);

    /**
     * registers a user into the system
     * @param toRegister User to add to the system
     * @return true if user is correctly added
     */
    boolean register(User toRegister);

    /**
     * changes email to a user
     * @param target user we want to change email
     * @param newEmail new email to set
     * @return true if email is correctly updated
     */
    boolean changeEmail(User target, String newEmail);

    /**
     * changes password to a user
     * @param target user we want to change password
     * @param newPassword new encrypted password to set
     * @return true if password is correctly updated
     */
    boolean changePassword(User target, String newPassword);

    /**
     * changes country to a user
     * @param target user we want to change country
     * @param newCountry new encrypted country to set
     * @return true if country is correctly updated
     */
    boolean changeCountry(User target, String newCountry);

    /**
     * removes an user from the system
     * @param target user to eliminate (at least username must be set)
     * @return true if user is removed correctly
     */
    boolean removeUser(User target);

    /**
     * removes an user from the system
     * @param username username of the  user to eliminate
     * @return true if user is removed correctly
     */
    boolean removeUser(String username);

    /**
     * Compares the password of a User object with the one memorized on DB
     * @param involved user to which compare password
     * @param password PLAIN TEXT password to compare with the encrypted one stored in db
     * @return true if password are equal
     */
    boolean verifyOldPassword(User involved, String password);

    /**
     * Changes the name of the team associated to a user
     * @param involved User that own the team
     * @param newName name of the team
     * @return true if the name is correctly updated
     */
    boolean changeTeamName(User involved, String newName);


    /**
     * Updates a user's points
     * @param target User whose points are updated
     * @param points new Value of target's points
     * @return true if points are successfully updated
     */
    boolean updatePoints(User target, double points);

    /**
     * updates number of daily Pokeball of the user, for cheat-prevention
     * @param target user to update
     * @return true if update is successful
     */
    boolean updateNumberOfPokeball(User target);

    /**
     * restores the default value of the available Pokeball for the target user
     * @param target user to update
     * @return true if update is successful
     */
    boolean updateNumberOfPokeballTo10(User target);

    /**
     * decrements by 1 the number of Pokeball associated to the target user
     * @param target user to update
     * @return true if update is successful
     */
    boolean decrementPokeball(User target);


    /**
     * logouts the user from the system
     * @param toLogOut user to logout
     */
    void logout(User toLogOut);

    /**
     * gets every user in the database
     * @return a list of users
     */
    ArrayList<User> getEveryUser();
}
