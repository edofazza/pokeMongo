package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.group.TeamUserWindowGroup;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UsernameLinkTeamButton extends Button {
    public UsernameLinkTeamButton(String name, int x, int y) {
        super(name);
        relocate(x, y);
        setOnAction(e -> createNewWindow(name));

        getStyleClass().add("FilterPokemonResultButton");
    }

    /**
     * Open a new window with the information concerning an user's team
     * @param name contains the name of the user in order to use it as the title for the Stage
     */
    private void createNewWindow(String name) {
        TeamUserWindowGroup root = new TeamUserWindowGroup(CurrentUI.getUser());

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add("file:css/pokemongoStyle.css");
        Stage stage = new Stage();
        stage.setTitle(name);
        stage.setScene(scene);
        stage.show();
    }
}
