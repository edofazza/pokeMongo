package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.addRemove;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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

    public AdminAddRemovePane(int x, int y) {
        relocate(x, y);
    }

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

    private void removeActionNodes() {
        CatchEmAllTextField pokemonName = new CatchEmAllTextField("Pokemon name", 200, 50);

        RegularButton removeButton = new RegularButton("REMOVE", 270, 120);

        getChildren().addAll(pokemonName, removeButton);
    }


    // ADD LEFT
    private void displayID() {
        FieldRelatedLabel id = new FieldRelatedLabel("Pokedex ID", 0, 0);

        idTF = new TextField();
        idTF.relocate(0, 30);

        getChildren().addAll(id, idTF);
    }

    private void displayWeight() {
        FieldRelatedLabel weight = new FieldRelatedLabel("Weight", 0, 70);

        weightTF = new TextField();
        weightTF.relocate(0, 100);

        getChildren().addAll(weight, weightTF);
    }

    private void displayType1() {
        FieldRelatedLabel type1 = new FieldRelatedLabel("Type1", 0, 140);

        type1TF = new TextField();
        type1TF.relocate(0, 170);

        getChildren().addAll(type1, type1TF);
    }

    private void displayCatchRate() {
        FieldRelatedLabel catchRate = new FieldRelatedLabel("Catch Rate", 0, 210);

        catchRateTF = new TextField();
        catchRateTF.relocate(0, 240);

        getChildren().addAll(catchRate, catchRateTF);
    }

    private void displayPortraitUrl() {
        FieldRelatedLabel portraitURL = new FieldRelatedLabel("Portrait URL", 0, 280);

        portraitTF = new TextField();
        portraitTF.relocate(0, 310);

        getChildren().addAll(portraitURL, portraitTF);
    }

    // ADD RIGHT
    private void displayName() {
        FieldRelatedLabel name = new FieldRelatedLabel("Pokemon Name", 400, 0);

        nameTF = new TextField();
        nameTF.relocate(400, 30);

        getChildren().addAll(name, nameTF);
    }

    private void displayHeight() {
        FieldRelatedLabel height = new FieldRelatedLabel("Height", 400, 70);

        heightTF = new TextField();
        heightTF.relocate(400, 100);

        getChildren().addAll(height, heightTF);
    }

    private void displayType2() {
        FieldRelatedLabel type1 = new FieldRelatedLabel("Type2", 400, 140);

        type2TF = new TextField();
        type2TF.relocate(400, 170);

        getChildren().addAll(type1, type2TF);
    }

    private void displayPoints() {
        FieldRelatedLabel points = new FieldRelatedLabel("Points", 400, 210);

        pointsTF = new TextField();
        pointsTF.relocate(400, 240);

        getChildren().addAll(points, pointsTF);
    }

    private void displaySpriteUrl() {
        FieldRelatedLabel spriteURL = new FieldRelatedLabel("Sprite URL", 400, 280);

        spriteTF = new TextField();
        spriteTF.relocate(400, 310);

        getChildren().addAll(spriteURL, spriteTF);
    }
}
