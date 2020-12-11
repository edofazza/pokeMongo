package it.unipi.dii.lsmsd.pokeMongo.config;

public class ConfigData {
    public int numRowsRanking;
    public String usernameSpeedLogin;
    public String passwordSpeedLogin;
    public String userDbArchitecture;
    public String pokemonDbArchitecture;
    public String teamDbArchitecture;
    public String postDbArchitecture;
    public String networkDbArchitecture;
    public String localUriNeo4j;
    public String remoteUriNeo4j;

    public String userNeo4j;
    public String passwordNeo4j;

    /**
     *  0: no logs at all
     *  1: only log(), warn(), error() functions print something
     *  2: only log(), warn(), error(), vlog() functions print something
     *  3: only log(), warn(), error(), vlog(), vvlog() functions print something
     */

    public int verbosityLevel;
    ConfigData(int numrows, String usernameSpeedLogin, String passwordSpeedLogin,
               String userDbArchitecture, String pokemonDbArchitecture, String teamDbArchitecture, String postDbArchitecture, String networkDbArchitecture,
               int verbosityLevel, String localUri, String remoteUriNeo4j, String userNeo4j, String passwordNeo4j){
        this.numRowsRanking = numrows;
        this.usernameSpeedLogin = usernameSpeedLogin;
        this.passwordSpeedLogin = passwordSpeedLogin;
        this.userDbArchitecture = userDbArchitecture;
        this.pokemonDbArchitecture = pokemonDbArchitecture;
        this.postDbArchitecture = postDbArchitecture;
        this.networkDbArchitecture = networkDbArchitecture;
        this.teamDbArchitecture = teamDbArchitecture;
        this.verbosityLevel = verbosityLevel;
        this.localUriNeo4j = localUri;
        this.remoteUriNeo4j = remoteUriNeo4j;
        this.userNeo4j = userNeo4j;
        this.passwordNeo4j = passwordNeo4j;
    }

}
