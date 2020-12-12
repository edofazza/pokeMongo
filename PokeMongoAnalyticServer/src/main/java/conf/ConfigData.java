package conf;

public class ConfigData {
    public int numRowsRanking;
    public String usernameSpeedLogin;
    public String passwordSpeedLogin;
    public String userDbArchitecture;
    public String analyticMemorySupport;
    public int daysToRemember;
    public String localUriNeo4j;
    public String remoteUriNeo4j;
    public String userNeo4j;
    public String passwordNeo4j;
    public String teamDbArchitecture;
    public String pokemonDbArchitecture;
    /**
     *  0: no logs at all
     *  1: only log(), warn(), error() functions print something
     *  2: only log(), warn(), error(), vlog() functions print something
     *  3: only log(), warn(), error(), vlog(), vvlog() functions print something
     */

    public int verbosityLevel;
    ConfigData(int numrows, String usernameSpeedLogin, String passwordSpeedLogin,
               String userDbArchitecture, String analyticMemorySupport, int daysToRemember, String localUriNeo4j,
               String remoteUriNeo4j, String userNeo4j, String passwordNeo4j, String teamDbArchitecture, String pokemonDbArchitecture){
        this.numRowsRanking = numrows;
        this.usernameSpeedLogin = usernameSpeedLogin;
        this.passwordSpeedLogin = passwordSpeedLogin;
        this.userDbArchitecture = userDbArchitecture;
        this.analyticMemorySupport = analyticMemorySupport;
        this.daysToRemember = daysToRemember;
        this.localUriNeo4j = localUriNeo4j;
        this.remoteUriNeo4j = remoteUriNeo4j;
        this.userNeo4j = userNeo4j;
        this.passwordNeo4j = passwordNeo4j;
        this.teamDbArchitecture = teamDbArchitecture;
        this.pokemonDbArchitecture = pokemonDbArchitecture;
    }

}
