package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Button;

/**
 * This particular Button is used for remove a pokemon in the Team
 */
public class TrashCanButton extends Button {

    /**
     * Instantiates a Button and associates it with a class style
     * (<code>TrashCanButton</code>)
     * @param x the x axis position
     * @param y the y axis position
     */
    public TrashCanButton(int x, int y) {
        super();
        Logger.vvlog("Creating TrashCanButton at (" + x + ", " + y + ")");
        relocate(x, y);
        setPrefSize(30, 30);
        getStyleClass().add("TrashCanButton");
    }
}
