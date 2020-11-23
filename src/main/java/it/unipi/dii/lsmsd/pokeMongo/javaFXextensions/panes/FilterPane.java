package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FilterLabel;
import javafx.scene.layout.Pane;

public class FilterPane extends Pane {
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
        FilterLabel name = new FilterLabel("Name", 20, 15);

        getChildren().addAll(name);
    }

    private void displayID() {

    }

    private void displayWeight() {

    }

    private void displayHeight() {

    }

    private void displayTypes() {

    }

    private void displayCatchRate() {

    }

    private void displayPoints() {

    }

    private void displayFilterButton() {
        RegularButton filterButton = new RegularButton("filter", 900, 160);

        getChildren().add(filterButton);
    }
}
