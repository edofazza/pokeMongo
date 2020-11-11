package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.SubmissionButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class LogIn extends PokeSceneWithBlastoiseCharizard {
    public LogIn() {
        displayEmailFields();
        displayPasswordFields();

        displayLogInButton();
        displaySignUpButton();

        setSceneMusic("opening.mp3");
    }

    ////////////////////////  FIELDS  ////////////////////////
    private void displayEmailFields() {
        FieldRelatedLabel emailLabel = new FieldRelatedLabel("Email", 550, 170);

        TextField emailTF = new TextField();
        emailTF.relocate(550, 200);

        sceneNodes.getChildren().add(emailLabel);
        sceneNodes.getChildren().add(emailTF);
    }

    private void displayPasswordFields() {
        FieldRelatedLabel passwordLabel = new FieldRelatedLabel("Password", 550, 270);

        TextField passwordTF = new TextField();
        passwordTF.relocate(550, 300);

        sceneNodes.getChildren().add(passwordLabel);
        sceneNodes.getChildren().add(passwordTF);
    }

    ////////////////////////  BUTTONS  ////////////////////////
    private void displayLogInButton() {
        SubmissionButton logInButton = new SubmissionButton("LOG IN", 700, 370);

        logInButton.setOnAction((ActionEvent ev)-> logInButtonAction());

        sceneNodes.getChildren().add(logInButton);
    }

    private void logInButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }

    private void displaySignUpButton() {
        SubmissionButton signUpButton = new SubmissionButton("SIGN UP", 500, 370);

        signUpButton.setOnAction((ActionEvent ev)-> signUpButtonAction());

        sceneNodes.getChildren().add(signUpButton);
    }

    private void signUpButtonAction() {
        CurrentUI.changeScene(SceneNames.SIGNUP);
    }
}
