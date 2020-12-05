package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.TrashCanButton;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Specific Pane for showing the pokemon in the team scene
 */
public class PokemonPane extends Pane {
    private TrashCanButton trashCanButton;
    private Circle pokemonCircle;
    private Rectangle stats;

    private Pokemon pokemon;
    /**
     * Relocates the pane and set the default elements.
     * @param x the x axis position
     * @param y the x axis position
     *
     */
    // TODO: deve mettere il default solo se non è presente un pokemon per lo slot
    public PokemonPane(int x, int y, Pokemon p) {
        relocate(x, y);
        setPrefSize(400, 150);

        this.pokemon = p;

        // TODO: gestire l'inserimento dell'evento in modo migliore
        trashCanButton = new TrashCanButton(350, 50);
        trashCanButton.setOnAction(e -> setToDefault());

        setToDefault();
    }

    /**
     * Adds all the attributes to the pane.
     */
    private void addToPane() {
        getChildren().addAll(trashCanButton, stats);
    }

    /**
     * Clears the pane, reset the attributes to the default parameters,
     * then adds them to the pane again.
     */
    private void setToDefault() {
        getChildren().clear();

        if (pokemon != null) {
            ImageView img = new ImageView();
            CurrentUI.getImage(pokemon.getSprite()).thenAccept(k -> img.setImage(k));
            img.setFitWidth(80);
            img.setFitHeight(80);
            img.relocate(50, 20);
            getChildren().add(img);
        } else {
            pokemonCircle = new Circle(40);
            pokemonCircle.relocate(50, 20);
            pokemonCircle.setStyle("-fx-fill: white; -fx-stroke: black;");
            getChildren().add(pokemonCircle);
        }

        stats = new Rectangle(180, 50);
        stats.setStyle("-fx-fill: white; -fx-stroke: black;");
        stats.relocate(150, 40);

        addToPane();
    }
}
