package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.group.TeamUserWindowGroup;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UsernameLinkTeamButton extends Button {
    public UsernameLinkTeamButton(User user, int x, int y) {
        super(user.getUsername());

        Logger.vvlog("Creating UsernameLinkTeamButton '" + user.getUsername() + "' at (" + x + ", " + y + ")");
        relocate(x, y);
        setOnAction(e -> createNewWindow(user));

        getStyleClass().add("FilterPokemonResultButton");
    }

    /**
     * Open a new window with the information concerning an user's team
     * @param user contains the name of the user in order to use it as the title for the Stage
     */
    private void createNewWindow(User user) {
        // I need the correct user

        TeamUserWindowGroup root = new TeamUserWindowGroup(user);

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add("file:css/pokemongoStyle.css");
        Stage stage = new Stage();
        stage.setTitle(user.getUsername());
        stage.setScene(scene);
        stage.show();
    }
}
