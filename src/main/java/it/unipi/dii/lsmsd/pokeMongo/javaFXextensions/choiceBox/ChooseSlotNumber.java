package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.choiceBox;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
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
        Logger.vvlog("Creating ChooseSlotNumber at (" + x + ", " + y + ")");
        setValue("1");

        relocate(x, y);

        getStyleClass().add("ChooseSlotNumber");
    }
}
