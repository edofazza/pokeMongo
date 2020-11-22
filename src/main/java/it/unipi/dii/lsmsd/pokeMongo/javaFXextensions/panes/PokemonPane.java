package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.TrashCanButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class PokemonPane extends Pane {
    private TrashCanButton trashCanButton;
    private Circle pokemon;
    private Rectangle stats;

    public PokemonPane(int x, int y) {
        relocate(x, y);
        setPrefSize(400, 150);

        // TODO: gestire l'inserimento dell'evento in modo migliore
        trashCanButton = new TrashCanButton(350, 50);
        trashCanButton.setOnAction(e -> setToDefault());

        setToDefault();
    }

    private void addToPane() {
        getChildren().addAll(trashCanButton, pokemon, stats);
    }

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
