package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.choiceBox;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class ChooseSlotNumber extends ChoiceBox {
    public ChooseSlotNumber(int x, int y) {
        super(FXCollections.observableArrayList(
                "1", "2", "3", "4", "5", "6"
        ));
        setValue("1");

        relocate(x, y);

        getStyleClass().add("ChooseSlotNumber");
    }
}
