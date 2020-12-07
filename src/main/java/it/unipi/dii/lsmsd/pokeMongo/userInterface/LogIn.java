package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.InvalidFormEntryLabel;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManagerOnNeo4j;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.security.PasswordEncryptor;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Class scene related to the Log In page
 */
public class LogIn extends PokeSceneWithBlastoiseCharizard {
    private TextField usernameTF;
    private PasswordField passwordTF;

    /**
     * Constructor. Called a series of functions to add all the <code>Node</code> needed.
     * It also sets the music.
     */
    public LogIn() {
        Logger.log("SHOWING LOGIN PAGE");
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
        FieldRelatedLabel usernameLabel = new FieldRelatedLabel("Username", 550, 170);

        usernameTF = new TextField();
        usernameTF.relocate(550, 200);

        sceneNodes.getChildren().addAll(usernameLabel, usernameTF);
    }

    /**
     * Display the Node related to the Password: the <code>FieldRelatedLabel</code> and the <code>FieldRelatedLabel</code>
     */
    private void displayPasswordFields() {
        FieldRelatedLabel passwordLabel = new FieldRelatedLabel("Password", 550, 270);

        passwordTF = new PasswordField();
        passwordTF.relocate(550, 300);

        sceneNodes.getChildren().addAll(passwordLabel, passwordTF);
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
        // Check the information given
        UserManager userManager = UserManagerFactory.buildManager();
        User user = userManager.login(usernameTF.getText(), passwordTF.getText());

        // set the user
        if(user != null) {
            CurrentUI.setUser(user);

            // retrieve the team, this auto compute the points
            CurrentUI.getUser().addTeam(retrieveTeam());

            // Update the point in mongodb
            userManager.updatePoints(user, user.getPoints());

            CurrentUI.changeScene(SceneNames.HOMEPAGE);
        } else {
            InvalidFormEntryLabel loginError = new InvalidFormEntryLabel("Username/password incorrect", 600, 400, true);
            sceneNodes.getChildren().add(loginError);
        }
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

    private Pokemon[] retrieveTeam() {
        TeamManagerOnNeo4j teamManagerOnNeo4j = new TeamManagerOnNeo4j();
        return teamManagerOnNeo4j.getUserTeam(CurrentUI.getUser());
    }
}
