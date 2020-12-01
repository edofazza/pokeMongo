package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Button;

/**
 * Particular Button for stopping or starting the music.
 */
public class MusicButton extends Button {
    private final String imgAudioOnLocation = "file:img/audio.png";
    private final String imgAudioOffLocation = "file:img/audioOff.png";

    /**
     * Instantiates the Button and associates to it a class style
     * (<code>MusicButton</code>)
     * @param x the x axis position
     * @param y the x axis position
     */
    public MusicButton(int x, int y) {
        super();
        Logger.vvlog("Creating MusicButton at (" + x + ", " + y + ")");
        relocate(x, y);
        setPrefSize(30, 30);
        getStyleClass().add("MusicButton");
    }

    /**
     * Sets the image if the music is on or is off.
     * @param musicOn current behavior of the music
     */
    public void setImage(boolean musicOn) {

        Logger.vvlog("Setting musicOn image");
        if (musicOn) {
            setStyle("-fx-background-image: url(" + imgAudioOffLocation + ")");
        }
        else {
            setStyle("-fx-background-image: url(" + imgAudioOnLocation + ")");
        }
    }
}
