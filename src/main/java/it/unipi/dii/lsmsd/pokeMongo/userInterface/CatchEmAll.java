package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.choiceBox.ChooseSlotNumber;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.imageviews.BackgroundImage;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.TitleLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;

/**
 * Scene related to the catch of pokemon.
 */
public class CatchEmAll extends PokeSceneWithHeaderAndBackButton {
    private CatchEmAllTextField selectPokemon;
    private ChooseSlotNumber selectSlot;

    private FieldRelatedLabel oddLabel;

    private BackgroundImage selectedPokemon;

    /**
     * <em>Constructor</em>. Called a series of function in order to create the <em>Node</em>
     * needed to set the scene up. It also sets the music.
     */
    public CatchEmAll() {
        displayTitle();

        displaySelectPokemon();
        displaySelectSlot();

        displaySelectedPokemon();
        displayOdd();

        displayTryToCatch();

        setSceneMusic("catchemAll.mp3");
    }

    /**
     * Add the Title to the scene.
     */
    private void displayTitle() {
        TitleLabel titleLabel = new TitleLabel("Catch'em All");

        sceneNodes.getChildren().add(titleLabel);
    }

    /**
     * Add a <code>CatchEmAllTextField</code> for selecting the pokemon the user
     * wants to catch.
     */
    private void displaySelectPokemon() {
        selectPokemon = new CatchEmAllTextField("SELECT POKEMON", 530, 200);

        sceneNodes.getChildren().add(selectPokemon);
    }

    /**
     * Add a <code>ChooseSlotNumber</code> for selecting the slot in which the pokemon
     * will be put if captured
     */
    private void displaySelectSlot() {
        selectSlot = new ChooseSlotNumber(620, 260);

        sceneNodes.getChildren().add(selectSlot);
    }

    /**
     * Add a <code>BackgroundImage</code> to the scene, displaying the portrait of the pokemon selected.
     * In case no pokemon is selected
     */
    // TODO: change it every time the selectPokemon changes
    private void displaySelectedPokemon() {
        selectedPokemon = new BackgroundImage("portraits/0.png", 130, 580, 320);

        sceneNodes.getChildren().add(selectedPokemon);
    }

    /**
     * Add a <code>FieldRelatedLabel</code> displaying the odd related to the pokemon selected. In case no
     * pokemon is selected, it just tells "Odd:".
     */
    private void displayOdd() {
        oddLabel = new FieldRelatedLabel("Odd: ", 560, 475);

        sceneNodes.getChildren().add(oddLabel);
    }

    /**
     * Add to the scene a <code>RegularButton</code> saying "TRY TO CATCH". Clicking on it will try to catch
     * the pokemon selected. If no pokemon is selected or the number of pokeballs is zero, nothing happens.
     */
    private void displayTryToCatch() {
        RegularButton tryToCatch = new RegularButton("TRY TO CATCH", 580, 510);

        sceneNodes.getChildren().add(tryToCatch);
    }
}
