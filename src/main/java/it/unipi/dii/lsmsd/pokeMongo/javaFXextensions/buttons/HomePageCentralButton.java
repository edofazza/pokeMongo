package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import javafx.scene.control.Button;

public class HomePageCentralButton extends Button {
    private final int x = 490;
    private final int y = 230;
    private final int delta = 50;
    private static int position = 0;

    public HomePageCentralButton(String text) {
        super();
        setText(text);
        relocate(x, y + position*delta);
        position++;

        // IN CASE OF RELOAD OF THE PAGE THIS IF RESET THE
        // POSITIONS IN THE CORRECT WAY
        if (text.equals("LOG OUT"))
            position = 0;

        getStyleClass().add("HomePageCentralButton");
    }
}
