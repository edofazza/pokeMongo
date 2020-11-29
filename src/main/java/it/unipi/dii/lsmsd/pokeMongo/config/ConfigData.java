package it.unipi.dii.lsmsd.pokeMongo.config;

public class ConfigData {
    public int numrows;
    public String usernameSpeedLogin;
    public String passwordSpeedLogin;

    ConfigData(int numrows, String usernameSpeedLogin, String passwordSpeedLogin){
        this.numrows = numrows;
        this.usernameSpeedLogin = usernameSpeedLogin;
        this.passwordSpeedLogin = passwordSpeedLogin;
    }
}
