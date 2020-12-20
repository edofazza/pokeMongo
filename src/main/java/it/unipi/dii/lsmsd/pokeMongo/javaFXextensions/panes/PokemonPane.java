package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.TrashCanButton;
import it.unipi.dii.lsmsd.pokeMongo.userInterface.CurrentUI;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;


/**
 * Specific Pane for showing the pokemon in the team scene
 */
public class PokemonPane extends Pane {
    private TrashCanButton trashCanButton;
    private Circle pokemonCircle;
    private Pane stats;

    private Pokemon pokemon;

    private boolean changed;
    /**
     * Relocates the pane and set the default elements.
     * @param x the x axis position
     * @param y the x axis position
     *
     */
    public PokemonPane(int x, int y, Pokemon p) {
        relocate(x, y);
        setPrefSize(400, 150);

        this.pokemon = p;

        trashCanButton = new TrashCanButton(350, 50);
        trashCanButton.setOnAction(e -> remove());

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

        stats = new Pane();
        stats.setPrefSize(180, 50);
        stats.setStyle("-fx-border-color: black;");
        stats.relocate(150, 40);

        if(pokemon != null) {
            Label name = new Label(pokemon.getName() + "\tTYPES: " + pokemon.getTypeSingleString());
            name.setMaxSize(160, 10);
            name.setStyle("-fx-font-size: 10;");
            name.relocate(10, 17);
            stats.getChildren().add(name);
        }

        addToPane();
    }

    /**
     * Sets the pokemon as null and changed as true, then it calls the <code>setToDefault</code> method
     */
    private void remove() {
        pokemon = null;
        changed = true;

        setToDefault();
    }

    /**
     *
     * @return the value of <code>changed</code>
     */
    public boolean isChanged() {
        return changed;
    }
}
