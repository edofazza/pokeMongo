package persistence;


import conf.ConfigDataHandler;

public class TeamManagerFactory {
    public static TeamManager buildManager(){
        String technology = getConfiguration();
        switch (technology){
            case "Neo4j":
                return new TeamManagerOnNeo4j();
            default:
                try{
                    throw new IllegalArgumentException();
                }catch (IllegalArgumentException iae){
                    iae.printStackTrace();
                };
                return null;
        }
    }

    public static String getConfiguration(){
        return ConfigDataHandler.getInstance().configData.teamDbArchitecture;
    }
}
