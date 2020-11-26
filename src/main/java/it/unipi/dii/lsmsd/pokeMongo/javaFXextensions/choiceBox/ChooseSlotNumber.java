package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.choiceBox;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

/**
 * Particular ChoiceBox for selecting the slot to put the pokemon captured
 */
public class ChooseSlotNumber extends ChoiceBox {

    /**
     * Instantiates a ChoiceBox and associates it with a class style
     * @param x the x axis position
     * @param y the y axis position
     */
    public ChooseSlotNumber(int x, int y) {
        super(FXCollections.observableArrayList(
                "1", "2", "3", "4", "5", "6"
        ));
        setValue("1");

        relocate(x, y);

        getStyleClass().add("ChooseSlotNumber");
    }
}
