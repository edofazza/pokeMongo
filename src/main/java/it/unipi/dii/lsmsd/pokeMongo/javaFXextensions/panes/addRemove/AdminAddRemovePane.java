package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.addRemove;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.exceptions.DuplicatePokemonException;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox.TypeComboBox;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.InvalidFormEntryLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.OnlyDecimalsTextField;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManager;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Specific pane showing the elements needed to add or remove a pokemon
 */
public class AdminAddRemovePane extends Pane {
    private OnlyDecimalsTextField idTF;
    private OnlyDecimalsTextField weightTF;
    private TypeComboBox type1TF;
    private OnlyDecimalsTextField catchRateTF;
    private TextField portraitTF;

    private TextField nameTF;
    private OnlyDecimalsTextField heightTF;
    private TypeComboBox type2TF;
    private TextField biologyTF;
    private TextField spriteTF;

    private CatchEmAllTextField pokemonName;

    private InvalidFormEntryLabel resultOperation;

    /**
     * Relocates the pane
     * @param x the x axis position
     * @param y the x axis position
     */
    public AdminAddRemovePane(int x, int y) {
        relocate(x, y);
        Logger.vvlog("Creating AdminAddRemovePane at (" + x + ", " + y + ")");

        resultOperation = new InvalidFormEntryLabel("", 650, 350, false);
    }

    /**
     * Adds to the scene the Node related to the specific action
     * @param action action selected by the admin user
     */
    public void setNodes(AdminAction action) {
        getChildren().clear();
        resultOperation.setVisible(false);
        getChildren().add(resultOperation);

        switch (action) {
            case ADD:
                addActionNodes();
                break;
            case REMOVE:
                removeActionNodes();
                break;
        }
    }

    /**
     * Calls a series of function in order to add to the pane all the fields related
     * to the ADD action.
     */
    private void addActionNodes() {
        displayID();
        displayWeight();
        displayType1();
        displayCatchRate();
        displayPortraitUrl();

        displayName();
        displayHeight();
        displayType2();
        //displayPoints();
        displayBiology();
        displaySpriteUrl();

        displayAddButton();
    }

    private void displayAddButton() {
        RegularButton addButton = new RegularButton("ADD", 650, 310);
        addButton.setOnAction(e -> addButtonAction());

        getChildren().add(addButton);
    }

    private void addButtonAction() {
        // prepare label for result
        resultOperation.relocate(650, 350);

        // CHECK IF THERE IS AN ERROR
        if (!idTF.getText().equals("") && !nameTF.getText().equals("") && !weightTF.getText().equals("") &&
            !heightTF.getText().equals("") && (!type1TF.getValue().toString().equals("") || !type2TF.getValue().toString().equals("")) &&
            !catchRateTF.getText().equals("") && !portraitTF.getText().equals("") && !spriteTF.getText().equals("") ) {
            PokemonManager pokemonManager = PokemonManagerFactory.buildManager();

            //TODO: Gestione tipi potrebbe essere gestita meglio con una List<String>
            String[] types;
            int size = 2;
            if(type1TF.getValue().toString().equals("") || type2TF.getValue().toString().equals(""))
                size = 1;
            types = new String[size];
            types[0] = (type1TF.getValue().toString().equals("") ? type2TF.getValue().toString() : type1TF.getValue().toString());
            if(size == 2){
                types[1] = type2TF.getValue().toString();
            }
            if (pokemonManager.addPokemon(
                    new Pokemon(
                            nameTF.getText(),
                            types,
                            Integer.parseInt(idTF.getText()),
                            Double.parseDouble(catchRateTF.getText()),
                            Integer.parseInt(heightTF.getText()),
                            Integer.parseInt(weightTF.getText()),
                            biologyTF.getText(),
                            portraitTF.getText(),
                            spriteTF.getText()
                    )
            ) ) {
                //TODO
                TeamManager teamManager = TeamManagerFactory.buildManager();
                try{
                    teamManager.addPokemon(new Pokemon(
                            nameTF.getText(),
                            types,
                            Integer.parseInt(idTF.getText()),
                            Double.parseDouble(catchRateTF.getText()),
                            Integer.parseInt(heightTF.getText()),
                            Integer.parseInt(weightTF.getText()),
                            biologyTF.getText(),
                            portraitTF.getText(),
                            spriteTF.getText()
                    ));

                    resultOperation.setText("Pokemon added");
                    resultOperation.setStyle("-fx-background-color: green;");
                    resultOperation.setVisible(true);
                } catch(DuplicatePokemonException dpe){
                    //Roll-back
                    pokemonManager.removePokemon(nameTF.getText());
                    resultOperation.setText("Pokemon not added: duplicate found");
                    resultOperation.setStyle("-fx-background-color: red;");
                    resultOperation.setVisible(true);
                }


            } else {
                resultOperation.setText("Something went wrong");
                resultOperation.setStyle("-fx-background-color: #FF211A;");
                resultOperation.setVisible(true);
            }

        } else {
            resultOperation.setText("Some parameters\nmissing");
            resultOperation.setStyle("-fx-background-color: #FF211A;");
            resultOperation.setVisible(true);
        }

    }

