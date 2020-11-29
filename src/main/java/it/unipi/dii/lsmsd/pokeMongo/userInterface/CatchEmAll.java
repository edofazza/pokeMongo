package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.choiceBox.ChooseSlotNumber;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.imageviews.BackgroundImage;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.InvalidFormEntryLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.TitleLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import it.unipi.dii.lsmsd.pokeMongo.persistence.Filter;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManagerOnMongoDb;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerOnMongoDb;

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
     * <em>Constructor</em>. Called a series of function in order to create the <em>Node</em>
     * needed to set the scene up. It also sets the music.
     */
    public CatchEmAll() {
        displayTitle();

        displaySelectPokemon();
        displaySelectSlot();

        displaySelectedPokemon();
        displayOdd();

        displayTryToCatch();

        displayResultLabel();

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
        selectPokemonTF = new CatchEmAllTextField("", 530, 200);

        //For text Hint
        selectPokemonTF.setPromptText("Type pokemon name"); //to set the hint text

        selectPokemonTF.setOnKeyReleased(e->loadPokemonInfoByName(selectPokemonTF.getText()));
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
    // TODO: change it every time the selectPokemon changes
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

    private void displayResultLabel() {
        invalidFormEntryLabel = new InvalidFormEntryLabel("", 600, 550, false);
        sceneNodes.getChildren().add(invalidFormEntryLabel);
    }


    //---------------------------------------
    // METHODS TO BE PLACED SOMEWHERE ELSE
    //---------------------------------------

    private void loadPokemonInfoByName(String pokemonName){
        if (invalidFormEntryLabel.isVisible())
            invalidFormEntryLabel.setVisible(false);

        HashMap<Filter, String> hashMap = new HashMap<>();
        hashMap.put(Filter.NAME, pokemonName);

        PokemonManagerOnMongoDb pokemonManagerOnMongoDb = new PokemonManagerOnMongoDb();
        ArrayList<Pokemon> arrayList = pokemonManagerOnMongoDb.searchWithFilter(hashMap);

        if (arrayList.size() != 0) {
            pokemon = arrayList.get(0);
            oddLabel.setText("ODD: " + String.format("%.2f", (pokemon.getCapture_rate() *100/255)) + "%");
            CurrentUI.getImage(pokemon.getPortrait()).thenAccept(k -> selectedPokemon.setImage(k)); //TODO not fx thread error
        } else {
            pokemon = null;
            oddLabel.setText("ODD: ");
            CurrentUI.getImage(imgLocation + "portraits/0.png").thenAccept(k -> selectedPokemon.setImage(k)); //TODO not fx thread error
        }
    }

    private void tryToCatchAction() {
        if (pokemon != null && CurrentUI.getNumberOfPokeball() != 0) {
            // DECREMENT POKEBALL FROM THE USER
            CurrentUI.decrementPokeball();
            updatePokeBallsLabelNumber();

            // DECREMENT ALSO IN THE DB
            UserManagerOnMongoDb userManagerOnMongoDb = new UserManagerOnMongoDb();
            userManagerOnMongoDb.updateNumberOfPokeball(CurrentUI.getUser());

            if ((Math.random() * 254 + 1) < pokemon.getCapture_rate()) {
                invalidFormEntryLabel.setText(pokemon.getName() + " caught");
                invalidFormEntryLabel.setVisible(true);
                invalidFormEntryLabel.setStyle("-fx-background-color: green");

                // TODO: ADD THE POKEMON TO TEAM

            } else {
                invalidFormEntryLabel.setText(pokemon.getName() + " uncaught");
                invalidFormEntryLabel.setVisible(true);
                invalidFormEntryLabel.setStyle("-fx-background-color: #FF211A");
            }
        }
    }


}
