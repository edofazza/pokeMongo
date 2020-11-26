package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.TrashCanButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Specific Pane for showing the pokemon in the team scene
 */
public class PokemonPane extends Pane {
    private TrashCanButton trashCanButton;
    private Circle pokemon;
    private Rectangle stats;

    /**
     * Relocates the pane and set the default elements.
     * @param x the x axis position
     * @param y the x axis position
     */
    // TODO: deve mettere il default solo se non è presente un pokemon per lo slot
    public PokemonPane(int x, int y) {
        relocate(x, y);
        setPrefSize(400, 150);

        // TODO: gestire l'inserimento dell'evento in modo migliore
        trashCanButton = new TrashCanButton(350, 50);
        trashCanButton.setOnAction(e -> setToDefault());

        setToDefault();
    }

    /**
     * Adds all the attributes to the pane.
     */
    private void addToPane() {
        getChildren().addAll(trashCanButton, pokemon, stats);
    }

    /**
     * Clears the pane, reset the attributes to the default parameters,
     * then adds them to the pane again.
     */
    private void setToDefault() {
        getChildren().clear();

        pokemon = new Circle(40);
        pokemon.relocate(50, 20);
        pokemon.setStyle("-fx-fill: white; -fx-stroke: black;");

        stats = new Rectangle(180, 50);
        stats.setStyle("-fx-fill: white; -fx-stroke: black;");
        stats.relocate(150, 40);

        addToPane();
    }
}
