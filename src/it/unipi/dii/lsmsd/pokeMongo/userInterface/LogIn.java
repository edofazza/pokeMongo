package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LogIn extends PokeSceneWithBlastoiseCharizard {
    public LogIn() {
        displayEmailFields();
        displayPasswordFields();
        displayLogInButton();
        displaySignUpButton();

        setSceneMusic("opening.mp3");
    }

    private void displayEmailFields() {
        Label emailLabel = new Label("Email");
        emailLabel.relocate(550, 170);
        emailLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField emailTF = new TextField();
        emailTF.relocate(550, 200);

        sceneNodes.getChildren().add(emailLabel);
        sceneNodes.getChildren().add(emailTF);
    }

    private void displayPasswordFields() {
        Label passwordLabel = new Label("Password");
        passwordLabel.relocate(550, 270);
        passwordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField passwordTF = new TextField();
        passwordTF.relocate(550, 300);

        sceneNodes.getChildren().add(passwordLabel);
        sceneNodes.getChildren().add(passwordTF);
    }

    private void displayLogInButton() {
        Button logInButton = new Button("LOG IN");
        logInButton.relocate(700, 370);

        logInButton.setOnAction((ActionEvent ev)-> logInButtonAction());

        sceneNodes.getChildren().add(logInButton);
    }

    private void logInButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }

    private void displaySignUpButton() {
        Button signUpButton = new Button("SIGN UP");
        signUpButton.relocate(500, 370);

        signUpButton.setOnAction((ActionEvent ev)-> signUpButtonAction());

        sceneNodes.getChildren().add(signUpButton);
    }

    private void signUpButtonAction() {
        CurrentUI.changeScene(SceneNames.SIGNUP);
    }
}
