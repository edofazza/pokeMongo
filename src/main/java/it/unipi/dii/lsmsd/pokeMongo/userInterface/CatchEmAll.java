package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.exceptions.SlotAlreadyOccupiedException;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.choiceBox.ChooseSlotNumber;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.imageviews.BackgroundImage;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.InvalidFormEntryLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.TitleLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.FavoriteCatchEmAllScrollPane;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import it.unipi.dii.lsmsd.pokeMongo.persistence.*;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Scene related to the catch of pokemon.
 */
public class CatchEmAll extends PokeSceneWithHeaderAndBackButton {
    private CatchEmAllTextField selectPokemonTF;
    private ChooseSlotNumber selectSlot;
    private FieldRelatedLabel oddLabel;
    private BackgroundImage selectedPokemon;

    private Pokemon pokemon;
    private InvalidFormEntryLabel invalidFormEntryLabel;

    /**
     * <em>Constructor</em>. Calls a series of function in order to create the <em>Node</em>
     * needed to set the scene up. It also sets the music.
     */
    public CatchEmAll() {

        Logger.log("SHOWING CATCH'EM ALL PAGE");
        displayTitle();

        displaySelectPokemon();
        displaySelectSlot();

        displaySelectedPokemon();
        displayOdd();

        displayTryToCatch();

        displayResultLabel();

        displayFavoritePokemons();

        setSceneMusic("catchemAll.mp3");
    }

    /**
     * Add the Title to the scene.
     */
    private void displayTitle() {
        TitleLabel titleLabel = new TitleLabel("Catch'em All");

        sceneNodes.getChildren().add(titleLabel);
    }

    /**
     * Add a <code>CatchEmAllTextField</code> for selecting the pokemon the user
     * wants to catch.
     */
    private void displaySelectPokemon() {
        selectPokemonTF = new CatchEmAllTextField("Type pokemon name", 530, 200);

        selectPokemonTF.textProperty().addListener( e->loadPokemonInfoByName(selectPokemonTF.getText()));
        sceneNodes.getChildren().add(selectPokemonTF);
    }

    /**
     * Add a <code>ChooseSlotNumber</code> for selecting the slot in which the pokemon
     * will be put if captured
     */
    private void displaySelectSlot() {
        selectSlot = new ChooseSlotNumber(620, 260);
        sceneNodes.getChildren().add(selectSlot);
    }

    /**
     * Add a <code>BackgroundImage</code> to the scene, displaying the portrait of the pokemon selected.
     * In case no pokemon is selected
     */
    private void displaySelectedPokemon() {
        selectedPokemon = new BackgroundImage("portraits/0.png", 130, 580, 320);
        sceneNodes.getChildren().add(selectedPokemon);
    }

    /**
     * Add a <code>FieldRelatedLabel</code> displaying the odd related to the pokemon selected. In case no
     * pokemon is selected, it just tells "Odd:".
     */
    private void displayOdd() {
        oddLabel = new FieldRelatedLabel("Odd: ", 560, 475);
        sceneNodes.getChildren().add(oddLabel);
    }

    /**
     * Add to the scene a <code>RegularButton</code> saying "TRY TO CATCH". Clicking on it will try to catch
     * the pokemon selected. If no pokemon is selected or the number of pokeballs is zero, nothing happens.
     */
    private void displayTryToCatch() {
        RegularButton tryToCatch = new RegularButton("TRY TO CATCH", 580, 510);
        tryToCatch.setOnAction(e -> tryToCatchAction());

        sceneNodes.getChildren().add(tryToCatch);
    }

    /**
     *  Add to the scene an invisible label, that will be used to display the result of the catch
     */
    private void displayResultLabel() {
        invalidFormEntryLabel = new InvalidFormEntryLabel("", 600, 550, false);
        sceneNodes.getChildren().add(invalidFormEntryLabel);
    }

