package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import javafx.scene.control.Button;

public class TrashCanButton extends Button {
    public TrashCanButton(int x, int y) {
        super();
        relocate(x, y);
        setPrefSize(30, 30);
        getStyleClass().add("TrashCanButton");
    }
}
