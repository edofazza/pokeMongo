package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.buttons.HomePageCentralButton;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

/**
 * <em>HomePage</em> is the principal scene showed to the user. It handles both the admin and normal user scene.
 */
public class HomePage extends PokeSceneWithHeaderAndAggregateBlastoiseCharizard {
    private int buttonPosition = 0;

    /**
     * Constructor. Set the <code>Node</code> in the scene checking if the user is a normal user or an admin.
     * It also sets the music.
     */
    public HomePage() {

        Logger.log("SHOWING HOMEPAGE");
        displayPokedexButton();

        if(!CurrentUI.isAdmin()) {
            displayTeamButton();
            displayCatchemAllButton();
            displayFriendsButton();
        }

        displayRankingButton();

        if(!CurrentUI.isAdmin())
            displaySettingsButton();
        else {
            displayAnalytics();
            displayAddRemovePokemonButton();
            displayRemoveUserScene();
        }

        displayLogOutButton();

        setSceneMusic("professor_oak_theme.mp3");
    }

    /**
     * Add to <code>sceneNodes</code> the button to view the Pokedex
     */
    private void displayPokedexButton() {
        HomePageCentralButton pokedexButton = new HomePageCentralButton("POKEDEX");
        pokedexButton.setOnAction(e -> pokedexButtonAction());

        sceneNodes.getChildren().add(pokedexButton);
    }

    /**
     * Add to <code>sceneNodes</code> the button to view the Team. Only standard user.
     */
    private void displayTeamButton() {
        HomePageCentralButton teamButton = new HomePageCentralButton("TEAM");
        teamButton.setOnAction(e -> teamButtonAction());

        sceneNodes.getChildren().add(teamButton);
    }

    /**
     * Add to <code>sceneNodes</code> the button to go to the CatchEmAll scene. Only standard user.
     */
    private void displayCatchemAllButton() {
        HomePageCentralButton catchemAllButton = new HomePageCentralButton("CATCH'EM ALL");
        catchemAllButton.setOnAction(e -> catchemAllButtonAction());

        sceneNodes.getChildren().add(catchemAllButton);
    }

    /**
     * Add to <code>sceneNodes</code> the button to go to the Friends scene. Only standard user.
     */
    private void displayFriendsButton() {
        HomePageCentralButton friendsButton = new HomePageCentralButton("FRIENDS");
        friendsButton.setOnAction(e -> friendsButtonAction());

        sceneNodes.getChildren().add(friendsButton);
    }

    /**
     * Add to <code>sceneNodes</code> the button to view to Ranking.
     */
    private void displayRankingButton() {
        HomePageCentralButton rankingButton = new HomePageCentralButton("RANKING");
        rankingButton.setOnAction(e -> rankingButtonAction());

        sceneNodes.getChildren().add(rankingButton);
    }

    /**
     * Add to <code>sceneNodes</code> the button to view to change the settings. Only standard user.
     */
    private void displaySettingsButton() {
        HomePageCentralButton settingButton = new HomePageCentralButton("SETTINGS");

        settingButton.setOnAction(e-> settingButtonAction());

        sceneNodes.getChildren().add(settingButton);
    }

    /**
     * Add to <code>sceneNodes</code> the button to view to AddRemove scene. Only admin user.
     */
    private void displayAddRemovePokemonButton() {
        HomePageCentralButton addRemoveButton = new HomePageCentralButton("ADD/REMOVE POKEMON");

        addRemoveButton.setOnAction(e-> addRemovePokemonAction());

        sceneNodes.getChildren().add(addRemoveButton);
    }

    /**
     * Add to <code>sceneNodes</code> the button to view to Analytics scene. Only admin user.
     */
    private void displayAnalytics() {
        HomePageCentralButton analyticsButton = new HomePageCentralButton("ANALYTICS");

        analyticsButton.setOnAction(e-> analyticsButtonAction());

        sceneNodes.getChildren().add(analyticsButton);
    }

    /**
     * Add to <code>sceneNodes</code> the button to view to Analytics scene. Only admin user.
     */
    private void displayRemoveUserScene() {
        HomePageCentralButton removeUserButton = new HomePageCentralButton("REMOVE USER");

        removeUserButton.setOnAction(e-> removeUserButtonAction());

        sceneNodes.getChildren().add(removeUserButton);
    }

    /**
     * Add to <code>sceneNodes</code> the button to log out from the account.
     */
    private void displayLogOutButton() {
        HomePageCentralButton logOutButton = new HomePageCentralButton("LOG OUT");

        logOutButton.setOnAction(e-> logOutButtonAction());

        sceneNodes.getChildren().add(logOutButton);
    }

    ///////////////// ACTIONS /////////////////
    /**
     * Action related to the logout button. It logs out the user and goes back to the LOGIN scene.
     */
    private void logOutButtonAction() {
        UserManager userManager = UserManagerFactory.buildManager();
        userManager.logout(CurrentUI.getUser());
        CurrentUI.userExit();
        CurrentUI.changeScene(SceneNames.LOGIN);
    }

    /**
     * Change the scene to Pokedex.
     */
    private void pokedexButtonAction() {
        CurrentUI.changeScene(SceneNames.POKEDEX);
    }

    /**
     * Change the scene to Team.
     */
    private void teamButtonAction() {
        CurrentUI.changeScene(SceneNames.TEAM);
    }

    /**
     * Change the scene to Friends
     */
    private void friendsButtonAction() {
        CurrentUI.changeScene(SceneNames.FRIENDS);
    }

    /**
     * Change the scene to Settings.
     */
    private void settingButtonAction() {
        CurrentUI.changeScene(SceneNames.SETTINGS);
    }

    /**
     * Change the scene to Ranking.
     */
    private void rankingButtonAction() {
        CurrentUI.changeScene(SceneNames.RANKING);
    }

    /**
     * Change the scene to CatchemAll.
     */
    private void catchemAllButtonAction() {
        CurrentUI.changeScene(SceneNames.CATCHEMALL);
    }

    /**
     * Change the scene to AddRemovePokemon.
     */
    private void addRemovePokemonAction() {
        CurrentUI.changeScene(SceneNames.ADD_REMOVE);
    }

    /**
     * Change the scene to AnalyticsScene.
     */
    private void analyticsButtonAction() {
        CurrentUI.changeScene(SceneNames.ANALYTICS);
    }

    /**
     * Change the scene to RemoveUserScene.
     */
    private void removeUserButtonAction() {
        CurrentUI.changeScene(SceneNames.REMOVEUSER);
    }
}
