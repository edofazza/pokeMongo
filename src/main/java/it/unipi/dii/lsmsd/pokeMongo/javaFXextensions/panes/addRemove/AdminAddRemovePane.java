package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.addRemove;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import javafx.scene.layout.Pane;

/*
    CANNOT CREATE AN INTERFACE BECAUSE PANE IS NOT ONE
 */

public class AdminAddRemovePane extends Pane {
    public AdminAddRemovePane(int x, int y) {
        relocate(x, y);
    }

    public void setNodes(AdminAction action) {
        getChildren().clear();

        switch (action) {
            case ADD:
                addActionNodes();
                break;
            case REMOVE:
                removeActionNodes();
                break;
        }
    }

    private void addActionNodes() {

    }

    private void removeActionNodes() {
        CatchEmAllTextField pokemonName = new CatchEmAllTextField("Pokemon name", 200, 50);

        RegularButton removeButton = new RegularButton("REMOVE", 270, 120);

        getChildren().addAll(pokemonName, removeButton);
    }
}
