package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Button;

/**
 * Standard Central Button for the HomePage
 */
public class HomePageCentralButton extends Button {
    private final int x = 490;
    private final int y = 230;
    private final int delta = 50;
    private static int position = 0;

    /**
     * Instantiates a Button and associates it with a class style
     * @param text what will be written in the Button
     */
    public HomePageCentralButton(String text) {
        super();

        Logger.vvlog("Creating HomePageCentralButton at (" + x + ", " + y + ")");
        setText(text);
        relocate(x, y + position*delta);
        position++;

        // IN CASE OF RELOAD OF THE PAGE THIS WILL RESET THE
        // POSITIONS IN THE CORRECT WAY
        if (text.equals("LOG OUT"))
            position = 0;

        getStyleClass().add("HomePageCentralButton");
    }
}
