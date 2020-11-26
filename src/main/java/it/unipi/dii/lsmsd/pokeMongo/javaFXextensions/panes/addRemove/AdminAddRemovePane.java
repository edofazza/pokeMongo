package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.addRemove;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Specific pane showing the elements needed to add or remove a pokemon
 */
public class AdminAddRemovePane extends Pane {
    private TextField idTF;
    private TextField weightTF;
    private TextField type1TF;
    private TextField catchRateTF;
    private TextField portraitTF;

    private TextField nameTF;
    private TextField heightTF;
    private TextField type2TF;
    private TextField pointsTF;
    private TextField spriteTF;

    /**
     * Relocates the pane
     * @param x the x axis position
     * @param y the x axis position
     */
    public AdminAddRemovePane(int x, int y) {
        relocate(x, y);
    }

    /**
     * Adds to the scene the Node related to the specific action
     * @param action action selected by the admin user
     */
    public void setNodes(AdminAction action) {
        getChildren().clear();

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
     * Calls a series of funcion in order to add to the pane all the fields related
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
        displayPoints();
        displaySpriteUrl();

        RegularButton addButton = new RegularButton("ADD", 650, 400);

        getChildren().add(addButton);
    }

    /**
     * Adds to the pane a <code>CatchEmAllTextField</code> through the admin can select a pokemon
     * and a <code>RegularButton</code> for confirming the removing.
     */
    private void removeActionNodes() {
        CatchEmAllTextField pokemonName = new CatchEmAllTextField("Pokemon name", 200, 50);

        RegularButton removeButton = new RegularButton("REMOVE", 270, 120);

        getChildren().addAll(pokemonName, removeButton);
    }


    // ADD LEFT
    /**
     * Adds to the pane the fields related to the ID (<code>FielRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayID() {
        FieldRelatedLabel id = new FieldRelatedLabel("Pokedex ID", 0, 0);

        idTF = new TextField();
        idTF.relocate(0, 30);

        getChildren().addAll(id, idTF);
    }

    /**
     * Adds to the pane the fields related to the weight (<code>FielRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayWeight() {
        FieldRelatedLabel weight = new FieldRelatedLabel("Weight", 0, 70);

        weightTF = new TextField();
        weightTF.relocate(0, 100);

        getChildren().addAll(weight, weightTF);
    }

    /**
     * Adds to the pane the fields related to the first type (<code>FielRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayType1() {
        FieldRelatedLabel type1 = new FieldRelatedLabel("Type1", 0, 140);

        type1TF = new TextField();
        type1TF.relocate(0, 170);

        getChildren().addAll(type1, type1TF);
    }

    /**
     * Adds to the pane the fields related to the catch rate (<code>FielRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayCatchRate() {
        FieldRelatedLabel catchRate = new FieldRelatedLabel("Catch Rate", 0, 210);

        catchRateTF = new TextField();
        catchRateTF.relocate(0, 240);

        getChildren().addAll(catchRate, catchRateTF);
    }

    /**
     * Adds to the pane the fields related to the portrait url (<code>FielRelatedLabel</code> and
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
     * Adds to the pane the fields related to the name (<code>FielRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayName() {
        FieldRelatedLabel name = new FieldRelatedLabel("Pokemon Name", 400, 0);

        nameTF = new TextField();
        nameTF.relocate(400, 30);

        getChildren().addAll(name, nameTF);
    }

    /**
     * Adds to the pane the fields related to the height (<code>FielRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayHeight() {
        FieldRelatedLabel height = new FieldRelatedLabel("Height", 400, 70);

        heightTF = new TextField();
        heightTF.relocate(400, 100);

        getChildren().addAll(height, heightTF);
    }

    /**
     * Adds to the pane the fields related to the second type (<code>FielRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayType2() {
        FieldRelatedLabel type1 = new FieldRelatedLabel("Type2", 400, 140);

        type2TF = new TextField();
        type2TF.relocate(400, 170);

        getChildren().addAll(type1, type2TF);
    }

    /**
     * Adds to the pane the fields related to the points (<code>FielRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displayPoints() {
        FieldRelatedLabel points = new FieldRelatedLabel("Points", 400, 210);

        pointsTF = new TextField();
        pointsTF.relocate(400, 240);

        getChildren().addAll(points, pointsTF);
    }

    /**
     * Adds to the pane the fields related to the sprite url (<code>FielRelatedLabel</code> and
     * <code>TextField</code>)
     */
    private void displaySpriteUrl() {
        FieldRelatedLabel spriteURL = new FieldRelatedLabel("Sprite URL", 400, 280);

        spriteTF = new TextField();
        spriteTF.relocate(400, 310);

        getChildren().addAll(spriteURL, spriteTF);
    }
}
