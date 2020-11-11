package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.userRelated.User;
import javafx.scene.Group;
import javafx.scene.Scene;

public class CurrentUI {
    private static PokeScene nodeWindow;
    private static Group root;
    private static User userLogged = new User("edofazza");

    public Scene initScene() {
        /*   LogIn   */
        nodeWindow = new LogIn();
        root = nodeWindow.getNodes();

        return new Scene(root, 1280, 700);
    }

    protected static void changeScene(SceneNames sn) {
        root.getChildren().clear();
        nodeWindow = sn.createNewScene(sn);
        root = nodeWindow.getNodes();
    }

    protected static String getUsername() {
        if (userLogged != null)
            return userLogged.getUsername();
        return "username";
    }

    protected static int getNumberOfPokeball() {
        if (userLogged != null)
            return userLogged.getNumberOfPokeball();
        return 0;
    }

    protected static boolean isAdmin() {
        if (userLogged != null)
            return userLogged.isAdmin();
        return false;
    }

    protected static String getTeamName() {
        if (userLogged != null)
            return userLogged.getTeamname();
        return "No team name";
    }
}
