package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.scene.Group;
import javafx.scene.Scene;

public class CurrentUI {
    private PokeScene nodeWindow;

    public Scene initScene() {
        Group root;

        /*   LogIn   */
        nodeWindow = new SignIn();
        root = nodeWindow.getNodes();

        return new Scene(root, 1280, 700);
    }
}
