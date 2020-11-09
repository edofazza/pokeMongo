package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.scene.Group;
import javafx.scene.Scene;

public class CurrentUI {
    private static PokeScene nodeWindow;
    private static Group root;

    public Scene initScene() {
        /*   LogIn   */
        //nodeWindow = new SignIn();
        //root = nodeWindow.getNodes();

        // TEST


        return new Scene(root, 1280, 700);
    }

    public static void changeScene(PokeScene pScene) {
        nodeWindow = new LogIn();
        root.getChildren().clear();
        root = nodeWindow.getNodes();
    }
}
