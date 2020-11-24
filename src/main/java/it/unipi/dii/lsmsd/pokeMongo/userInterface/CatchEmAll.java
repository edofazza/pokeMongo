package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.choiceBox.ChooseSlotNumber;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.imageviews.BackgroundImage;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.TitleLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;

public class CatchEmAll extends PokeSceneWithHeaderAndBackButton {
    private CatchEmAllTextField selectPokemon;
    private ChooseSlotNumber selectSlot;

    private FieldRelatedLabel oddLabel;

    private BackgroundImage selectedPokemon;

    public CatchEmAll() {
        displayTitle();

        displaySelectPokemon();
        displaySelectSlot();

        displaySelectedPokemon();
        displayOdd();

        displayTryToCatch();

        setSceneMusic("catchemAll.mp3");
    }

    private void displayTitle() {
        TitleLabel titleLabel = new TitleLabel("Catch'em All");

        sceneNodes.getChildren().add(titleLabel);
    }

    private void displaySelectPokemon() {
        selectPokemon = new CatchEmAllTextField("SELECT POKEMON", 530, 200);

        sceneNodes.getChildren().add(selectPokemon);
    }

    private void displaySelectSlot() {
        selectSlot = new ChooseSlotNumber(620, 260);

        sceneNodes.getChildren().add(selectSlot);
    }

    private void displaySelectedPokemon() {
        selectedPokemon = new BackgroundImage("portraits/0.png", 130, 580, 320);

        sceneNodes.getChildren().add(selectedPokemon);
    }

    private void displayOdd() {
        oddLabel = new FieldRelatedLabel("Odd: ", 560, 475);

        sceneNodes.getChildren().add(oddLabel);
    }

    private void displayTryToCatch() {
        RegularButton tryToCatch = new RegularButton("TRY TO CATCH", 580, 510);

        sceneNodes.getChildren().add(tryToCatch);
    }
}
