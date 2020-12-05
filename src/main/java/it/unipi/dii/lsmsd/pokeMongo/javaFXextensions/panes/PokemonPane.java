package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.TrashCanButton;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
<<<<<<< HEAD
import javafx.scene.control.Label;
import javafx.scene.image.Image;
=======
>>>>>>> parent of 87b48a6... Team fixed
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Specific Pane for showing the pokemon in the team scene
 */
public class PokemonPane extends Pane {
    private TrashCanButton trashCanButton;
    private Circle pokemonCircle;
    private Pane stats;

    private Pokemon pokemon;
    /**
     * Relocates the pane and set the default elements.
     * @param x the x axis position
     * @param y the x axis position
     *
     */
    // TODO: deve mettere il default solo se non è presente un pokemon per lo slot
    public PokemonPane(int x, int y) {
        relocate(x, y);
        setPrefSize(400, 150);

        //this.pokemon = p;

        // TODO: gestire l'inserimento dell'evento in modo migliore
        trashCanButton = new TrashCanButton(350, 50);
        trashCanButton.setOnAction(e -> setToDefault());

        setToDefault();
    }

    /**
     * Adds all the attributes to the pane.
     */
    private void addToPane() {
        getChildren().addAll(trashCanButton, pokemonCircle, stats);
    }

    /**
     * Clears the pane, reset the attributes to the default parameters,
     * then adds them to the pane again.
     */
    private void setToDefault() {
        getChildren().clear();

        pokemonCircle = new Circle(40);
        pokemonCircle.relocate(50, 20);
        pokemonCircle.setStyle("-fx-fill: white; -fx-stroke: black; -fx-pref-width: 80; -fx-pref-height: 80;");

        if (pokemon != null) {
            ImageView img = new ImageView();
            CurrentUI.getImage(pokemon.getSprite()).thenAccept(k -> img.setImage(k));
        }

        stats = new Pane();
        stats.setPrefSize(180, 50);
        stats.setStyle("-fx-border-color: black;");
        stats.relocate(150, 40);

        /*if(pokemon != null) {
            Label name = new Label(pokemon.getName() + "\tTYPES:" + pokemon.getTypeSingleString());
            name.relocate(10, 20);
            stats.getChildren().add(name);
        }*/

        addToPane();
    }
}
