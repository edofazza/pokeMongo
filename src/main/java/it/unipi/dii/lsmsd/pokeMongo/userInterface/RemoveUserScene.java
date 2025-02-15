package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.InvalidFormEntryLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.textfields.CatchEmAllTextField;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

/**
 * This is allow available to the admin user
 */
public class RemoveUserScene extends PokeSceneWithHeaderAndBackButton {
    private CatchEmAllTextField username;
    private InvalidFormEntryLabel operationResultLabel;

    /**
     * Calls a series of function in order to display all the Nodes
     */
    public RemoveUserScene() {
        Logger.log("SHOWING REMOVE USER SCENE");

        displayUsernameTextField();
        displayRemoveButton();
        addToBeDisplayedOperationResultButton();
    }

    /**
     * Displays a textfield in which the admin user can type the name of a normal user
     */
    private void displayUsernameTextField() {
        username = new CatchEmAllTextField("", 530, 180);
        username.setPromptText("username");

        sceneNodes.getChildren().add(username);
    }

    /**
     * Displays a button saying "REMOVE"
     */
    private void displayRemoveButton() {
        RegularButton removeButton = new RegularButton("REMOVE", 598, 260);
        removeButton.setOnAction(e -> removeButtonAction());

        sceneNodes.getChildren().add(removeButton);
    }

    /**
     * Add an invisible label in which is written the result of the delete operation (after the operation is done the
     * <code>removeButtonAction</code> will set the visibility of this label as true
     */
    private void addToBeDisplayedOperationResultButton() {
        operationResultLabel = new InvalidFormEntryLabel("", 588, 295, false);

        sceneNodes.getChildren().add(operationResultLabel);
    }

    /**
     * Remove the user inserted and display the result of the operation.
     */
    private void removeButtonAction() {
        UserManager userManager = UserManagerFactory.buildManager();

        if (userManager.removeUser(username.getText())) {
            operationResultLabel.setText("user removed correctly");
            operationResultLabel.setStyle("-fx-background-color: green;");

            //REMOVE IT FROM NEO$J ALSO
            UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
            userNetworkManager.deleteUser(username.getText());
        } else {
            operationResultLabel.setText("user didn't found");
            operationResultLabel.setStyle("-fx-background-color: #FF211A;");
        }
        operationResultLabel.setVisible(true);
    }
}
