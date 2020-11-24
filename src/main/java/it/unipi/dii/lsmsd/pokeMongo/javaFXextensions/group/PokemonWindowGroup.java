package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.group;

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
        sprite.setFitWidth(50);
        sprite.setFitHeight(50);
        sprite.relocate(240, 20);

        getChildren().add(sprite);
    }

    private void displayPokemonName(String name) {
        Label pokeName = new Label(name);
        pokeName.relocate(280, 30);
        pokeName.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 18; -fx-font-weight: bold;");

        getChildren().add(pokeName);
    }

    private void displayTypes(String types) {
        Label typesLabel = new Label("Type: " + types);
        typesLabel.relocate(240, 80);

        getChildren().add(typesLabel);
    }

    private void displayWeight(String weight) {
        Label weightLabel = new Label("Weight: " + weight);
        weightLabel.relocate(240, 100);

        getChildren().add(weightLabel);
    }

    private void displayHeight(String height) {
        Label heightLabel = new Label("Height: " + height);
        heightLabel.relocate(240, 120);

        getChildren().add(heightLabel);
    }

    private void displayCatchRate(String catchRate) {
        Label catchRateLabel = new Label("Height: " + catchRate);
        catchRateLabel.relocate(240, 140);

        getChildren().add(catchRateLabel);
    }

    private void displayPoints(String points) {
        Label pointsLabel = new Label("Points: " + points);
        pointsLabel.relocate(240, 160);

        getChildren().add(pointsLabel);
    }

    private void displayBiology(String biology) {
        TextArea biologyText= new TextArea(biology);
        biologyText.setPrefSize(550, 120);
        biologyText.relocate(20, 250);
        biologyText.setWrapText(true);
        biologyText.setEditable(false);

        Label biologyTitle = new Label("Biology");
        biologyTitle.relocate(20, 220);
        biologyTitle.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #70709d;");

        getChildren().addAll(biologyTitle, biologyText);
    }
}
