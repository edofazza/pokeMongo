package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.imageviews;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.image.ImageView;

/**
 * This particular ImageVIew used to display resized Pokemon's image in the background
 */
public class BackgroundImage extends ImageView {
    private static final String imgLocation = "file:img/";

    /**
     * Instantiates a Button and fits the size
     * @param url location of the image
     * @param dimension value for the width and the height
     * @param x the x axis position
     * @param y the y axis position
     */
    public BackgroundImage(String url, int dimension, int x, int y) {
        super(imgLocation + url);
        Logger.vvlog("Loading BackgroundImage at '" + url + "'(" + x + ", " + y + ")");
        setFitWidth(dimension);
        setFitHeight(dimension);
        relocate(x, y);
    }
}
