package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.utils.FormValidatorPokeMongo;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.regex.*;

public class SignUp extends PokeSceneWithTitle {
    // LEFT SIDE
    private TextField surnameTF;
    private TextField nicknameTF;
    private TextField passwordTF;
    private DatePicker birthdayDP;

    private Label invalidSurnameLabel;
    private Label invalidPasswordLabel;
    private Label invalidBirthdayLabel;

    // RIGHT SIDE
    private TextField nameTF;
    private TextField emailTF;
    private TextField confirmPasswordTF;
    private TextField countryTF;

    private Label invalidNameLabel;
    private Label invalidEmailLabel;
    private Label invalidConfirmPasswordLabel;


    private final String styleInvalidFormEntry = "-fx-font-family: 'Arial'; -fx-font-size: 12px; " +
            "-fx-background-color: rgb(255, 33, 26); -fx-text-fill: white; -fx-max-width: 230; -fx-padding: 3;" +
            " -fx-border-radius: 15px; -fx-background-radius: 15px; -fx-border-width: 0.06em;" +
            " -fx-border-color: black;";

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
        FieldRelatedLabel surnameLabel = new FieldRelatedLabel("Surname", 350, 170);

        invalidSurnameLabel = new Label("Surname must only contain letters,\nspaces, dots and apostrophes");
        invalidSurnameLabel.relocate(510, 190);
        invalidSurnameLabel.setVisible(false);
        invalidSurnameLabel.setStyle(styleInvalidFormEntry);

        surnameTF = new TextField();
        surnameTF.relocate(350, 200);
        surnameTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleName(surnameTF, invalidSurnameLabel));

        sceneNodes.getChildren().add(surnameLabel);
        sceneNodes.getChildren().add(invalidSurnameLabel);
        sceneNodes.getChildren().add(surnameTF);
    }

    private void displayNicknameFields() {
        FieldRelatedLabel nicknameLabel = new FieldRelatedLabel("Nickname", 350, 270);

        nicknameTF = new TextField();
        nicknameTF.relocate(350, 300);

        sceneNodes.getChildren().add(nicknameLabel);
        sceneNodes.getChildren().add(nicknameTF);
    }

    private void displayPasswordFields() {
        FieldRelatedLabel passwordLabel = new FieldRelatedLabel("Password", 350, 370);

        invalidPasswordLabel = new Label("Password must contain at least:\n8 characters, a letter and\na number");
        invalidPasswordLabel.relocate(510, 380);
        invalidPasswordLabel.setVisible(false);
        invalidPasswordLabel.setStyle(styleInvalidFormEntry);

        passwordTF = new PasswordField();
        passwordTF.relocate(350, 400);
        passwordTF.setOnKeyReleased(e->FormValidatorPokeMongo.handlePassword(passwordTF, invalidPasswordLabel));

        sceneNodes.getChildren().add(passwordLabel);
        sceneNodes.getChildren().add(invalidPasswordLabel);
        sceneNodes.getChildren().add(passwordTF);
    }

    private void displayBirthdayFields() {
        FieldRelatedLabel passwordLabel = new FieldRelatedLabel("Birthday", 350, 470);

        invalidBirthdayLabel = new Label("Birthday must not be\na day in the future");
        invalidBirthdayLabel.relocate(540, 490);
        invalidBirthdayLabel.setVisible(false);
        invalidBirthdayLabel.setStyle(styleInvalidFormEntry);

        birthdayDP = new DatePicker();
        birthdayDP.relocate(350, 500);
        birthdayDP.setOnAction(e->FormValidatorPokeMongo.handleBirthday(birthdayDP, invalidBirthdayLabel));

        sceneNodes.getChildren().add(passwordLabel);
        sceneNodes.getChildren().add(invalidBirthdayLabel);
        sceneNodes.getChildren().add(birthdayDP);
    }

    // RIGHT SIDE
    private void displayNameFields() {
        FieldRelatedLabel nameLabel = new FieldRelatedLabel("Name", 750, 170);

        invalidNameLabel = new Label("Name must only contain letters,\nspaces, dots and apostrophes");
        invalidNameLabel.relocate(910, 190);
        invalidNameLabel.setVisible(false);
        invalidNameLabel.setStyle(styleInvalidFormEntry);

        nameTF = new TextField();
        nameTF.relocate(750, 200);
        nameTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleName(nameTF, invalidNameLabel)); //TODO: maybe setOnKeyReleased event trigger it's not the best choice

        sceneNodes.getChildren().add(nameLabel);
        sceneNodes.getChildren().add(invalidNameLabel);
        sceneNodes.getChildren().add(nameTF);
    }

    private void displayEmailFields() {
        FieldRelatedLabel emailLabel = new FieldRelatedLabel("Email", 750, 270);

        invalidEmailLabel = new Label("Email must follow the format:\nexample@domain.tld");
        invalidEmailLabel.relocate(910, 290);
        invalidEmailLabel.setVisible(false);
        invalidEmailLabel.setStyle(styleInvalidFormEntry);

        emailTF = new TextField();
        emailTF.relocate(750, 300);
        emailTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleEmail(emailTF, invalidEmailLabel));

        sceneNodes.getChildren().add(emailLabel);
        sceneNodes.getChildren().add(invalidEmailLabel);
        sceneNodes.getChildren().add(emailTF);
    }

    private void displayConfirmPassword() {
        FieldRelatedLabel confirmPasswordLabel = new FieldRelatedLabel("Confirm Password", 750, 370);

        invalidConfirmPasswordLabel = new Label("Does not match with the other password");
        invalidConfirmPasswordLabel.relocate(910, 400);
        invalidConfirmPasswordLabel.setVisible(false);
        invalidConfirmPasswordLabel.setStyle(styleInvalidFormEntry);

        confirmPasswordTF = new PasswordField();
        confirmPasswordTF.relocate(750, 400);
        confirmPasswordTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleConfirmField(passwordTF, confirmPasswordTF, invalidConfirmPasswordLabel));

        sceneNodes.getChildren().add(confirmPasswordLabel);
        sceneNodes.getChildren().add(invalidConfirmPasswordLabel);
        sceneNodes.getChildren().add(confirmPasswordTF);
    }

    private void displayCountryFields() {
        FieldRelatedLabel countryLabel = new FieldRelatedLabel("Country", 750, 470);

        countryTF = new TextField();
        countryTF.relocate(750, 500);

        sceneNodes.getChildren().add(countryLabel);
        sceneNodes.getChildren().add(countryTF);
    }

    // BUTTONS
    private void displayBackButton() {
        RegularButton backButton = new RegularButton("BACK", 200, 600);

        backButton.setOnAction((ActionEvent ev)-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.LOGIN);
    }

    private void displaySubmitButton() {
        RegularButton submitButton = new RegularButton("SUBMIT", 1000, 600);

        sceneNodes.getChildren().add(submitButton);
    }


    private void handleSubmit(){
        //TODO: query al database necessaria
    }
}