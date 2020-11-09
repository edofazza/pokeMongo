package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignIn extends PokeSceneWithTitle {
    public SignIn() {
        displayTitle();
        displaySurnameFields();
        displayNameFields();
        displayNicknameFields();
        displayEmailFields();
        displayPasswordFields();
        displayConfirmPassword();
        displayBirthdayFields();
        displayCountryFields();
        setSceneMusic("pallet_town.mp3");
    }

    // LEFT SIDE
    private void displaySurnameFields() {
        Label surnameLabel = new Label("Surname");
        surnameLabel.relocate(350, 170);
        surnameLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField surnameTF = new TextField();
        surnameTF.relocate(350, 200);

        sceneNodes.getChildren().add(surnameLabel);
        sceneNodes.getChildren().add(surnameTF);
    }

    private void displayNicknameFields() {
        Label nicknameLabel = new Label("NickName");
        nicknameLabel.relocate(350, 270);
        nicknameLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField nicknameTF = new TextField();
        nicknameTF.relocate(350, 300);

        sceneNodes.getChildren().add(nicknameLabel);
        sceneNodes.getChildren().add(nicknameTF);
    }

    private void displayPasswordFields() {
        Label passwordLabel = new Label("Password");
        passwordLabel.relocate(350, 370);
        passwordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField passwordTF = new TextField();
        passwordTF.relocate(350, 400);

        sceneNodes.getChildren().add(passwordLabel);
        sceneNodes.getChildren().add(passwordTF);
    }

    private void displayBirthdayFields() {
        Label passwordLabel = new Label("Birthday");
        passwordLabel.relocate(350, 470);
        passwordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        DatePicker datePicker = new DatePicker();
        datePicker.relocate(350, 500);

        sceneNodes.getChildren().add(passwordLabel);
        sceneNodes.getChildren().add(datePicker);
    }

    // RIGHT SIDE
    private void displayNameFields() {
        Label nameLabel = new Label("Name");
        nameLabel.relocate(750, 170);
        nameLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField nameTF = new TextField();
        nameTF.relocate(750, 200);

        sceneNodes.getChildren().add(nameLabel);
        sceneNodes.getChildren().add(nameTF);
    }

    private void displayEmailFields() {
        Label emailLabel = new Label("Name");
        emailLabel.relocate(750, 270);
        emailLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField emailTF = new TextField();
        emailTF.relocate(750, 300);

        sceneNodes.getChildren().add(emailLabel);
        sceneNodes.getChildren().add(emailTF);
    }

    private void displayConfirmPassword() {
        Label confirmPasswordLabel = new Label("Confirm Password");
        confirmPasswordLabel.relocate(750, 370);
        confirmPasswordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField confirmPasswordTF = new TextField();
        confirmPasswordTF.relocate(750, 400);

        sceneNodes.getChildren().add(confirmPasswordLabel);
        sceneNodes.getChildren().add(confirmPasswordTF);
    }

    private void displayCountryFields() {
        Label countryLabel = new Label("Country");
        countryLabel.relocate(750, 470);
        countryLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField countryTF = new TextField();
        countryTF.relocate(750, 500);

        sceneNodes.getChildren().add(countryLabel);
        sceneNodes.getChildren().add(countryTF);
    }
}