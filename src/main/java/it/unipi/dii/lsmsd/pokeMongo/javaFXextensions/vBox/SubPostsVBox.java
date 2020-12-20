package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.vBox;

import javafx.scene.layout.VBox;

public class SubPostsVBox extends VBox {

    /**
     * Constructs a simple VBox with a spacing of 3
     * @param x the x axis position
     * @param y the y axis position
     */
    public SubPostsVBox(int x, int y) {
        relocate(x, y);
        setSpacing(3);
    }
}
