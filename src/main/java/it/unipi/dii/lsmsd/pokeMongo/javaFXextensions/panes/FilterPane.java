package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox.TypeForFilteringComboBox;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FilterLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.FilterTextField;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.FilterTextFieldOnlyNumeric;
import it.unipi.dii.lsmsd.pokeMongo.persistence.Filter;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManagerFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Specific pane showing the elements needed in the filter (Pokedex)
 */
public class FilterPane extends Pane {
    private FilterTextField nameTF;
    private FilterTextFieldOnlyNumeric idTF;
    private FilterTextFieldOnlyNumeric weightMinTF;
    private FilterTextFieldOnlyNumeric weightMaxTF;

    private FilterTextFieldOnlyNumeric heightMinTF;
    private FilterTextFieldOnlyNumeric heightMaxTF;
    private TypeForFilteringComboBox type1CB;
    private TypeForFilteringComboBox type2CB;

    private FilterTextFieldOnlyNumeric catchRateMinTF;
    private FilterTextFieldOnlyNumeric catchRateMaxTF;
    private FilterTextFieldOnlyNumeric minPointsTF;
    private FilterTextFieldOnlyNumeric maxPointsTF;

    /**
     * Relocates the pane
     * @param x the x axis position
     * @param y the x axis position
     */
    public FilterPane(int x, int y) {
        relocate(x, y);

        displayFields();

        getStyleClass().add("FilterPane");
    }

    /**
     * Calls a series of function in order to add to the scene all the <code>Node</code>
     */
    private void displayFields() {
        displayName();
        displayID();
        displayWeight();
        displayHeight();
        displayTypes();
        displayCatchRate();
        //displayPoints();
        displayFilterButton();
    }

    /**
     * Adds to the pane the fields related to the name (<code>FilterLabel</code> and
     * <code>FilterTextField</code>)
     */
    private void displayName() {
        FilterLabel name = new FilterLabel("Name", 78, 17);

        nameTF = new FilterTextField(120, 15);   // WIDTH: 84

        getChildren().addAll(name, nameTF);
    }

    /**
     * Adds to the pane the fields related to the ID (<code>FilterLabel</code> and
     * <code>FilterTextField</code>)
     */
    private void displayID() {
        FilterLabel id = new FilterLabel("Pokedex ID", 280, 17);

        idTF = new FilterTextFieldOnlyNumeric(355, 15);

        getChildren().addAll(id, idTF);
    }

    /**
     * Adds to the pane the fields related to the weight, min and max (2 <code>FilterLabel</code> and
     * 2 <code>FilterTextField</code>)
     */
    private void displayWeight() {
        // MIN
        FilterLabel minWeight = new FilterLabel("Min Weight", 483, 17);
        weightMinTF = new FilterTextFieldOnlyNumeric(560, 15);

        // MAX
        FilterLabel maxWeight = new FilterLabel("Max Weight", 693, 17);
        weightMaxTF = new FilterTextFieldOnlyNumeric(775, 15);

        getChildren().addAll(minWeight, weightMinTF, maxWeight, weightMaxTF);
    }

    /**
     * Adds to the pane the fields related to the height, min and max (2 <code>FilterLabel</code> and
     * 2 <code>FilterTextField</code>)
     */
    private void displayHeight() {
        FilterLabel heightMin = new FilterLabel("Min Height", 48, 60);
        heightMinTF = new FilterTextFieldOnlyNumeric(120, 60);

        FilterLabel heightMax = new FilterLabel("Max Height", 280, 60);
        heightMaxTF = new FilterTextFieldOnlyNumeric(355, 60);

        getChildren().addAll(heightMin, heightMinTF, heightMax, heightMaxTF);
    }

    /**
     * Adds to the pane the fields related to the types (2 <code>FilterLabel</code> and
     * 2 <code>FilterTextField</code>)
     */
    private void displayTypes() {
        // TYPE 1
        FilterLabel type1 = new FilterLabel("Type1", 515, 60);
;
        try {
            type1CB = new TypeForFilteringComboBox(560, 60);
            getChildren().add(type1CB);
        } catch (IOException e) { e.printStackTrace(); }

        // TYPE 2
        FilterLabel type2 = new FilterLabel("Type2", 730, 60);
        try {
            type2CB = new TypeForFilteringComboBox(775, 60);
            getChildren().add(type2CB);
        } catch (IOException e) { e.printStackTrace(); }

        getChildren().addAll(type1, type2);
    }

