package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.exceptions.DuplicateUserException;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox.CountryComboBox;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.InvalidFormEntryLabel;
import it.unipi.dii.lsmsd.pokeMongo.persistence.*;
import it.unipi.dii.lsmsd.pokeMongo.utils.FormValidatorPokeMongo;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * This class is used to display the <code>Node</code> concerning the Settings.
 */
public class SignUp extends PokeSceneWithTitle {
    // LEFT SIDE
    private TextField surnameTF;
    private TextField usernameTF;
    private TextField passwordTF;
    private DatePicker birthdayDP;

    private InvalidFormEntryLabel invalidSurnameLabel;
    private InvalidFormEntryLabel invalidPasswordLabel;
    private InvalidFormEntryLabel invalidBirthdayLabel;

    // RIGHT SIDE
    private TextField nameTF;
    private TextField emailTF;
    private TextField confirmPasswordTF;
    private CountryComboBox country;

    private InvalidFormEntryLabel invalidNameLabel;
    private InvalidFormEntryLabel invalidEmailLabel;
    private InvalidFormEntryLabel invalidConfirmPasswordLabel;

    /**
     * Calls a series of function in order to display all the fields. It sets
     * also the music.
     */
    public SignUp() {
        Logger.log("SHOWING SIGN-UP PAGE");

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

    /**
     * Adds to the scene the <code>Node</code> concerning the surname
     */
    // LEFT SIDE
    private void displaySurnameFields() {
        FieldRelatedLabel surnameLabel = new FieldRelatedLabel("Surname", 350, 170);

        invalidSurnameLabel = new InvalidFormEntryLabel("Surname must only contain letters,\nspaces, dots and apostrophes", 510, 190, false);

        surnameTF = new TextField();
        surnameTF.relocate(350, 200);
        surnameTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleName(surnameTF, invalidSurnameLabel));

        sceneNodes.getChildren().add(surnameLabel);
        sceneNodes.getChildren().add(invalidSurnameLabel);
        sceneNodes.getChildren().add(surnameTF);
    }

    /**
     * Adds to the scene the <code>Node</code> concerning the username
     */
    private void displayNicknameFields() {
        FieldRelatedLabel usernameLabel = new FieldRelatedLabel("Username", 350, 270);

        usernameTF = new TextField();
        usernameTF.relocate(350, 300);

        sceneNodes.getChildren().addAll(usernameLabel, usernameTF);
    }

    /**
     * Adds to the scene the <code>Node</code> concerning the password
     */
    private void displayPasswordFields() {
        FieldRelatedLabel passwordLabel = new FieldRelatedLabel("Password", 350, 370);

        invalidPasswordLabel = new InvalidFormEntryLabel("Password must contain at least:\n8 characters, a letter and\na number", 510, 380, false);

        passwordTF = new PasswordField();
        passwordTF.relocate(350, 400);
        passwordTF.setOnKeyReleased(e->{
            FormValidatorPokeMongo.handlePassword(passwordTF, invalidPasswordLabel);
            FormValidatorPokeMongo.handleConfirmField(passwordTF, confirmPasswordTF, invalidConfirmPasswordLabel);
        });

        sceneNodes.getChildren().addAll(passwordLabel, invalidPasswordLabel, passwordTF);
    }

    /**
     * Adds to the scene the <code>Node</code> concerning the birthday
     */
    private void displayBirthdayFields() {
        FieldRelatedLabel passwordLabel = new FieldRelatedLabel("Birthday", 350, 470);

        invalidBirthdayLabel = new InvalidFormEntryLabel("Birthday must not be\na day in the future", 540, 490, true);

        birthdayDP = new DatePicker();
        birthdayDP.relocate(350, 500);
        birthdayDP.setOnAction(e->FormValidatorPokeMongo.handleBirthday(birthdayDP, invalidBirthdayLabel));

        sceneNodes.getChildren().addAll(passwordLabel, invalidBirthdayLabel, birthdayDP);
    }

    /**
     * Adds to the scene the <code>Node</code> concerning the name
     */
    // RIGHT SIDE
    private void displayNameFields() {
        FieldRelatedLabel nameLabel = new FieldRelatedLabel("Name", 750, 170);

        invalidNameLabel = new InvalidFormEntryLabel("Name must only contain letters,\nspaces, dots and apostrophes", 910, 190, false);

        nameTF = new TextField();
        nameTF.relocate(750, 200);
        nameTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleName(nameTF, invalidNameLabel));

