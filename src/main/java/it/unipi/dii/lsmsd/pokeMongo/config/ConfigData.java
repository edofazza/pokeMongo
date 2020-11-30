package it.unipi.dii.lsmsd.pokeMongo.config;

public class ConfigData {
    public int numrows;
    public String usernameSpeedLogin;
    public String passwordSpeedLogin;
    public String userDbArchitecture;
    public String pokemonDbArchitecture;
    public String teamDbArchitecture;

    ConfigData(int numrows, String usernameSpeedLogin, String passwordSpeedLogin, String userDbArchitecture,
               String pokemonDbArchitecture, String teamDbArchitecture){
        this.numrows = numrows;
        this.usernameSpeedLogin = usernameSpeedLogin;
        this.passwordSpeedLogin = passwordSpeedLogin;
        this.userDbArchitecture = userDbArchitecture;
        this.pokemonDbArchitecture = pokemonDbArchitecture;
        this.teamDbArchitecture = teamDbArchitecture;
    }

}