    /**
     * Adds to the pane the fields related to the catch rate, min and max (2 <code>FilterLabel</code> and
     * 2 <code>FilterTextField</code>)
     */
    private void displayCatchRate() {
        // MIN CATCH RATE
        FilterLabel minCatchRate = new FilterLabel("Min Catch Rate", 20, 103);
        catchRateMinTF = new FilterTextFieldOnlyNumeric(120, 103);

        // MAX CATCH RATE
        FilterLabel maxCatchRate = new FilterLabel("Max Catch Rate", 250, 103);
        catchRateMaxTF = new FilterTextFieldOnlyNumeric(355, 103);

        getChildren().addAll(minCatchRate, catchRateMinTF, maxCatchRate, catchRateMaxTF);
    }

    /**
     * Adds to the pane the fields related to the points, min and max (2 <code>FilterLabel</code> and
     * 2 <code>FilterTextField</code>)
     */
    private void displayPoints() {
        // MIN POINTS
        FilterLabel minPoints = new FilterLabel("Min Points", 488, 103);
        minPointsTF = new FilterTextFieldOnlyNumeric(560, 103);

        // MAX POINTS
        FilterLabel maxPoints = new FilterLabel("Max Points", 700, 103);
        maxPointsTF = new FilterTextFieldOnlyNumeric(775, 103);

        getChildren().addAll(minPoints, minPointsTF, maxPoints, maxPointsTF);
    }

    /**
     * Adds to the pane the button to confirm filtering
     */
    private void displayFilterButton() {
        RegularButton filterButton = new RegularButton("filter", 900, 160);
        filterButton.setOnAction(e -> filterButtonAction());

        getChildren().add(filterButton);
    }

    private void filterButtonAction() {
        HashMap<Filter, String> tmpFilterMap = new HashMap<>();

        if (!nameTF.getText().equals(""))
            tmpFilterMap.put(Filter.NAME, nameTF.getText());

        if (!idTF.getText().equals(""))
            tmpFilterMap.put(Filter.POKEDEX_ID, idTF.getText());

        if (!weightMinTF.getText().equals(""))
            tmpFilterMap.put(Filter.MIN_WEIGHT, weightMinTF.getText());

        if (!weightMaxTF.getText().equals(""))
            tmpFilterMap.put(Filter.MAX_WEIGHT, weightMaxTF.getText());

        if (!heightMinTF.getText().equals(""))
            tmpFilterMap.put(Filter.MIN_HEIGHT, heightMinTF.getText());

        if (!heightMaxTF.getText().equals(""))
            tmpFilterMap.put(Filter.MAX_HEIGHT, heightMaxTF.getText());

        if (!type1CB.getValue().toString().equals(""))
            tmpFilterMap.put(Filter.TYPE1, type1CB.getValue().toString());

        if (!type2CB.getValue().toString().equals(""))
            tmpFilterMap.put(Filter.TYPE2, type2CB.getValue().toString());

        if (!catchRateMinTF.getText().equals(""))
            tmpFilterMap.put(Filter.MIN_CATCH_RATE, catchRateMinTF.getText());

        if (!catchRateMaxTF.getText().equals(""))
            tmpFilterMap.put(Filter.MAX_CATCH_RATE, catchRateMaxTF.getText());

        /*if (!minPointsTF.getText().equals(""))
            tmpFilterMap.put(Filter.MIN_POINTS, minPointsTF.getText());

        if (!maxPointsTF.getText().equals(""))
            tmpFilterMap.put(Filter.MAX_POINTS, maxPointsTF.getText());*/

        if(tmpFilterMap.isEmpty()) {
            PokedexResultScrollPane.clearVBox();
            return;
        }

        // CALL MONGODB FUNCTION
        PokemonManager pokemonManager = PokemonManagerFactory.buildManager();

        // DISPLAY RESULTS
        ArrayList<Pokemon> pokemonArray = pokemonManager.searchWithFilter(tmpFilterMap);

        PokedexResultScrollPane.clearVBox();
        if (pokemonArray.size() != 0)
            PokedexResultScrollPane.addResult(pokemonArray);
    }
}