        sceneNodes.getChildren().addAll(nameLabel, invalidNameLabel, nameTF);
    }

    /**
     * Adds to the scene the <code>Node</code> concerning the email
     */
    private void displayEmailFields() {
        FieldRelatedLabel emailLabel = new FieldRelatedLabel("Email", 750, 270);

        invalidEmailLabel = new InvalidFormEntryLabel("Email must follow the format:\nexample@domain.tld", 910, 290, false);

        emailTF = new TextField();
        emailTF.relocate(750, 300);
        emailTF.setOnKeyReleased(e->{FormValidatorPokeMongo.handleEmail(emailTF, invalidEmailLabel); });

        sceneNodes.getChildren().addAll(emailLabel, invalidEmailLabel, emailTF);
    }

    /**
     * Adds to the scene the <code>Node</code> to confirm the password
     */
    private void displayConfirmPassword() {
        FieldRelatedLabel confirmPasswordLabel = new FieldRelatedLabel("Confirm Password", 750, 370);

        invalidConfirmPasswordLabel = new InvalidFormEntryLabel("Does not match with the other password", 910, 400, false);

        confirmPasswordTF = new PasswordField();
        confirmPasswordTF.relocate(750, 400);
        confirmPasswordTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleConfirmField(passwordTF, confirmPasswordTF, invalidConfirmPasswordLabel));

        sceneNodes.getChildren().addAll(confirmPasswordLabel, invalidConfirmPasswordLabel, confirmPasswordTF);
    }

    /**
     * Adds to the scene the <code>Node</code> concerning the country
     */
    private void displayCountryFields() {
        FieldRelatedLabel countryLabel = new FieldRelatedLabel("Country", 750, 470);
        try {
            country = new CountryComboBox(750, 500);
            sceneNodes.getChildren().add(country);
        } catch (IOException e) { e.printStackTrace(); }


        sceneNodes.getChildren().addAll(countryLabel);
    }

    /**
     * Adds to the scene the <code>RegularButton</code> for going back to the login in scene.
     */
    // BUTTONS
    private void displayBackButton() {
        RegularButton backButton = new RegularButton("BACK", 200, 600);

        backButton.setOnAction(e -> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    /**
     * Comes back to the login scene
     */
    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.LOGIN);
    }

    /**
     * Adds to the scene the <code>RegularButton</code> for submitting.
     */
    private void displaySubmitButton() {
        RegularButton submitButton = new RegularButton("SUBMIT", 1000, 600);
        submitButton.setOnAction(e -> handleSubmit());

        sceneNodes.getChildren().add(submitButton);
    }

    /**
     * Manages the information given by the user in order to create a new account.
     */
    private void handleSubmit(){
        // Check if the fields are the properly inserted (no label displayed and no empty fields)
        if (!invalidBirthdayLabel.isVisible() && !invalidConfirmPasswordLabel.isVisible() &&
                !invalidEmailLabel.isVisible() && !invalidNameLabel.isVisible() && !invalidPasswordLabel.isVisible() &&
                !invalidSurnameLabel.isVisible() && !surnameTF.getText().equals("") && !usernameTF.getText().equals("") &&
                !passwordTF.getText().equals("") && !nameTF.getText().equals("") && !emailTF.getText().equals("") &&
                !confirmPasswordTF.getText().equals("") && !country.getValue().toString().equals("")
        ) {
            InvalidFormEntryLabel resultLabel;
            // Get the Date
            LocalDate localDate = birthdayDP.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);

            User user = new User(
                    false,
                    surnameTF.getText(),
                    nameTF.getText(),
                    usernameTF.getText(),
                    passwordTF.getText(),
                    emailTF.getText(),
                    date,
                    country.getValue().toString());

            // Create a connection to MongoDB and insert the user
            UserManager userManager = UserManagerFactory.buildManager();
            if(userManager.register(user)) {
                resultLabel = new InvalidFormEntryLabel("Sign up successfully done", 800, 600, true);
                resultLabel.setStyle("-fx-background-color: green;");

                // ADD IT ALSO IN NEO4J
                UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
                try{
                    userNetworkManager.addUser(user);
                } catch(DuplicateUserException due){
                    System.out.println("Duplicate of user");
                }
            }
            else
                resultLabel = new InvalidFormEntryLabel("Username already exists", 800, 600, true);

            sceneNodes.getChildren().add(resultLabel);
        }

    }
}