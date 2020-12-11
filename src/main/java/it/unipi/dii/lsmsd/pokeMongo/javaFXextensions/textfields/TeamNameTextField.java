package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields;

import it.unipi.dii.lsmsd.pokeMongo.config.ConfigDataHandler;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Specific TextField for the Team name in the Team scene.
 */
public class TeamNameTextField extends TextField {
    private final int limit = ConfigDataHandler.getInstance().configData.teamNameLimitCharacters;
    /**
     * Instantiates a TextField and associates it with a class style
     * @param text placeholder
     * @param x the x axis position
     * @param y the y axis position
     */
    public TeamNameTextField(String text, int x, int y) {
        super(text);
        Logger.vvlog("Creating TeamNameTextField '" + text + "' at (" + x + ", " + y + ")");

        relocate(x, y);

        getStyleClass().add("TeamNameTextField");
    }

    @Override
    public void replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        verify();
    }

    @Override
    public void replaceSelection(String text) {
        super.replaceSelection(text);
        verify();
    }

    private void verify() {
        if (getText().length() > limit) {
            setText(getText().substring(0, limit));
        }
    }
}
