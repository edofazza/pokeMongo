package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        //displayOldPassword();
        //displayNewPassword();
        displayConfirmPassword();
        //displayCountry();

        displayBackButton();
        displayConfirmButton();
    }

    // LEFT SIDE
    private void displayNewEmail() {
        Label emailLabel = new Label("New Email");
        emailLabel.relocate(350, 170);
        emailLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        newEmailTF = new TextField();
        newEmailTF.relocate(350, 200);

        sceneNodes.getChildren().add(emailLabel);
        sceneNodes.getChildren().add(newEmailTF);
    }

    private void displayConfirmEmail() {
        Label oldPasswordLabel = new Label("Old Password");
        oldPasswordLabel.relocate(350, 270);
        oldPasswordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        oldPasswordTF = new TextField();
        oldPasswordTF.relocate(350, 300);

        sceneNodes.getChildren().add(oldPasswordLabel);
        sceneNodes.getChildren().add(oldPasswordTF);
    }

    private void displayConfirmPassword() {
        Label ConfirmPasswordLabel = new Label("Confirm Password");
        ConfirmPasswordLabel.relocate(350, 370);
        ConfirmPasswordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        confirmPasswordTF = new TextField();
        confirmPasswordTF.relocate(350, 400);

        sceneNodes.getChildren().add(ConfirmPasswordLabel);
        sceneNodes.getChildren().add(confirmPasswordTF);
    }

    // BUTTONS
    private void setCSS(Button b) { //TODO: remove it with a final string or css file
        b.setStyle("-fx-font-size: 15px; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold; " +
                "-fx-background-color: transparent; " +
                "-fx-border-color: #000000;");
    }

    private void displayBackButton() {
        Button backButton = new Button("BACK");
        backButton.relocate(200, 600);

        setCSS(backButton);

        backButton.setOnAction((ActionEvent ev)-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }

    private void displayConfirmButton() {
        Button submitButton = new Button("CONFIRM");
        submitButton.relocate(1000, 600);

        setCSS(submitButton);

        sceneNodes.getChildren().add(submitButton);
    }
}
