package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FilterLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.FilterTextField;
import javafx.scene.layout.Pane;

public class FilterPane extends Pane {
    private FilterTextField nameTF;
    private FilterTextField idTF;
    private FilterTextField weightMinTF;
    private FilterTextField weightMaxTF;

    private FilterTextField heightMinTF;
    private FilterTextField heightMaxTF;
    private FilterTextField type1TF;
    private FilterTextField type2TF;

    private FilterTextField catchRateMinTF;
    private FilterTextField getCatchRateMaxTF;
    private FilterTextField minPointsTF;
    private FilterTextField maxPointsTF;

    public FilterPane(int x, int y) {
        relocate(x, y);

        displayFields();

        getStyleClass().add("FilterPane");
    }

    private void displayFields() {
        displayName();
        displayID();
        displayWeight();
        displayHeight();
        displayTypes();
        displayCatchRate();
        displayPoints();
        displayFilterButton();
    }

    private void displayName() {
        FilterLabel name = new FilterLabel("Name", 78, 17);

        nameTF = new FilterTextField(120, 15);   // WIDTH: 84

        getChildren().addAll(name, nameTF);
    }

    private void displayID() {
        FilterLabel id = new FilterLabel("Pokedex ID", 280, 17);

        idTF = new FilterTextField(355, 15);

        getChildren().addAll(id, idTF);
    }

    private void displayWeight() {
        // MIN
        FilterLabel minWeight = new FilterLabel("Min Weight", 483, 17);
        weightMinTF = new FilterTextField(560, 15);

        // MAX
        FilterLabel maxWeight = new FilterLabel("Max Weight", 693, 17);
        weightMaxTF = new FilterTextField(775, 15);

        getChildren().addAll(minWeight, weightMinTF, maxWeight, weightMaxTF);
    }

    private void displayHeight() {
        FilterLabel heightMin = new FilterLabel("Min Height", 48, 60);
        heightMinTF = new FilterTextField(120, 60);

        FilterLabel heightMax = new FilterLabel("Max Height", 280, 60);
        heightMaxTF = new FilterTextField(355, 60);

        getChildren().addAll(heightMin, heightMinTF, heightMax, heightMaxTF);
    }

    private void displayTypes() {
        // TYPE 1
        FilterLabel type1 = new FilterLabel("Type1", 515, 60);
        type1TF = new FilterTextField(560, 60);

        // TYPE 2
        FilterLabel type2 = new FilterLabel("Type2", 730, 60);
        type2TF = new FilterTextField(775, 60);

        getChildren().addAll(type1, type1TF, type2, type2TF);
    }

    private void displayCatchRate() {
        // MIN CATCH RATE
        FilterLabel minCatchRate = new FilterLabel("Min Catch Rate", 20, 103);
        catchRateMinTF = new FilterTextField(120, 103);

        // MAX CATCH RATE
        FilterLabel maxCatchRate = new FilterLabel("Max Catch Rate", 250, 103);
        getCatchRateMaxTF = new FilterTextField(355, 103);

        getChildren().addAll(minCatchRate, catchRateMinTF, maxCatchRate, getCatchRateMaxTF);
    }

    private void displayPoints() {
        // MIN POINTS
        FilterLabel minPoints = new FilterLabel("Min Points", 488, 103);
        minPointsTF = new FilterTextField(560, 103);

        // MAX POINTS
        FilterLabel maxPoints = new FilterLabel("Max Points", 700, 103);
        maxPointsTF = new FilterTextField(775, 103);

        getChildren().addAll(minPoints, minPointsTF, maxPoints, maxPointsTF);
    }

    private void displayFilterButton() {
        RegularButton filterButton = new RegularButton("filter", 900, 160);

        getChildren().add(filterButton);
    }
}
