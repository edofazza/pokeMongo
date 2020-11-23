package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import javafx.scene.control.RadioButton;

public class CircleButton extends RadioButton {
    public CircleButton(int x, int y) {
        relocate(x, y);
        getStyleClass().add("CircleButton");
    }
}
