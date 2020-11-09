package it.unipi.dii.lsmsd.pokeMongo.userInterface;

public enum SceneNames {
    LOGIN, SIGNUP, HOMEPAGE;

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
