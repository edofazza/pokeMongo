package conf;

public class ConfigData {
    public int numRowsRanking;
    public String usernameSpeedLogin;
    public String passwordSpeedLogin;
    public String userDbArchitecture;
    public String analyticMemorySupport;
    public int daysToRemember;

    /**
     *  0: no logs at all
     *  1: only log(), warn(), error() functions print something
     *  2: only log(), warn(), error(), vlog() functions print something
     *  3: only log(), warn(), error(), vlog(), vvlog() functions print something
     */

    public int verbosityLevel;
    ConfigData(int numrows, String usernameSpeedLogin, String passwordSpeedLogin,
               String userDbArchitecture, String analyticMemorySupport, int daysToRemember){
        this.numRowsRanking = numrows;
        this.usernameSpeedLogin = usernameSpeedLogin;
        this.passwordSpeedLogin = passwordSpeedLogin;
        this.userDbArchitecture = userDbArchitecture;
        this.analyticMemorySupport = analyticMemorySupport;
        this.daysToRemember = daysToRemember;
    }

}
