package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.ToggleGroupPane;

public class AddRemovePokemon extends PokeSceneWithHeader {
    public AddRemovePokemon() {
        displayAddPokemon();

        displayBackButton();

        setSceneMusic("catchemAll.mp3");
    }

    private void displayAddPokemon() {
        ToggleGroupPane addRemove = new ToggleGroupPane("Add Pokemon", "Remove Pokemon", 270, 100);

        sceneNodes.getChildren().add(addRemove);
    }

    private void displayBackButton() {
        RegularButton backButton = new RegularButton("BACK", 200, 600);

        backButton.setOnAction(e-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }
}
