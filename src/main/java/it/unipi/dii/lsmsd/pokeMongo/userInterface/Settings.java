package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.utils.FormValidatorPokeMongo;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Settings extends PokeSceneWithHeader {
    // LEFT
    private TextField newEmailTF;
    private TextField oldPasswordTF;
    private TextField confirmPasswordTF;

    private Label invalidEmailLabel;
    private Label invalidConfirmPasswordLabel;

    // RIGHT
    private TextField confirmEmailTF;
    private TextField newPasswordTF;
    private TextField countryTF;

    private Label invalidConfirmEmailLabel;
    private Label invalidPasswordLabel;

    private final String styleInvalidFormEntry = "-fx-font-family: 'Arial'; -fx-font-size: 12px; " +
            "-fx-background-color: rgb(255, 33, 26); -fx-text-fill: white; -fx-max-width: 230; -fx-padding: 3;" +
            " -fx-border-radius: 15px; -fx-background-radius: 15px; -fx-border-width: 0.06em;" +
            " -fx-border-color: black;";

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

        invalidEmailLabel = new Label("Email must follow the format:\nexample@domain.tld");
        invalidEmailLabel.relocate(510, 190);
        invalidEmailLabel.setVisible(false);
        invalidEmailLabel.setStyle(styleInvalidFormEntry);

        newEmailTF = new TextField();
        newEmailTF.relocate(350, 200);
        newEmailTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleEmail(newEmailTF, invalidEmailLabel));

        sceneNodes.getChildren().add(emailLabel);
        sceneNodes.getChildren().add(invalidEmailLabel);
        sceneNodes.getChildren().add(newEmailTF);
    }

    private void displayOldPassword() {
        FieldRelatedLabel oldPasswordLabel = new FieldRelatedLabel("Old Password", 350, 270);

        //TODO: query to db to find if the password matches with the old one

        oldPasswordTF = new PasswordField();
        oldPasswordTF.relocate(350, 300);

        sceneNodes.getChildren().add(oldPasswordLabel);
        sceneNodes.getChildren().add(oldPasswordTF);
    }

    private void displayConfirmPassword() {
        FieldRelatedLabel confirmPasswordLabel = new FieldRelatedLabel("Confirm Password", 350, 370);

        invalidConfirmPasswordLabel = new Label("Does not match with the other password");
        invalidConfirmPasswordLabel.relocate(510, 400);
        invalidConfirmPasswordLabel.setVisible(false);
        invalidConfirmPasswordLabel.setStyle(styleInvalidFormEntry);

        confirmPasswordTF = new PasswordField();
        confirmPasswordTF.relocate(350, 400);
        confirmPasswordTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleConfirmField(newPasswordTF, confirmPasswordTF, invalidConfirmPasswordLabel));

        sceneNodes.getChildren().add(confirmPasswordLabel);
        sceneNodes.getChildren().add(invalidConfirmPasswordLabel);
        sceneNodes.getChildren().add(confirmPasswordTF);
    }

    // RIGHT SIDE
    private void displayConfirmEmail() {
        FieldRelatedLabel confirmEmailLabel = new FieldRelatedLabel("Confirm Email", 750, 170);

        invalidConfirmEmailLabel = new Label("Does not match with the other email");
        invalidConfirmEmailLabel.relocate(910, 200);
        invalidConfirmEmailLabel.setVisible(false);
        invalidConfirmEmailLabel.setStyle(styleInvalidFormEntry);

        confirmEmailTF = new TextField();
        confirmEmailTF.relocate(750, 200);
        confirmEmailTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleConfirmField(newEmailTF, confirmEmailTF, invalidConfirmEmailLabel));

        sceneNodes.getChildren().add(confirmEmailLabel);
        sceneNodes.getChildren().add(invalidConfirmEmailLabel);
        sceneNodes.getChildren().add(confirmEmailTF);
    }

    private void displayNewPassword() {
        FieldRelatedLabel newPasswordLabel = new FieldRelatedLabel("New Password", 750, 270);

        invalidPasswordLabel = new Label("Password must contain at least:\n8 characters, a letter and\na number");
        invalidPasswordLabel.relocate(910, 285);
        invalidPasswordLabel.setVisible(false);
        invalidPasswordLabel.setStyle(styleInvalidFormEntry);

        newPasswordTF = new PasswordField();
        newPasswordTF.relocate(750, 300);
        newPasswordTF.setOnKeyReleased(e->FormValidatorPokeMongo.handlePassword(newPasswordTF, invalidPasswordLabel));

        sceneNodes.getChildren().add(newPasswordLabel);
        sceneNodes.getChildren().add(invalidPasswordLabel);
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
        RegularButton backButton = new RegularButton("BACK", 200, 600);

        backButton.setOnAction((ActionEvent ev)-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }

    private void displayConfirmButton() {
        RegularButton submitButton = new RegularButton("CONFIRM", 1000, 600);

        sceneNodes.getChildren().add(submitButton);
    }
}
