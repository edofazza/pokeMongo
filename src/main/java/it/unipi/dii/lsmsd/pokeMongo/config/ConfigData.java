package it.unipi.dii.lsmsd.pokeMongo.config;

public class ConfigData {
    public int numrows;
    public String usernameSpeedLogin;
    public String passwordSpeedLogin;
    public String userDbArchitecture;
    public String pokemonDbArchitecture;
    public String teamDbArchitecture;
    /**
     *  0: no logs at all
     *  1: only log(), warn(), error() functions print something
     *  2: only log(), warn(), error(), vlog() functions print something
     *  3: only log(), warn(), error(), vlog(), vvlog() functions print something
     */

    public int verbosityLevel;
    ConfigData(int numrows, String usernameSpeedLogin, String passwordSpeedLogin, String userDbArchitecture,
               String pokemonDbArchitecture, String teamDbArchitecture, int verbosityLevel){
        this.numrows = numrows;
        this.usernameSpeedLogin = usernameSpeedLogin;
        this.passwordSpeedLogin = passwordSpeedLogin;
        this.userDbArchitecture = userDbArchitecture;
        this.pokemonDbArchitecture = pokemonDbArchitecture;
        this.teamDbArchitecture = teamDbArchitecture;
        this.verbosityLevel = verbosityLevel;
    }

}
