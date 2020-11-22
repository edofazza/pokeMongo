package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.HomePageCentralButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.TitleLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class CatchEmAll extends PokeSceneWithHeader {
    private CatchEmAllTextField selectPokemon;
    private TextField selectSlot;

    public CatchEmAll() {
        displayTitle();
        displayBackButton();

        displaySelectPokemon();
        displaySelectSlot();

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

    }

    private void displayBackButton() {
        RegularButton backButton = new RegularButton("BACK", 200, 600);

        backButton.setOnAction((ActionEvent ev)-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    // ACTIONS
    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }
}
