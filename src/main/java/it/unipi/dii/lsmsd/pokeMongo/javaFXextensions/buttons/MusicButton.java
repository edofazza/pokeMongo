package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import javafx.scene.control.Button;

public class MusicButton extends Button {
    private final String imgAudioOnLocation = "file:img/audio.png";
    private final String imgAudioOffLocation = "file:img/audioOff.png";

    public MusicButton(int x, int y) {
        super();
        relocate(x, y);
        setPrefSize(30, 30);
        getStyleClass().add("MusicButton");
    }

    public void setImage(boolean musicOn) {
        if (musicOn) {
            setStyle("-fx-background-image: url(" + imgAudioOffLocation + ")");
        }
        else {
            setStyle("-fx-background-image: url(" + imgAudioOnLocation + ")");
        }
    }
}
