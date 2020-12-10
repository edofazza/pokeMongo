package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.panes.SubPostPane;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PostManagerFactory;
import javafx.scene.control.Button;

public class DeleteSubPostButton extends Button {
    private SubPostPane subPostPane;

    public DeleteSubPostButton(String text, int x, int y, SubPostPane subPostPane) {
        super(text);
        relocate(x, y);
        setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #f36666;");

        this.subPostPane = subPostPane;

        setOnAction(e -> deleteSubPost());
    }

    private void deleteSubPost() {
        PostManager postManager = PostManagerFactory.buildManager();
        postManager.deleteResponse(subPostPane.getPost());

        subPostPane.removeItself();
    }
}
