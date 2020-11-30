package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox.CountryComboBox;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.InvalidFormEntryLabel;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerOnMongoDb;
import it.unipi.dii.lsmsd.pokeMongo.security.PasswordEncryptor;
import it.unipi.dii.lsmsd.pokeMongo.utils.FormValidatorPokeMongo;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * This class is used to display the <code>Node</code> concerning the Settings.
 */
public class Settings extends PokeSceneWithHeaderAndBackButton {
    // LEFT
    private TextField newEmailTF;
    private TextField oldPasswordTF;
    private TextField confirmPasswordTF;

    private InvalidFormEntryLabel invalidEmailLabel;
    private InvalidFormEntryLabel invalidConfirmPasswordLabel;

    // RIGHT
    private TextField confirmEmailTF;
    private TextField newPasswordTF;
    private CountryComboBox countryCB;

    private InvalidFormEntryLabel invalidConfirmEmailLabel;
    private InvalidFormEntryLabel invalidPasswordLabel;

    // RESULT OF THE UPDATE LABEL
    private InvalidFormEntryLabel resultUpdateLabel;
    /**
     * Calls series of functions in order to display all the fields. It sets
     * also the music
     */
    public Settings() {
        Logger.log("SHOWING SETTINGS PAGE");

        displayNewEmail();
        displayConfirmEmail();
        displayOldPassword();
        displayNewPassword();
        displayConfirmPassword();
        displayCountry();

        displayConfirmButton();
        displayResultLabel();

        setSceneMusic("gym.mp3");
    }

    // LEFT SIDE

    /**
     * Adds to the scene the <code>Node</code> concerning the new email
     */
    private void displayNewEmail() {
        FieldRelatedLabel emailLabel = new FieldRelatedLabel("New Email", 350, 170);

        invalidEmailLabel = new InvalidFormEntryLabel("Email must follow the format:\nexample@domain.tld", 510, 190, false);

        newEmailTF = new TextField();
        newEmailTF.relocate(350, 200);
        newEmailTF.setOnKeyReleased(e->{
            FormValidatorPokeMongo.handleEmail(newEmailTF, invalidEmailLabel);
            FormValidatorPokeMongo.handleConfirmField(newEmailTF, confirmEmailTF, invalidConfirmEmailLabel);
        });

        sceneNodes.getChildren().addAll(emailLabel, invalidEmailLabel, newEmailTF);
    }

    /**
     * Adds to the scene the <code>Node</code> concerning the old password
     */
    private void displayOldPassword() {
        FieldRelatedLabel oldPasswordLabel = new FieldRelatedLabel("Old Password*", 350, 270);

        //TODO: query to db to find if the password matches with the old one

        oldPasswordTF = new PasswordField();
        oldPasswordTF.relocate(350, 300);

        sceneNodes.getChildren().addAll(oldPasswordLabel, oldPasswordTF);
    }

    /**
     * Adds to the scene the <code>Node</code> in order to confirm the password
     */
    private void displayConfirmPassword() {
        FieldRelatedLabel confirmPasswordLabel = new FieldRelatedLabel("Confirm Password", 350, 370);

        invalidConfirmPasswordLabel = new InvalidFormEntryLabel("Does not match with the other password", 510, 400, false);

        confirmPasswordTF = new PasswordField();
        confirmPasswordTF.relocate(350, 400);
        confirmPasswordTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleConfirmField(newPasswordTF, confirmPasswordTF, invalidConfirmPasswordLabel));

