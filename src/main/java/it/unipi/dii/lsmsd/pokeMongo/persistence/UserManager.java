package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;

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
}
