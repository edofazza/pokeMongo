package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.imageviews;

import javafx.scene.image.ImageView;

public class BackgroundImage extends ImageView {
    private static final String imgLocation = "file:img/";

    public BackgroundImage(String url, int dimension, int x, int y) {
        super(imgLocation + url);
        setFitWidth(dimension);
        setFitHeight(dimension);
        relocate(x, y);
    }
}
