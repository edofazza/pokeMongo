package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox.CountryComboBox;

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
        setSceneMusic("ranking.mp3");
    }

    void displayCountryButton() {
        try {
            CountryComboBox country = new CountryComboBox(100, 100);
            sceneNodes.getChildren().add(country);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
