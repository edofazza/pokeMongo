package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import com.google.common.annotations.VisibleForTesting;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.cache.PokeMongoImageCache;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.util.concurrent.CompletableFuture;

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
    @VisibleForTesting
    protected static void changeScene(SceneNames sn) {

        Logger.log("CHANGE OF SCENE");
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
    public static User getUser() {
        return userLogged;
    }

    /**
     * Sets the user logged
     * @param user the user logged
     */
    protected static void setUser(User user) {
        userLogged = user;
    }

    protected static void decrementPokeball() {
        userLogged.decrementDailyPokeball();
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

    /**
     * Removes the user stored in memory (<code>userLogged</code>) when the user logged out (in order to avoid error and to be coherent).
     */
    protected static void userExit() {
        userLogged = null;
    }


    /////////////////////// IMAGE //////////////////////////

    /**
     * Return a sort of Image that can be loaded also after the entire scene is loaded, in this way we doesn't slow down
     * the application
     * @param url of the image to be displayed
     * @return A CompletableFuture<Image>
     */
    public static CompletableFuture<Image> getImage(String url) {
        return pokeMongoImageCache.getDataIfPresent(url);
    }
}
