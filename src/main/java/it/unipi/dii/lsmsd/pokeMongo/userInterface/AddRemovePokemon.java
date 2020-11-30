package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.ToggleGroupPane;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

/**
 * It initializes the scene, only visible to an admin user, for removing
 * and adding pokemon. It also sets the music.
 */
public class AddRemovePokemon extends PokeSceneWithHeaderAndBackButton {
    /**
     * <em>Constructor</em>. Call a function <code>displayAddRemovePokemon()</code>
     * in order to create all the <em>Node</em> needed. It also sets the music.
     */
    public AddRemovePokemon() {
        Logger.log("SHOWING ADD/REMOVE PAGE");
        displayAddRemovePokemon();

        setSceneMusic("catchemAll.mp3");
    }

    /**
     * Create a new <em>ToggleGroupPane</em> passing the information needed.<p>
     * <code>ToggleGroupPane addRemove = new ToggleGroupPane("Add Pokemon", "Remove Pokemon", 270, 100);</code><p>
     * Then add it to the <em>sceneNodes</em>.
     */
    private void displayAddRemovePokemon() {
        ToggleGroupPane addRemove = new ToggleGroupPane("Add Pokemon", "Remove Pokemon", 270, 100);

        sceneNodes.getChildren().add(addRemove);
    }
}
