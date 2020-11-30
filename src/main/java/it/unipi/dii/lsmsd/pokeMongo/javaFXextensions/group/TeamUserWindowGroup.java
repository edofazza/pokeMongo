package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.group;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.PokemonTeamForUserSelectedWindow;
import javafx.scene.Group;
import javafx.scene.control.Label;

public class TeamUserWindowGroup extends Group {
    User user;

    // TODO: retrieve the data from the user
    public TeamUserWindowGroup(User user) {
        this.user = user;

        displayTeamName();

        displayPokemons();
    }

    private void displayTeamName() {
        Label teamLabel = new Label(user.getTeamName());
        teamLabel.setStyle("-fx-font-size: 23; -fx-font-family: 'Arial Black'; -fx-text-fill: blue;");
        teamLabel.relocate(227, 5);

        getChildren().addAll(teamLabel);
    }

    private void displayPokemons() {
        for (int i = 0; i < 3; i++) {
            PokemonTeamForUserSelectedWindow p0 = new PokemonTeamForUserSelectedWindow(10, 50+i*(35+90));
            getChildren().add(p0);
        }
        for (int i = 0; i < 3; i++) {
            PokemonTeamForUserSelectedWindow p = new PokemonTeamForUserSelectedWindow(320, 50+i*(35+90));
            getChildren().add(p);
        }
    }
}
