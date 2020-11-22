package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import javafx.scene.control.TextField;

public class CatchEmAllTextField extends TextField {
    public CatchEmAllTextField(String title, int x, int y) {
        super(title);
        relocate(x, y);

        getStyleClass().add("CatchEmAllTextField");
    }
}
