package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.SubmissionButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class Settings extends PokeSceneWithHeader {
    // LEFT
    private TextField newEmailTF;
    private TextField oldPasswordTF;
    private TextField confirmPasswordTF;

    // RIGHT
    private TextField confirmEmailTF;
    private TextField newPasswordTF;
    private TextField countryTF;

    public Settings() {
        displayNewEmail();
        displayConfirmEmail();
        displayOldPassword();
        displayNewPassword();
        displayConfirmPassword();
        displayCountry();

        displayBackButton();
        displayConfirmButton();

        setSceneMusic("gym.mp3");
    }

    // LEFT SIDE
    private void displayNewEmail() {
        FieldRelatedLabel emailLabel = new FieldRelatedLabel("New Email", 350, 170);

        newEmailTF = new TextField();
        newEmailTF.relocate(350, 200);

        sceneNodes.getChildren().add(emailLabel);
        sceneNodes.getChildren().add(newEmailTF);
    }

    private void displayOldPassword() {
        FieldRelatedLabel oldPasswordLabel = new FieldRelatedLabel("Old Password", 350, 270);

        oldPasswordTF = new TextField();
        oldPasswordTF.relocate(350, 300);

        sceneNodes.getChildren().add(oldPasswordLabel);
        sceneNodes.getChildren().add(oldPasswordTF);
    }

    private void displayConfirmPassword() {
        FieldRelatedLabel confirmPasswordLabel = new FieldRelatedLabel("Confirm Password", 350, 370);

        confirmPasswordTF = new TextField();
        confirmPasswordTF.relocate(350, 400);

        sceneNodes.getChildren().add(confirmPasswordLabel);
        sceneNodes.getChildren().add(confirmPasswordTF);
    }

    // RIGHT SIDE
    private void displayConfirmEmail() {
        FieldRelatedLabel confirmEmailLabel = new FieldRelatedLabel("Confirm Email", 750, 170);

        confirmEmailTF = new TextField();
        confirmEmailTF.relocate(750, 200);

        sceneNodes.getChildren().add(confirmEmailLabel);
        sceneNodes.getChildren().add(confirmEmailTF);
    }

    private void displayNewPassword() {
        FieldRelatedLabel newPasswordLabel = new FieldRelatedLabel("New Password", 750, 270);

        newPasswordTF = new TextField();
        newPasswordTF.relocate(750, 300);

        sceneNodes.getChildren().add(newPasswordLabel);
        sceneNodes.getChildren().add(newPasswordTF);
    }

    private void displayCountry() {
        FieldRelatedLabel newPasswordLabel = new FieldRelatedLabel("Country", 750, 370);

        countryTF = new TextField();
        countryTF.relocate(750, 400);

        sceneNodes.getChildren().add(newPasswordLabel);
        sceneNodes.getChildren().add(countryTF);
    }

    // BUTTONS
    private void displayBackButton() {
        SubmissionButton backButton = new SubmissionButton("BACK", 200, 600);

        backButton.setOnAction((ActionEvent ev)-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }

    private void displayConfirmButton() {
        SubmissionButton submitButton = new SubmissionButton("CONFIRM", 1000, 600);

        sceneNodes.getChildren().add(submitButton);
    }
}