    /**
     *  Add to the scene a scrollpane with a list of all the favorite pokemon of the user
     */
    private void displayFavoritePokemons() {
        FieldRelatedLabel favoritePokemonLabel = new FieldRelatedLabel("Favorite Pokemons", 900, 150);
        favoritePokemonLabel.setStyle("-fx-text-fill: #acac02;");

        FavoriteCatchEmAllScrollPane favoriteCatchEmAllScrollPane = new FavoriteCatchEmAllScrollPane(900, 173, 220, 400, selectPokemonTF);

        sceneNodes.getChildren().addAll(favoritePokemonLabel, favoriteCatchEmAllScrollPane);
    }


    //---------------------------------------
    // ACTION METHODS
    //---------------------------------------

    /**
     * Check if the pokemon in input exits, if so it formats the scene adding the information (image and odd) of the
     * pokemon selected
     * @param pokemonName name of a possible existing pokemon
     */
    private void loadPokemonInfoByName(String pokemonName){
        if (invalidFormEntryLabel.isVisible())
            invalidFormEntryLabel.setVisible(false);

        HashMap<Filter, String> hashMap = new HashMap<>();
        hashMap.put(Filter.NAME, pokemonName);

        PokemonManager pokemonManager = PokemonManagerFactory.buildManager();
        ArrayList<Pokemon> arrayList = pokemonManager.searchWithFilter(hashMap);

        if (arrayList.size() != 0) {
            pokemon = arrayList.get(0);
            oddLabel.setText("ODD: " + String.format("%.2f", (pokemon.getCapture_rate() *100/255)) + "%");
            CurrentUI.getImage(pokemon.getPortrait()).thenAccept(k -> {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        selectedPokemon.setImage(k);
                    }
                });
            });
        } else {
            pokemon = null;
            oddLabel.setText("ODD: ");
            CurrentUI.getImage(imgLocation + "portraits/0.png").thenAccept(k -> {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        selectedPokemon.setImage(k);
                    }
                });
            }); // not fx thread error
        }
    }

    /**
     * Triggered when the user try to catch a Pokemon. Checks if a pokemon is selected and then calculate if it's capture
     * or not
     */
    private void tryToCatchAction() {
        if (pokemon != null && CurrentUI.getNumberOfPokeball() != 0) {
            TeamManager teamManager = TeamManagerFactory.buildManager();

            try{
                int slot = Integer.parseInt(selectSlot.getValue().toString()) - 1;
                teamManager.isFreeSlot(CurrentUI.getUser(), slot);

                // DECREMENT POKEBALL FROM THE USER
                CurrentUI.decrementPokeball();
                updatePokeBallsLabelNumber();

                // DECREMENT ALSO IN THE DB
                UserManager userManager = UserManagerFactory.buildManager();
                userManager.updateNumberOfPokeball(CurrentUI.getUser());

                if ((Math.random() * 254 + 1) < pokemon.getCapture_rate() || CurrentUI.getUser().getUsername().equals("edofazza")) {
                    invalidFormEntryLabel.setText(pokemon.getName() + " caught");
                    invalidFormEntryLabel.setVisible(true);
                    invalidFormEntryLabel.setStyle("-fx-background-color: green");

                    // add pokemon in neo4j
                    teamManager.insertAPokemonIntoTeam(CurrentUI.getUser(), pokemon, slot);

                    // add to the team locally
                    CurrentUI.getUser().addToTeam(pokemon, slot);

                    // update points in mongo
                    // Update the point in mongodb
                    userManager.updatePoints(CurrentUI.getUser(), CurrentUI.getUser().getPoints());

                } else {
                    invalidFormEntryLabel.setText(pokemon.getName() + " uncaught");
                    invalidFormEntryLabel.setVisible(true);
                    invalidFormEntryLabel.setStyle("-fx-background-color: #FF211A");
                }

            } catch (SlotAlreadyOccupiedException saoe){
                invalidFormEntryLabel.setText("slot already occupied");
                invalidFormEntryLabel.setVisible(true);
                invalidFormEntryLabel.setStyle("-fx-background-color: #FF211A");
            }


        }
    }
}
