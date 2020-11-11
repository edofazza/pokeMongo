package it.unipi.dii.lsmsd.pokeMongo.userInterface;

public enum SceneNames {
    LOGIN, SIGNUP, HOMEPAGE, POKEDEX, CATCHEMALL, RANKING, SETTINGS, ADD_REMOVE;

    protected PokeScene createNewScene(SceneNames sn) {
        switch (sn) {
            case LOGIN:
                return new LogIn();
            case SIGNUP:
                return new SignUp();
            case HOMEPAGE:
                return new HomePage();
            default:
                return null;
        }
    }
}
