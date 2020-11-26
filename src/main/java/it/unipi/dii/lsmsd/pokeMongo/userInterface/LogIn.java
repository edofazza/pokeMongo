package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

/**
 * Class scene related to the Log In page
 */
public class LogIn extends PokeSceneWithBlastoiseCharizard {
    /**
     * Constructor. Called a series of functions to add all the <code>Node</code> needed.
     * It also sets the music.
     */
    public LogIn() {
        displayEmailFields();
        displayPasswordFields();

        displayLogInButton();
        displaySignUpButton();

        setSceneMusic("opening.mp3");
    }

    ////////////////////////  FIELDS  ////////////////////////

    /**
     * Display the Node related to the Email: the <code>FieldRelatedLabel</code> and the <code>FieldRelatedLabel</code>
     */
    private void displayEmailFields() {
        FieldRelatedLabel emailLabel = new FieldRelatedLabel("Email", 550, 170);

        TextField emailTF = new TextField();
        emailTF.relocate(550, 200);

        sceneNodes.getChildren().add(emailLabel);
        sceneNodes.getChildren().add(emailTF);
    }

    /**
     * Display the Node related to the Password: the <code>FieldRelatedLabel</code> and the <code>FieldRelatedLabel</code>
     */
    private void displayPasswordFields() {
        FieldRelatedLabel passwordLabel = new FieldRelatedLabel("Password", 550, 270);

        TextField passwordTF = new TextField();
        passwordTF.relocate(550, 300);

        sceneNodes.getChildren().add(passwordLabel);
        sceneNodes.getChildren().add(passwordTF);
    }

    ////////////////////////  BUTTONS  ////////////////////////

    /**
     * Add to the scene the <code>RegularButton</code> for the log in.
     */
    private void displayLogInButton() {
        RegularButton logInButton = new RegularButton("LOG IN", 700, 370);

        logInButton.setOnAction((ActionEvent ev)-> logInButtonAction());

        sceneNodes.getChildren().add(logInButton);
    }

    /**
     * Checks if the fields filled by the user are correct and if so, it goes to his homepage.
     */
    private void logInButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }

    /**
     * Add to the scene the <code>RegularButton</code> for the sign up.
     */
    private void displaySignUpButton() {
        RegularButton signUpButton = new RegularButton("SIGN UP", 500, 370);

        signUpButton.setOnAction((ActionEvent ev)-> signUpButtonAction());

        sceneNodes.getChildren().add(signUpButton);
    }

    /**
     * Change the scene to the SIGNUP scene.
     */
    private void signUpButtonAction() {
        CurrentUI.changeScene(SceneNames.SIGNUP);
    }
}
