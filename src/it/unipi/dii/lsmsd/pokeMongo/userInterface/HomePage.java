package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class HomePage extends PokeSceneWithHeaderAndAggregateBlastoiseCharizard {
    public HomePage() {
        displayPokedexButton();
        displayTeamButton();
        displayCatchemAllButton();
        displayRankingButton();
        displaySettingsButton();
        displayLogOutButton();
        setSceneMusic("professor_oak_theme.mp3");
    }

    private void setCSS(Button b) { //TODO: remove it with a final string or css file
        b.setStyle("-fx-font-size: 20px; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold; " +
                "-fx-background-color: transparent; " +
                "-fx-border-color: #000000; " +
                "-fx-min-width: 200;" +
                "-fx-min-height: 40;");
    }

    private void displayPokedexButton() {
        Button pokedexButton = new Button("POKEDEX");
        pokedexButton.relocate(530, 200);

        setCSS(pokedexButton);

        sceneNodes.getChildren().add(pokedexButton);
    }

    private void displayTeamButton() {
        Button teamButton = new Button("TEAM");
        teamButton.relocate(530, 250);

        setCSS(teamButton);

        sceneNodes.getChildren().add(teamButton);
    }

    private void displayCatchemAllButton() {
        Button catchemAllButton = new Button("CATCH'EM ALL");
        catchemAllButton.relocate(530, 300);

        setCSS(catchemAllButton);

        sceneNodes.getChildren().add(catchemAllButton);
    }

    private void displayRankingButton() {
        Button rankingButton = new Button("RANKING");
        rankingButton.relocate(530, 350);

        setCSS(rankingButton);

        sceneNodes.getChildren().add(rankingButton);
    }

    private void displaySettingsButton() {
        Button settingButton = new Button("SETTINGS");
        settingButton.relocate(530, 400);

        setCSS(settingButton);

        sceneNodes.getChildren().add(settingButton);
    }

    private void displayLogOutButton() {
        Button logOutButton = new Button("LOG OUT");
        logOutButton.relocate(530, 450);

        setCSS(logOutButton);

        logOutButton.setOnAction((ActionEvent ev)-> logOutButtonAction());

        sceneNodes.getChildren().add(logOutButton);
    }

    private void logOutButtonAction() {
        CurrentUI.changeScene(SceneNames.LOGIN);
    }
}
