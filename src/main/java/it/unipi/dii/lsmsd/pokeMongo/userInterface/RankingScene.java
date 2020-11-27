package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox.CountryComboBox;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.RankingScollPane;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;

import java.io.IOException;

/**
 * This class is used to display the <code>Node</code> concerning the Rankings.
 */
public class RankingScene extends PokeSceneWithHeaderAndBackButton {
    /**
     * Calls a series of function in order to add to the scene all the elements needed.
     * It also sets the music.
     */
    public RankingScene() {
        displayCountryButton();

        displayMostUsedPokemon();
        displayBestTeam();

        displaySeparator();

        displayFriends();

        setSceneMusic("ranking.mp3");
    }

    private void displayCountryButton() {
        try {
            CountryComboBox country = new CountryComboBox(100, 100);
            sceneNodes.getChildren().add(country);
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void displayMostUsedPokemon() {
        FieldRelatedLabel mostUsedLabel = new FieldRelatedLabel("MOST USED POKEMON", 100, 170);

        RankingScollPane mostUsedPokemon = new RankingScollPane(100, 200, RankingTypes.BESTPOKEMON);

        sceneNodes.getChildren().addAll(mostUsedLabel, mostUsedPokemon);
    }

    private void displayBestTeam() {
        FieldRelatedLabel bestTeamLabel = new FieldRelatedLabel("BEST TEAM", 500, 170);

        RankingScollPane bestTeam = new RankingScollPane(500, 200, RankingTypes.BESTTEAM);

        sceneNodes.getChildren().addAll(bestTeamLabel, bestTeam);
    }

    private void displayFriends() {
        FieldRelatedLabel friendsLabel = new FieldRelatedLabel("FRIENDS", 900, 170);

        RankingScollPane friends = new RankingScollPane(900, 200, RankingTypes.FRIENDS);

        sceneNodes.getChildren().addAll(friendsLabel, friends);
    }

    private void displaySeparator() {
        Separator sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        sep.setPrefHeight(450);
        sep.relocate(850, 160);

        sceneNodes.getChildren().add(sep);
    }
}
