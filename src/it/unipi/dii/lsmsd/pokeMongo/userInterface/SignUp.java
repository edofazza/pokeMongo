package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUp extends PokeSceneWithTitle {
    // LEFT SIDE
    private TextField surnameTF;
    private TextField nicknameTF;
    private TextField passwordTF;
    private DatePicker birthdayDP;

    // RIGHT SIDE
    private TextField nameTF;
    private TextField emailTF;
    private TextField confirmPasswordTF;
    private TextField countryTF;

    public SignUp() {
        displaySurnameFields();
        displayNameFields();
        displayNicknameFields();
        displayEmailFields();
        displayPasswordFields();
        displayConfirmPassword();
        displayBirthdayFields();
        displayCountryFields();

        displayBackButton();
        displaySubmitButton();

        setSceneMusic("pallet_town.mp3");
    }

    // LEFT SIDE
    private void displaySurnameFields() {
        Label surnameLabel = new Label("Surname");
        surnameLabel.relocate(350, 170);
        surnameLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        surnameTF = new TextField();
        surnameTF.relocate(350, 200);

        sceneNodes.getChildren().add(surnameLabel);
        sceneNodes.getChildren().add(surnameTF);
    }

    private void displayNicknameFields() {
        Label nicknameLabel = new Label("NickName");
        nicknameLabel.relocate(350, 270);
        nicknameLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        nicknameTF = new TextField();
        nicknameTF.relocate(350, 300);

        sceneNodes.getChildren().add(nicknameLabel);
        sceneNodes.getChildren().add(nicknameTF);
    }

    private void displayPasswordFields() {
        Label passwordLabel = new Label("Password");
        passwordLabel.relocate(350, 370);
        passwordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        passwordTF = new TextField();
        passwordTF.relocate(350, 400);

        sceneNodes.getChildren().add(passwordLabel);
        sceneNodes.getChildren().add(passwordTF);
    }

    private void displayBirthdayFields() {
        Label passwordLabel = new Label("Birthday");
        passwordLabel.relocate(350, 470);
        passwordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        birthdayDP = new DatePicker();
        birthdayDP.relocate(350, 500);

        sceneNodes.getChildren().add(passwordLabel);
        sceneNodes.getChildren().add(birthdayDP);
    }

    // RIGHT SIDE
    private void displayNameFields() {
        Label nameLabel = new Label("Name");
        nameLabel.relocate(750, 170);
        nameLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        nameTF = new TextField();
        nameTF.relocate(750, 200);

        sceneNodes.getChildren().add(nameLabel);
        sceneNodes.getChildren().add(nameTF);
    }

    private void displayEmailFields() {
        Label emailLabel = new Label("Name");
        emailLabel.relocate(750, 270);
        emailLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        emailTF = new TextField();
        emailTF.relocate(750, 300);

        sceneNodes.getChildren().add(emailLabel);
        sceneNodes.getChildren().add(emailTF);
    }

    private void displayConfirmPassword() {
        Label confirmPasswordLabel = new Label("Confirm Password");
        confirmPasswordLabel.relocate(750, 370);
        confirmPasswordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        confirmPasswordTF = new TextField();
        confirmPasswordTF.relocate(750, 400);

        sceneNodes.getChildren().add(confirmPasswordLabel);
        sceneNodes.getChildren().add(confirmPasswordTF);
    }

    private void displayCountryFields() {
        Label countryLabel = new Label("Country");
        countryLabel.relocate(750, 470);
        countryLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        countryTF = new TextField();
        countryTF.relocate(750, 500);

        sceneNodes.getChildren().add(countryLabel);
        sceneNodes.getChildren().add(countryTF);
    }

    // BUTTONS
    private void displayBackButton() {
        Button backButton = new Button("BACK");
        backButton.relocate(200, 600);

        backButton.setOnAction((ActionEvent ev)->{ backButtonAction();});

        sceneNodes.getChildren().add(backButton);
    }

    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.LOGIN);
    }

    private void displaySubmitButton() {
        Button submitButton = new Button("SUBMIT");
        submitButton.relocate(1000, 600);

        sceneNodes.getChildren().add(submitButton);
    }
}