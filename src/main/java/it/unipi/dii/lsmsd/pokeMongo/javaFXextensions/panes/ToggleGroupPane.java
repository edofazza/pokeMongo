package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.CircleButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.addRemove.AdminAction;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.addRemove.AdminAddRemovePane;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class ToggleGroupPane extends Pane {
    ToggleGroup toggleGroup;
    AdminAddRemovePane currentPane;
    CircleButton add;
    CircleButton remove;

    public ToggleGroupPane(String labelText, String labelText2, int x, int y) {
        currentPane = new AdminAddRemovePane(300, 200);

        toggleGroup = new ToggleGroup();

        add = new CircleButton(x, y);
        add.setToggleGroup(toggleGroup);

        add.setOnAction(e -> changePane(AdminAction.ADD));

        remove = new CircleButton(x+500, y);
        remove.setToggleGroup(toggleGroup);

        remove.setOnAction(e -> changePane(AdminAction.REMOVE));

        Label description = new Label(labelText);
        description.relocate(x+20, y);

        Label description2 = new Label(labelText2);
        description2.relocate(x+520, y);

        getChildren().addAll(add, remove, description, description2, currentPane);
    }

    private void changePane(AdminAction action) {
        currentPane.setNodes(action);
    }
}
