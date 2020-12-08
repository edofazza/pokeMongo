package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.group;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.FavoriteButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.PokemonWindowLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.PostsPane;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * This particular Group is used for creating the elements to be added in the
 * window which shows the information of a pokemon.
 */
public class PokemonWindowGroup extends Group {

    /**
     * Calls a series of function for displaying each field.
     * @param portraitUrl contains the url for the pokemon portrait
     * @param spriteUrl contains the url for the pokemon sprites
     * @param name name of the pokemon
     * @param type types of the pokemon
     * @param weight weight of the pokemon
     * @param height height of the pokemon
     * @param catchRate catch rate of the pokemon (value between 1 and 255)
     * @param biology description of the pokemon
     */
    //TODO: type is an array, change it
    public PokemonWindowGroup(String portraitUrl, String spriteUrl, String name, String type,
                              String weight, String height, String catchRate, String biology) {

        Logger.vvlog("Creating PokemonWindowGroup for pokemon " + name);

        displayFavorite(name);
        displayPortrait(portraitUrl);
        displaySprite(spriteUrl);
        displayPokemonName(name);
        displayTypes(type);
        displayWeight(weight);
        displayHeight(height);
        displayCatchRate(catchRate);
        displayBiology(biology);

        displayPosts();
    }


    private void displayFavorite(String name) {
        FavoriteButton favoriteStar =  new FavoriteButton(560, 10, 40, name);

        getChildren().add(favoriteStar);
    }
    /**
     * Adds to the class the Nodes related to the Portrait
     * @param url contains the url for the pokemon portrait
     */
    private void displayPortrait(String url) {
        ImageView portrait = new ImageView();
        portrait.setFitWidth(170);
        portrait.setFitHeight(170);
        portrait.relocate(20, 30);
        getChildren().add(portrait);

        CurrentUI.getImage(url).thenAccept(k -> {portrait.setImage(k);}); //TODO not fx thread error
    }

    /**
     * Adds to the class the Nodes related to the Sprite
     * @param url contains the url for the pokemon sprite
     */
    private void displaySprite(String url) {
        ImageView sprite = new ImageView();
        sprite.setFitWidth(60);
        sprite.setFitHeight(60);
        sprite.relocate(230, 10);

        getChildren().add(sprite);

        CurrentUI.getImage(url).thenAccept(k -> {sprite.setImage(k);}); //TODO not fx thread error
    }

    /**
     * Adds to the class the Nodes related to the pokemon's name
     * @param name name of the pokemon
     */
    private void displayPokemonName(String name) {
        Label pokeName = new Label(name);
        pokeName.relocate(280, 22);
        pokeName.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 25; -fx-font-weight: bold;");

        getChildren().add(pokeName);
    }

    /**
     * Adds to the class the Nodes related to the pokemon's types
     * @param types types of the pokemon
     */
    private void displayTypes(String types) {
        PokemonWindowLabel typesLabel = new PokemonWindowLabel("TYPE: " + types, 250, 80);

        getChildren().add(typesLabel);
    }

    /**
     * Adds to the class the Nodes related to the pokemon's weight
     * @param weight weight of the pokemon
     */
    private void displayWeight(String weight) {
        PokemonWindowLabel weightLabel = new PokemonWindowLabel("WEIGHT: " + weight, 250, 110);

        getChildren().add(weightLabel);
    }

    /**
     * Adds to the class the Nodes related to the pokemon's height
     * @param height height of the pokemon
     */
    private void displayHeight(String height) {
        PokemonWindowLabel heightLabel = new PokemonWindowLabel("HEIGHT: " + height, 250, 140);

        getChildren().add(heightLabel);
    }

    /**
     * Adds to the class the Nodes related to the pokemon's catch rate
     * @param catchRate pokemon's catch rate (value between 1 and 255)
     */
    private void displayCatchRate(String catchRate) {
        PokemonWindowLabel catchRateLabel = new PokemonWindowLabel("CATCH RATE: " + catchRate, 250, 170);

        getChildren().add(catchRateLabel);
    }

    /**
     * Adds to the class the Nodes related to the pokemon's biology. The Nodes created are a TextArea
     * in which the user can read the description (cannot modify it) and a Label over it, saying "Biology"
     * @param biology description of the pokemon
     */
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

    private void displayPosts() {
        Label postLabel = new Label("POSTS");
        postLabel.relocate( 815, 70);
        postLabel.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 15; -fx-font-weight: bold;");

        PostsPane pane = new PostsPane(640, 100, 430, 500);

        getChildren().addAll(postLabel, pane);
    }
}
