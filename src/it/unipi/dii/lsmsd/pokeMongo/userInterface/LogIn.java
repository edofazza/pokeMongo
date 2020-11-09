package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LogIn extends PokeSceneWithBlastoiseCharizard {
    public LogIn() {
        displayTitle();
        displayEmailFields();
        displayPasswordFields();
        displayLogInButton();
        displaySignUpButton();

        setSceneMusic("opening.mp3");
    }

    private void displayTitle() {
        Label title = new Label("PokeMongo");
        title.relocate(475, 50);
        title.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 50px; -fx-font-weight: bold; -fx-text-fill: #a30014");

        logInNodes.getChildren().add(title);
    }

    private void displayEmailFields() {
        Label emailLabel = new Label("Email");
        emailLabel.relocate(550, 170);
        emailLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField emailTF = new TextField();
        emailTF.relocate(550, 200);

        logInNodes.getChildren().add(emailLabel);
        logInNodes.getChildren().add(emailTF);
    }

    private void displayPasswordFields() {
        Label passwordLabel = new Label("Password");
        passwordLabel.relocate(550, 270);
        passwordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField passwordTF = new TextField();
        passwordTF.relocate(550, 300);

        logInNodes.getChildren().add(passwordLabel);
        logInNodes.getChildren().add(passwordTF);
    }

    private void displayLogInButton() {
        Button logInButton = new Button("LOG IN");
        logInButton.relocate(700, 370);

        logInNodes.getChildren().add(logInButton);
    }

    private void displaySignUpButton() {
        Button signUpButton = new Button("SIGN UP");
        signUpButton.relocate(500, 370);

        logInNodes.getChildren().add(signUpButton);
    }
}
