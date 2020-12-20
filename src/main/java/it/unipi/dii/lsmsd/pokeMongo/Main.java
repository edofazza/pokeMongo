package it.unipi.dii.lsmsd.pokeMongo;

import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.LogManager;

/**
 * Main Class of the application
 */
public class Main extends Application {
    /**
     * Set the scene up
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        Scene scene = new CurrentUI().initScene();
        primaryStage.setTitle("pokeMongo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * automatic called by the IDE in order to launch the Application
     * @param args no args present
     */
    public static void main(String[] args) {
        Logger.log("Starting of the application");

        // removes the log messages from the dbs
        LogManager.getLogManager().reset();

        launch(args);
    }
}