package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.cache.PokeMongoImageCache;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * <em>CurrentUI</em> handles the scene showed to the user
 */
public class CurrentUI {
    private static PokeScene nodeWindow;
    private static Group root;
    private static User userLogged;
    private static PokeMongoImageCache pokeMongoImageCache = PokeMongoImageCache.getInstance();

    /**
     * Create the default scene (<em>LogIn</em>) and set the dimension of it
     * @return the scene created
     */
    public Scene initScene() {
        /*   LogIn   */
        nodeWindow = new LogIn();
        root = nodeWindow.getNodes();

        Scene scene = new Scene(root, 1280, 700);
        scene.getStylesheets().add("file:css/pokemongoStyle.css");
        return scene;
    }

    /**
     * Clears all the nodes from the scene, and then adds the nodes regarding the new one
     * @param sn is a <em>SceneNames</em> which indicates the new scene
     */
    protected static void changeScene(SceneNames sn) {
        root.getChildren().clear();
        nodeWindow = sn.createNewScene(sn);
        root = nodeWindow.getNodes();
    }

    /**
     * Get the username of the user currently logged
     * @return the username of the user currently logged
     */
    protected static String getUsername() {
        if (userLogged != null)
            return userLogged.getUsername();
        return "username";
    }

    /**
     * Get the user logged
     * @return the user logged
     */
    protected static User getUser() {
        return userLogged;
    }

    /**
     * Sets the user logged
     * @param user the user logged
     */
    protected static void setUser(User user) {
        userLogged = user;
    }

    /**
     * Get the number of pokeball currently hold by the user
     * @return number of pokeball currently hold by the user
     */
    protected static int getNumberOfPokeball() {
        if (userLogged != null)
            return userLogged.getDailyPokeball();
        return 0;
    }

    /**
     * Check if the user is an admin
     * @return A boolean saying if the user logged is an admin or not
     */
    protected static boolean isAdmin() {
        if (userLogged != null)
            return userLogged.isAdmin();
        return false;
    }

    /**
     * Retrieve the name of the user's team
     * @return A String containing the name of the user's team
     */
    protected static String getTeamName() {
        if (userLogged != null)
            return userLogged.getTeamName();
        return "No team name";
    }

    // TODO: handle exit
    protected static void userExit() {
        userLogged = null;
    }


    /////////////////////// IMAGE //////////////////////////
    public static Image getImage(String url) {
        return pokeMongoImageCache.getDataIfPresent(url);
    }
}
