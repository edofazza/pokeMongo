package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.RegularButton;

/**
 * This class should be used to be extended by scenes that want the header containing the username
 *  and the number of pokeball hold (this one only for normal user), and the back button for coming
 *  back to the HomePage.
 */
public class PokeSceneWithHeaderAndBackButton extends PokeSceneWithHeader {
    /**
     * Calls the function <code>displayBackButton()</code>.
     */
    public PokeSceneWithHeaderAndBackButton() {
        displayBackButton();
    }

    /**
     * Create a <code>RegularButton</code> in order to instantiate the BACK button.
     */
    private void displayBackButton() {
        RegularButton backButton = new RegularButton("BACK", 200, 600);

        backButton.setOnAction(e-> backButtonAction());

        sceneNodes.getChildren().add(backButton);
    }

    /**
     * Change the scene going back to the HomePage.
     */
    private void backButtonAction() {
        CurrentUI.changeScene(SceneNames.HOMEPAGE);
    }
}
