package it.unipi.dii.lsmsd.pokeMongo.userInterface;

public enum SceneNames {
    LOGIN, SIGNUP;

    protected PokeScene createNewScene(SceneNames sn) {
        switch (sn) {
            case LOGIN:
                return new LogIn();
            case SIGNUP:
                return new SignUp();
            default:
                return null;
        }
    }
}
