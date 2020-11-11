package it.unipi.dii.lsmsd.pokeMongo.userInterface;

public enum SceneNames {
    LOGIN, SIGNUP, HOMEPAGE, POKEDEX, TEAM, CATCHEMALL, RANKING, SETTINGS, ADD_REMOVE;

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
            default:
                return null;
        }
    }
}
