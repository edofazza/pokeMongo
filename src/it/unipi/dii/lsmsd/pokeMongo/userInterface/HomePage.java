package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.HomePageCentralButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class HomePage extends PokeSceneWithHeaderAndAggregateBlastoiseCharizard {
    private int buttonPosition = 0;

    public HomePage() {
        displayPokedexButton();

        if(!CurrentUI.isAdmin()) {
            displayTeamButton();
            displayCatchemAllButton();
        }

        displayRankingButton();

        if(!CurrentUI.isAdmin())
            displaySettingsButton();
        else
            displayAddRemovePokemonButton();

        displayLogOutButton();

        setSceneMusic("professor_oak_theme.mp3");
    }

    private void setCSS(Button b) {
        b.setStyle("-fx-font-size: 20px; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold; " +
                "-fx-background-color: transparent; " +
                "-fx-border-color: #000000; " +
                "-fx-min-width: 280;" +
                "-fx-min-height: 40;");
        b.relocate(490, 230 + buttonPosition*50);
        buttonPosition++;
    }

    private void displayPokedexButton() {
        /*Button pokedexButton = new Button("POKEDEX");

        setCSS(pokedexButton);*/
        HomePageCentralButton pokedexButton = new HomePageCentralButton("POKEDEX");

        sceneNodes.getChildren().add(pokedexButton);
    }

    private void displayTeamButton() {
        //Button teamButton = new Button("TEAM");
        HomePageCentralButton teamButton = new HomePageCentralButton("TEAM");
        teamButton.setOnAction((ActionEvent ev)-> teamButtonAction());

        //setCSS(teamButton);

        sceneNodes.getChildren().add(teamButton);
    }

    private void displayCatchemAllButton() {
        /*Button catchemAllButton = new Button("CATCH'EM ALL");

        setCSS(catchemAllButton);*/
        HomePageCentralButton catchemAllButton = new HomePageCentralButton("CATCH'EM ALL");

        sceneNodes.getChildren().add(catchemAllButton);
    }

    private void displayRankingButton() {
        /*Button rankingButton = new Button("RANKING");

        setCSS(rankingButton);*/
        HomePageCentralButton rankingButton = new HomePageCentralButton("RANKING");

        sceneNodes.getChildren().add(rankingButton);
    }

    private void displaySettingsButton() {
        //Button settingButton = new Button("SETTINGS");
        HomePageCentralButton settingButton = new HomePageCentralButton("SETTINGS");

        settingButton.setOnAction((ActionEvent ev)-> settingButtonAction());

        //setCSS(settingButton);

        sceneNodes.getChildren().add(settingButton);
    }

    private void displayAddRemovePokemonButton() {
        /*Button logOutButton = new Button("ADD/REMOVE POKEMON");

        setCSS(logOutButton);*/
        HomePageCentralButton addRemoveButton = new HomePageCentralButton("ADD/REMOVE POKEMON");

        sceneNodes.getChildren().add(addRemoveButton);
    }

    private void displayLogOutButton() {
        /*Button logOutButton = new Button("LOG OUT");

        setCSS(logOutButton);*/
        HomePageCentralButton logOutButton = new HomePageCentralButton("LOG OUT");

        logOutButton.setOnAction((ActionEvent ev)-> logOutButtonAction());

        sceneNodes.getChildren().add(logOutButton);
    }

    ///////////////// ACTIONS /////////////////
    private void logOutButtonAction() {
        CurrentUI.userExit();
        CurrentUI.changeScene(SceneNames.LOGIN);

        // TODO: exit from the account
    }

    private void teamButtonAction() {
        CurrentUI.changeScene(SceneNames.TEAM);
    }

    private void settingButtonAction() {
        CurrentUI.changeScene(SceneNames.SETTINGS);
    }
}
