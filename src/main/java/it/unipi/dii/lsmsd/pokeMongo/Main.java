package it.unipi.dii.lsmsd.pokeMongo;

import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new CurrentUI().initScene();
        primaryStage.setTitle("pokeMongo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}