package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox.CountryComboBox;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.RankingScollPane;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;

import java.io.IOException;

/**
 * This class is used to display the <code>Node</code> concerning the Rankings.
 */
public class RankingScene extends PokeSceneWithHeaderAndBackButton {
    private CountryComboBox country;
    private RankingScollPane bestTeam;
    private RankingScollPane mostUsedPokemon;

    /**
     * Calls a series of function in order to add to the scene all the elements needed.
     * It also sets the music.
     */
    public RankingScene() {
        Logger.log("SHOWING RANKING SCENE");

        displayCountryButton();

        displayMostUsedPokemon();
        displayBestTeam();

        if (!CurrentUI.getUser().isAdmin()) {
            displaySeparator();
            displayFriends();
        }

        setSceneMusic("ranking.mp3");
    }

    private void displayCountryButton() {
        try {
            country = new CountryComboBox(100, 100);

            country.setOnAction(e -> countryChanged());

            sceneNodes.getChildren().add(country);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void displayMostUsedPokemon() {
        FieldRelatedLabel mostUsedLabel = new FieldRelatedLabel("MOST USED POKEMON", 100, 170);

        mostUsedPokemon = new RankingScollPane(100, 200, 320, 350, RankingTypes.BESTPOKEMON);

        sceneNodes.getChildren().addAll(mostUsedLabel, mostUsedPokemon);
    }

    private void displayBestTeam() {
        FieldRelatedLabel bestTeamLabel = new FieldRelatedLabel("BEST TEAM", 500, 170);

        bestTeam = new RankingScollPane(500, 200, 320, 350, RankingTypes.BESTTEAM);

        sceneNodes.getChildren().addAll(bestTeamLabel, bestTeam);
    }

    private void displayFriends() {
        FieldRelatedLabel friendsLabel = new FieldRelatedLabel("FRIENDS", 900, 170);

        RankingScollPane friends = new RankingScollPane(900, 200, 320, 350, RankingTypes.FRIENDS);

        sceneNodes.getChildren().addAll(friendsLabel, friends);
    }

    private void displaySeparator() {
        Separator sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        sep.setPrefHeight(450);
        sep.relocate(850, 160);

        sceneNodes.getChildren().add(sep);
    }

    private void countryChanged() {
        String tmpCountry = country.getValue().toString();

        bestTeam.changeCountry(tmpCountry);
        mostUsedPokemon.changeCountry(tmpCountry);
    }
}
