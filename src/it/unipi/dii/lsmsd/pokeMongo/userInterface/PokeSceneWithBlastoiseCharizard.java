package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.scene.image.ImageView;

public class PokeSceneWithBlastoiseCharizard extends PokeScene {
    private final String imgLocation = "file:img/";

    public PokeSceneWithBlastoiseCharizard() {
        ImageView blastoise = new ImageView(imgLocation + "blastoise.png");
        blastoise.setFitWidth(350);
        blastoise.setFitHeight(350);
        blastoise.relocate(80, 300);

        ImageView charizard = new ImageView(imgLocation + "charizard.png");
        charizard.setFitWidth(400);
        charizard.setFitHeight(400);
        charizard.relocate(800, 300);

        logInNodes.getChildren().add(blastoise);
        logInNodes.getChildren().add(charizard);
    }
}
