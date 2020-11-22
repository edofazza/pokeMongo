package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import javafx.scene.control.Button;

public class RegularButton extends Button {
    public RegularButton(String text, int x, int y) {
        super();
        setText(text);
        relocate(x, y);
        getStyleClass().add("SubmissionButton");
    }
}