        sceneNodes.getChildren().addAll(confirmPasswordLabel, invalidConfirmPasswordLabel, confirmPasswordTF);
    }

    /**
     * Adds to the scene the <code>Node</code> in order to confirm the email
     */
    // RIGHT SIDE
    private void displayConfirmEmail() {
        FieldRelatedLabel confirmEmailLabel = new FieldRelatedLabel("Confirm Email", 750, 170);

        invalidConfirmEmailLabel = new InvalidFormEntryLabel("Does not match with the other email", 910, 200, false);

        confirmEmailTF = new TextField();
        confirmEmailTF.relocate(750, 200);
        confirmEmailTF.setOnKeyReleased(e->FormValidatorPokeMongo.handleConfirmField(newEmailTF, confirmEmailTF, invalidConfirmEmailLabel));

        sceneNodes.getChildren().addAll(confirmEmailLabel, invalidConfirmEmailLabel, confirmEmailTF);
    }

    /**
     * Adds to the scene the <code>Node</code> concerning the new password
     */
    private void displayNewPassword() {
        FieldRelatedLabel newPasswordLabel = new FieldRelatedLabel("New Password", 750, 270);

        invalidPasswordLabel = new InvalidFormEntryLabel("Password must contain at least:\n8 characters, a letter and\na number", 910, 285, false);

        newPasswordTF = new PasswordField();
        newPasswordTF.relocate(750, 300);
        newPasswordTF.setOnKeyReleased(e->{
            FormValidatorPokeMongo.handlePassword(newPasswordTF, invalidPasswordLabel);
            FormValidatorPokeMongo.handleConfirmField(newPasswordTF, confirmPasswordTF, invalidConfirmPasswordLabel);
        });

        sceneNodes.getChildren().addAll(newPasswordLabel, invalidPasswordLabel, newPasswordTF);
    }

    /**
     * Adds to the scene the <code>Node</code> concerning the country
     */
    private void displayCountry() {
        FieldRelatedLabel newPasswordLabel = new FieldRelatedLabel("Country", 750, 370);

        try {
            countryCB = new CountryComboBox(750, 400);
            sceneNodes.getChildren().add(countryCB);
        } catch (IOException e) { e.printStackTrace(); }

        sceneNodes.getChildren().addAll(newPasswordLabel);
    }

    // RESULT LABEL
    private void displayResultLabel() {
        resultUpdateLabel = new InvalidFormEntryLabel("pippo", 1100, 600, false);

        sceneNodes.getChildren().add(resultUpdateLabel);
    }


    /**
     * Adds to the scene the <code>RegularButton</code> for confirming the fields.
     */
    // BUTTONS
    private void displayConfirmButton() {
        RegularButton submitButton = new RegularButton("CONFIRM", 1000, 600);
        submitButton.setOnAction(e -> submitbuttonAction());

        sceneNodes.getChildren().add(submitButton);
    }

    private void submitbuttonAction() {
        resultUpdateLabel.setStyle("-fx-background-color: #FF211A;");

        boolean moreThanOne = false;

        if (oldPasswordTF.getText().equals("") || !PasswordEncryptor.encryptPassword(oldPasswordTF.getText()).equals(CurrentUI.getUser().getPassword())) {
            resultUpdateLabel.setText("To update insert\nold password");
            resultUpdateLabel.setVisible(true);
        } else {
            // Remove the visibility of the previous banner
            resultUpdateLabel.setVisible(false);

            // change email
            if(!newEmailTF.getText().equals("") && !confirmEmailTF.getText().equals("")) {
                if (!invalidEmailLabel.isVisible() && !invalidConfirmEmailLabel.isVisible()) {
                    UserManagerOnMongoDb userManagerOnMongoDb = new UserManagerOnMongoDb();
                    userManagerOnMongoDb.changeEmail(CurrentUI.getUser(), newEmailTF.getText());

                    // locally
                    CurrentUI.getUser().setEmail(newEmailTF.getText());

                    resultUpdateLabel.setText("Email updated");
                    resultUpdateLabel.setStyle("-fx-background-color: green;");
                    resultUpdateLabel.setVisible(true);

                    moreThanOne = true;
                } else {
                    resultUpdateLabel.setText("Emails fields not\nproperly inserted");
                    resultUpdateLabel.setVisible(true);
                }
            } else if (!newEmailTF.getText().equals("") || !confirmEmailTF.getText().equals("")) {
                resultUpdateLabel.setText("Emails fields not\nproperly inserted");
                resultUpdateLabel.setVisible(true);

                moreThanOne = true;
            }


            // CHANGE PASSWORD
            if(!newPasswordTF.getText().equals("") && !confirmPasswordTF.getText().equals("")) {
                if (!invalidPasswordLabel.isVisible() && !invalidConfirmPasswordLabel.isVisible()) {
                    UserManagerOnMongoDb userManagerOnMongoDb = new UserManagerOnMongoDb();
                    userManagerOnMongoDb.changePassword(CurrentUI.getUser(), newPasswordTF.getText());

                    // locally
                    CurrentUI.getUser().setPassword(PasswordEncryptor.encryptPassword(newPasswordTF.getText()));

                    if (moreThanOne)
                        resultUpdateLabel.setText( resultUpdateLabel.getText() + "\nPassword updated");
                    else
                        resultUpdateLabel.setText("Password updated");

                    resultUpdateLabel.setStyle("-fx-background-color: green;");
                    resultUpdateLabel.setVisible(true);

                    moreThanOne = true;
                } else {
                    if (moreThanOne)
                        resultUpdateLabel.setText(resultUpdateLabel.getText() + "\nPassword fields not\nproperly inserted");
                    else
                        resultUpdateLabel.setText("Password fields not\nproperly inserted");
                    resultUpdateLabel.setVisible(true);

                    moreThanOne = true;
                }
            } else if (!newPasswordTF.getText().equals("") || !confirmPasswordTF.getText().equals("")) {
                if (moreThanOne)
                    resultUpdateLabel.setText(resultUpdateLabel.getText() + "\nPassword fields not\nproperly inserted");
                else
                    resultUpdateLabel.setText("Password fields not\nproperly inserted");
                resultUpdateLabel.setVisible(true);

                moreThanOne = true;
            }

            // CHANGE COUNTRY
            if (!countryCB.getValue().toString().equals("")) {
                String newCountry = countryCB.getValue().toString();

                UserManagerOnMongoDb userManagerOnMongoDb = new UserManagerOnMongoDb();
                userManagerOnMongoDb.changeCountry(CurrentUI.getUser(), newCountry);

                //locally
                CurrentUI.getUser().setCountry(newCountry);

                resultUpdateLabel.setStyle("-fx-background-color: green;");
                if (moreThanOne)
                    resultUpdateLabel.setText(resultUpdateLabel.getText() + "\nCountry updated");
                else
                    resultUpdateLabel.setText("Country updated");
                resultUpdateLabel.setVisible(true);
            }
        }
    }


}
