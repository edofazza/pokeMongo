package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import javafx.scene.control.Button;

public class MusicButton extends Button {
    private boolean musicOn = true;

    public MusicButton(int x, int y) {
        super("A");
        relocate(x, y);
        setPrefSize(30, 30);
    }
}
