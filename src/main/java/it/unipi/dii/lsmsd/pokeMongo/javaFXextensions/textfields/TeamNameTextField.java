package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import javafx.scene.control.TextField;

public class TeamNameTextField extends TextField {
    public TeamNameTextField(String text, int x, int y) {
        super(text);
        relocate(x, y);
        getStyleClass().add("TeamNameTextField");
    }
}
