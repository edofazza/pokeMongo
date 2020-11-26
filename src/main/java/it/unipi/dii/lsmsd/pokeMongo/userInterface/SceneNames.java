package it.unipi.dii.lsmsd.pokeMongo.userInterface;

/**
 * Contains all the names of the scenes.
 */
public enum SceneNames {
    LOGIN, SIGNUP, HOMEPAGE, POKEDEX, TEAM, CATCHEMALL, RANKING, SETTINGS, ADD_REMOVE;

    /**
     * This function handles the creation of the class scene asked.
     * @param sn is the SceneName we want to create.
     * @return The class scene asked.
     */
    protected PokeScene createNewScene(SceneNames sn) {
        switch (sn) {
            case LOGIN:
                return new LogIn();
            case SIGNUP:
                return new SignUp();
            case HOMEPAGE:
                return new HomePage();
            case TEAM:
                return new TeamScene();
            case SETTINGS:
                return new Settings();
            case CATCHEMALL:
                return new CatchEmAll();
            case ADD_REMOVE:
                return new AddRemovePokemon();
            case POKEDEX:
                return new Pokedex();
            case RANKING:
                return new RankingScene();
            default:
                return null;
        }
    }
}