    /**
     * Adds to the pane a <code>CatchEmAllTextField</code> through the admin can select a pokemon
     * and a <code>RegularButton</code> for confirming the removing.
     */
    private void removeActionNodes() {
        resultOperation.relocate(260, 150);

        pokemonName = new CatchEmAllTextField("Pokemon name", 200, 50);

        RegularButton removeButton = new RegularButton("REMOVE", 270, 120);
        removeButton.setOnAction(e -> removeButtonAction());

        getChildren().addAll(pokemonName, removeButton);
    }

    private void removeButtonAction() {
        if (pokemonName.getText().equals("")) {
            resultOperation.setText("Insert pokemon's name");
            resultOperation.setStyle("-fx-background-color: #FF211A;");
        } else {
            PokemonManager pokemonManager = PokemonManagerFactory.buildManager();
            if(pokemonManager.removePokemon(pokemonName.getText())) {
                TeamManager teamManager = TeamManagerFactory.buildManager();
                teamManager.deletePokemon(pokemonName.getText());
                resultOperation.setText("Pokemon removed");
                resultOperation.setStyle("-fx-background-color: green;");
            } else {
                resultOperation.setText("Something went wrong");
                resultOperation.setStyle("-fx-background-color: #FF211A;");
            }
        }
        resultOperation.setVisible(true);
    }


    // ADD LEFT
    /**
     * Adds to the pane the fields related to the ID (<code>FieldRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayID() {
        FieldRelatedLabel id = new FieldRelatedLabel("Pokedex ID", 0, 0);

        idTF = new OnlyDecimalsTextField(0, 30);


        getChildren().addAll(id, idTF);
    }

    /**
     * Adds to the pane the fields related to the weight (<code>FieldRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayWeight() {
        FieldRelatedLabel weight = new FieldRelatedLabel("Weight", 0, 70);

        weightTF = new OnlyDecimalsTextField(0, 100);

        getChildren().addAll(weight, weightTF);
    }

    /**
     * Adds to the pane the fields related to the first type (<code>FieldRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayType1() {
        FieldRelatedLabel type1 = new FieldRelatedLabel("Type1", 0, 140);


        try{
            type1TF = new TypeComboBox(0, 170);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        getChildren().addAll(type1, type1TF);
    }

    /**
     * Adds to the pane the fields related to the catch rate (<code>FieldRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayCatchRate() {
        FieldRelatedLabel catchRate = new FieldRelatedLabel("Catch Rate", 0, 210);

        catchRateTF = new OnlyDecimalsTextField(0, 240);

        getChildren().addAll(catchRate, catchRateTF);
    }

    /**
     * Adds to the pane the fields related to the portrait url (<code>FieldRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayPortraitUrl() {
        FieldRelatedLabel portraitURL = new FieldRelatedLabel("Portrait URL", 0, 280);

        portraitTF = new TextField();
        portraitTF.relocate(0, 310);

        getChildren().addAll(portraitURL, portraitTF);
    }

    // ADD RIGHT
    /**
     * Adds to the pane the fields related to the name (<code>FieldRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayName() {
        FieldRelatedLabel name = new FieldRelatedLabel("Pokemon Name", 400, 0);

        nameTF = new TextField();
        nameTF.relocate(400, 30);

        getChildren().addAll(name, nameTF);
    }

    /**
     * Adds to the pane the fields related to the height (<code>FieldRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayHeight() {
        FieldRelatedLabel height = new FieldRelatedLabel("Height", 400, 70);

        heightTF = new OnlyDecimalsTextField(400, 100);

        getChildren().addAll(height, heightTF);
    }

    /**
     * Adds to the pane the fields related to the second type (<code>FieldRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayType2() {
        FieldRelatedLabel type1 = new FieldRelatedLabel("Type2", 400, 140);


        try {
            type2TF = new TypeComboBox(400, 170);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
        getChildren().addAll(type1, type2TF);
    }

    private void displayBiology() {
        FieldRelatedLabel points = new FieldRelatedLabel("Biology", 400, 210);

        biologyTF = new TextField();
        biologyTF.relocate(400, 240);

        getChildren().addAll(points, biologyTF);
    }

    /**
     * Adds to the pane the fields related to the sprite url (<code>FieldRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displaySpriteUrl() {
        FieldRelatedLabel spriteURL = new FieldRelatedLabel("Sprite URL", 400, 280);

        spriteTF = new TextField();
        spriteTF.relocate(400, 310);

        getChildren().addAll(spriteURL, spriteTF);
    }
}
