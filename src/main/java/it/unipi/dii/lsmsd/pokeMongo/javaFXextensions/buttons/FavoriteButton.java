package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.Button;

public class FavoriteButton extends Button {
    private final String imgFavoriteOnLocation = "file:img/star.png";
    private final String imgFavoriteOffLocation = "file:img/emptyStar.png";

    public FavoriteButton(int x, int y, int dimension) {
        super();
        Logger.vvlog("Creating FavoriteButton at (" + x + ", " + y + ")");
        relocate(x, y);
        setPrefSize(dimension, dimension);

        setStyle(" -fx-border-color: transparent;" +
                " -fx-background-size: 30;");

        //setImage(false);
    }

    /**
     * Sets the image if the music is on or is off.
     * @param favoriteOn current behavior of the music
     */
    public void setImage(boolean favoriteOn) {

        Logger.vvlog("Setting favoriteStar image");
        if (favoriteOn) {
            setStyle("-fx-background-image: url(" + imgFavoriteOffLocation + ")");
        }
        else {
            setStyle("-fx-background-image: url(" + imgFavoriteOnLocation + ")");
        }
    }
}
