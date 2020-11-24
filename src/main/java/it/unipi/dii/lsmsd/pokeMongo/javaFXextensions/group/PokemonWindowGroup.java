package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.group;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.PokemonWindowLabel;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class PokemonWindowGroup extends Group {
    //TODO: type is an array, change it
    public PokemonWindowGroup(String portraitUrl, String spriteUrl, String name, String type,
                              String weight, String height, String catchRate, String points, String biology) {
        displayPortrait(portraitUrl);
        displaySprite(spriteUrl);
        displayPokemonName(name);
        displayTypes(type);
        displayWeight(weight);
        displayHeight(height);
        displayCatchRate(catchRate);
        displayPoints(points);
        displayBiology(biology);
    }

    private void displayPortrait(String url) {
        ImageView portrait = new ImageView(url);
        portrait.setFitWidth(170);
        portrait.setFitHeight(170);
        portrait.relocate(20, 30);

        getChildren().add(portrait);
    }

    private void displaySprite(String url) {
        ImageView sprite = new ImageView(url);
        sprite.setFitWidth(60);
        sprite.setFitHeight(60);
        sprite.relocate(230, 10);

        getChildren().add(sprite);
    }

    private void displayPokemonName(String name) {
        Label pokeName = new Label(name);
        pokeName.relocate(280, 22);
        pokeName.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 25; -fx-font-weight: bold;");

        getChildren().add(pokeName);
    }

    private void displayTypes(String types) {
        PokemonWindowLabel typesLabel = new PokemonWindowLabel("TYPE: " + types, 250, 80);

        getChildren().add(typesLabel);
    }

    private void displayWeight(String weight) {
        PokemonWindowLabel weightLabel = new PokemonWindowLabel("WEIGHT: " + weight, 250, 110);

        getChildren().add(weightLabel);
    }

    private void displayHeight(String height) {
        PokemonWindowLabel heightLabel = new PokemonWindowLabel("HEIGHT: " + height, 250, 140);

        getChildren().add(heightLabel);
    }

    private void displayCatchRate(String catchRate) {
        PokemonWindowLabel catchRateLabel = new PokemonWindowLabel("CATCH RATE: " + catchRate, 250, 170);

        getChildren().add(catchRateLabel);
    }

    private void displayPoints(String points) {
        PokemonWindowLabel pointsLabel = new PokemonWindowLabel("POINTS: " + points, 250, 200);

        getChildren().add(pointsLabel);
    }

    private void displayBiology(String biology) {
        TextArea biologyText= new TextArea(biology);
        biologyText.setPrefSize(550, 120);
        biologyText.relocate(20, 250);
        biologyText.setWrapText(true);
        biologyText.setEditable(false);
        biologyText.setStyle("-fx-focus-color: -fx-control-inner-background; -fx-faint-focus-color: -fx-control-inner-background; -fx-border-color: grey;");

        Label biologyTitle = new Label("Biology");
        biologyTitle.relocate(20, 220);
        biologyTitle.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #70709d;");

        getChildren().addAll(biologyTitle, biologyText);
    }
}
