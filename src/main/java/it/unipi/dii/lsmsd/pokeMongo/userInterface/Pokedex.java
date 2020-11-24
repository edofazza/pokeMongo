package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.FilterPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.PokedexResultScrollPane;

public class Pokedex extends PokeSceneWithHeader {
    public Pokedex() {
        displayFilterPane();

        displayResultScrollPane();

        displayBackButton();

        setSceneMusic("pokedex_oak_s_lab.mp3");
    }

    private void displayFilterPane() {
        FilterPane filterPane = new FilterPane(140, 60);

        sceneNodes.getChildren().add(filterPane);
    }

    private void displayResultScrollPane() {
        PokedexResultScrollPane sp = new PokedexResultScrollPane(490, 280);

        sceneNodes.getChildren().add(sp);
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
