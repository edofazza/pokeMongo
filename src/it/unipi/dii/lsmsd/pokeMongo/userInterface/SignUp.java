package it.unipi.dii.lsmsd.pokeMongo.userInterface;

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

    private void setCSS(Button b) { //TODO: remove it with a final string or css file
        b.setStyle("-fx-font-size: 15px; " +
                "-fx-font-family: 'Arial'; " +
                "-fx-font-weight: bold; " +
                "-fx-background-color: transparent; " +
                "-fx-border-color: #000000;");
    }

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

        invalidSurnameLabel = new Label("Surname must only contain letters,\nspaces, dots and apostrophes");
        invalidSurnameLabel.relocate(510, 190);
        invalidSurnameLabel.setVisible(false);
        invalidSurnameLabel.setStyle(styleInvalidFormEntry);

        surnameTF = new TextField();
        surnameTF.relocate(350, 200);
        surnameTF.setOnKeyReleased(e->handleSurname());

        sceneNodes.getChildren().add(surnameLabel);
        sceneNodes.getChildren().add(invalidSurnameLabel);
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

        invalidPasswordLabel = new Label("Password must contain at least:\n8 characters, a letter and\na number");
        invalidPasswordLabel.relocate(510, 380);
        invalidPasswordLabel.setVisible(false);
        invalidPasswordLabel.setStyle(styleInvalidFormEntry);

        passwordTF = new PasswordField();
        passwordTF.relocate(350, 400);
        passwordTF.setOnKeyReleased(e->handlePassword());

        sceneNodes.getChildren().add(passwordLabel);
        sceneNodes.getChildren().add(invalidPasswordLabel);
        sceneNodes.getChildren().add(passwordTF);
    }

    private void displayBirthdayFields() {
        Label passwordLabel = new Label("Birthday");
        passwordLabel.relocate(350, 470);
        passwordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        invalidBirthdayLabel = new Label("Birthday must not be\na day in the future");
        invalidBirthdayLabel.relocate(540, 490);
        invalidBirthdayLabel.setVisible(false);
        invalidBirthdayLabel.setStyle(styleInvalidFormEntry);

        birthdayDP = new DatePicker();
        birthdayDP.relocate(350, 500);
        birthdayDP.setOnAction(e->handleBirthday());


        sceneNodes.getChildren().add(passwordLabel);
        sceneNodes.getChildren().add(invalidBirthdayLabel);
        sceneNodes.getChildren().add(birthdayDP);
    }

    // RIGHT SIDE
    private void displayNameFields() {
        Label nameLabel = new Label("Name");
        nameLabel.relocate(750, 170);
        nameLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        invalidNameLabel = new Label("Name must only contain letters,\nspaces, dots and apostrophes");
        invalidNameLabel.relocate(910, 190);
        invalidNameLabel.setVisible(false);
        invalidNameLabel.setStyle(styleInvalidFormEntry);

        nameTF = new TextField();
        nameTF.relocate(750, 200);
        nameTF.setOnKeyReleased(e->handleName()); //TODO: maybe setOnKeyReleased event trigger it's not the best choice

        sceneNodes.getChildren().add(nameLabel);
        sceneNodes.getChildren().add(invalidNameLabel);
        sceneNodes.getChildren().add(nameTF);
    }

    private void displayEmailFields() {
        Label emailLabel = new Label("Email");
        emailLabel.relocate(750, 270);
        emailLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        invalidEmailLabel = new Label("Email must follow the format:\nexample@domain.tld");
        invalidEmailLabel.relocate(910, 290);
        invalidEmailLabel.setVisible(false);
        invalidEmailLabel.setStyle(styleInvalidFormEntry);

        emailTF = new TextField();
        emailTF.relocate(750, 300);
        emailTF.setOnKeyReleased(e->handleEmail());

        sceneNodes.getChildren().add(emailLabel);
        sceneNodes.getChildren().add(invalidEmailLabel);
        sceneNodes.getChildren().add(emailTF);
    }

    private void displayConfirmPassword() {
        Label confirmPasswordLabel = new Label("Confirm Password");
        confirmPasswordLabel.relocate(750, 370);
        confirmPasswordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        invalidConfirmPasswordLabel = new Label("Does not match with the other password");
        invalidConfirmPasswordLabel.relocate(910, 400);
        invalidConfirmPasswordLabel.setVisible(false);
        invalidConfirmPasswordLabel.setStyle(styleInvalidFormEntry);

        confirmPasswordTF = new PasswordField();
        confirmPasswordTF.relocate(750, 400);
        confirmPasswordTF.setOnKeyReleased(e->handleConfirmPassword());

        sceneNodes.getChildren().add(confirmPasswordLabel);
        sceneNodes.getChildren().add(invalidConfirmPasswordLabel);
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

        setCSS(backButton);

        backButton.setOnAction((ActionEvent ev)-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.LOGIN);
    }

    private void displaySubmitButton() {
        Button submitButton = new Button("SUBMIT");
        submitButton.relocate(1000, 600);

        setCSS(submitButton);

        sceneNodes.getChildren().add(submitButton);
    }

    // FORM CHECKING

    /**
     * In this section are present the event handler for the 'setOnKeyReleased' event in the form.
     */

    private void handleName(){
        if(isPersonNoun(nameTF.getText()))
            invalidNameLabel.setVisible(false);
        else
            invalidNameLabel.setVisible(true);
    }

    private void handleSurname(){
        if(isPersonNoun(surnameTF.getText()))
            invalidSurnameLabel.setVisible(false);
        else
            invalidSurnameLabel.setVisible(true);
    }

    /**
     * Check if the string contains only letters, spaces, dots and apostrophes.
     */
    private boolean isPersonNoun(String possibleNoun){
        Pattern pattern = Pattern.compile("^[a-zA-Z '.]*$");
        Matcher matcher = pattern.matcher(possibleNoun);
        return matcher.find();
    }

    private void handleEmail(){
        if(isValidEmail(emailTF.getText()))
            invalidEmailLabel.setVisible(false);
        else
            invalidEmailLabel.setVisible(true);
    }

    /**
     * Check if the email follows the format example@domain.tld
     */
    private boolean isValidEmail(String possibleEmail){
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(possibleEmail);
        return matcher.find();
    }


    private void handlePassword(){
        if(isValidPassword(passwordTF.getText()))
            invalidPasswordLabel.setVisible(false);
        else
            invalidPasswordLabel.setVisible(true);
    }

    private void handleConfirmPassword(){
        String password = passwordTF.getText(), confirmPassword = confirmPasswordTF.getText();

        if(password.equals(confirmPassword))
            invalidConfirmPasswordLabel.setVisible(false);
        else
            invalidConfirmPasswordLabel.setVisible(true);
    }

    /**
     * Checks if the password contains minimum eight characters, at least one letter and one number.
     */
    private boolean isValidPassword(String possiblePassword){
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher matcher = pattern.matcher(possiblePassword);
        return matcher.find();
    }


    /**
     * Checks if the birthday date selected is valid: future dates cannot be picked
     */
    private void handleBirthday(){
        LocalDate localDate = birthdayDP.getValue();
        LocalDate today = LocalDate.now();
        System.out.println(today);

        if(localDate.isAfter(today)){
            invalidBirthdayLabel.setVisible(true);
        } else {
            invalidBirthdayLabel.setVisible(false);
        }
    }



    private void handleSubmit(){
        //TODO: query al database necessaria
    }
}