package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.ToggleGroupPane;

public class AddRemovePokemon extends PokeSceneWithHeaderAndBackButton {
    public AddRemovePokemon() {
        displayAddRemovePokemon();

        setSceneMusic("catchemAll.mp3");
    }

    private void displayAddRemovePokemon() {
        ToggleGroupPane addRemove = new ToggleGroupPane("Add Pokemon", "Remove Pokemon", 270, 100);

        sceneNodes.getChildren().add(addRemove);
    }
}
