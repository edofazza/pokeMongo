package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.InvalidFormEntryLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerOnMongoDb;

public class RemoveUserScene extends PokeSceneWithHeaderAndBackButton {
    private CatchEmAllTextField username;
    private InvalidFormEntryLabel operationResultLabel;

    public RemoveUserScene() {
        displayUsernameTextField();
        displayRemoveButton();
        addToBeDisplayedOperationResultButton();
    }

    private void displayUsernameTextField() {
        username = new CatchEmAllTextField("", 530, 180);
        username.setPromptText("username");

        sceneNodes.getChildren().add(username);
    }

    private void displayRemoveButton() {
        RegularButton removeButton = new RegularButton("REMOVE", 598, 260);
        removeButton.setOnAction(e -> removeButtonAction());

        sceneNodes.getChildren().add(removeButton);
    }

    private void addToBeDisplayedOperationResultButton() {
        operationResultLabel = new InvalidFormEntryLabel("", 588, 295, false);

        sceneNodes.getChildren().add(operationResultLabel);
    }

    private void removeButtonAction() {
        UserManagerOnMongoDb userManagerOnMongoDb = new UserManagerOnMongoDb();

        if (userManagerOnMongoDb.removeUser(username.getText())) {
            operationResultLabel.setText("user removed correctly");
            operationResultLabel.setStyle("-fx-background-color: green;");
        } else {
            operationResultLabel.setText("user didn't found");
            operationResultLabel.setStyle("-fx-background-color: #FF211A;");
        }
        operationResultLabel.setVisible(true);
    }
}
