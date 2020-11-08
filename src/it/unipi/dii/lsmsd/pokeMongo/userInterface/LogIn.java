package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LogIn implements PokeScene{
    private Node[] logInNodes;
    private final String imgLocation = "file:img/";

    public LogIn() {
        Label title = new Label("PokeMongo");
        title.relocate(475, 50);
        title.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 50px; -fx-font-weight: bold; -fx-text-fill: #a30014");

        Label emailLabel = new Label("Email");
        emailLabel.relocate(550, 170);
        emailLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField emailTF = new TextField();
        emailTF.relocate(550, 200);

        Label passwordLabel = new Label("Password");
        passwordLabel.relocate(550, 270);
        passwordLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20px; -fx-font-weight: bold;");

        TextField passwordTF = new TextField();
        passwordTF.relocate(550, 300);

        Button logInButton = new Button("LOG IN");
        logInButton.relocate(700, 370);

        Button signUpButton = new Button("SIGN UP");
        signUpButton.relocate(500, 370);

        ImageView blastoise = new ImageView(imgLocation + "blastoise.png");
        blastoise.setFitWidth(350);
        blastoise.setFitHeight(350);
        blastoise.relocate(80, 300);

        ImageView charizard = new ImageView(imgLocation + "charizard.png");
        charizard.setFitWidth(400);
        charizard.setFitHeight(400);
        charizard.relocate(800, 300);

        logInNodes = new Node[]{title, emailLabel, emailTF, passwordLabel, passwordTF, logInButton, signUpButton, blastoise, charizard};
    }

    public Node[] getNodes() {
        return logInNodes;
    }
}
