package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FilterPokemonResultButton extends Button {
    public FilterPokemonResultButton(String name, int x, int y) {
        super(name);
        relocate(x, y);
        setOnAction(e -> createNewWindow(name)); // TODO: popup window

        getStyleClass().add("FilterPokemonResultButton");
    }

    private void createNewWindow(String name) {
        Group root = new Group();

        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setTitle(name);
        stage.setScene(scene);
        stage.show();
    }
}
