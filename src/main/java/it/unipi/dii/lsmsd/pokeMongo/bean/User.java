package it.unipi.dii.lsmsd.pokeMongo.bean;

public interface User {
    /**
     * Check if the user is an admin
     * @return true if the user is an admin, false otherwise
     */
    boolean isAdmin();

    /**
     * Get the user's username
     * @return A String containing the user's username
     */
    String getUsername();

    /**
     * Get the user's password
     * @return A String containing the user's password
     */
    String getPassword();

    /**
     * Get the user's email
     * @return A String containing the user's username
     */
    String getEmail();

    /**
     * Set the new user's email
     * @param email A String containing the new email we want to set.
     */
    void setEmail(String email);

    /**
     * Set the new user's password
     * @param password A String containg the new password we want to set.
     */
    void setPassword(String password);
}
