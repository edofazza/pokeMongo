package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.userRelated.User;
import javafx.scene.Group;
import javafx.scene.Scene;

public class CurrentUI {
    private static PokeScene nodeWindow;
    private static Group root;
    private static User userLogged = null;

    public Scene initScene() {
        /*   LogIn   */
        nodeWindow = new LogIn();
        root = nodeWindow.getNodes();

        return new Scene(root, 1280, 700);
    }

    public static void changeScene(SceneNames sn) {
        root.getChildren().clear();
        nodeWindow = sn.createNewScene(sn);
        root = nodeWindow.getNodes();
    }
}
