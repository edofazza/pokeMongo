package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.config.ConfigDataHandler;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

public class PostManagerFactory {
    public static PostManager buildManager(){
        String technology = getConfiguration();
        Logger.vlog("Obtaining technology: " + technology);
        switch (technology){
            case "Neo4j":
                return new PostManagerOnNeo4j();
            default:
                try{
                    throw new IllegalArgumentException();
                }catch (IllegalArgumentException iae){
                    iae.printStackTrace();
                    Logger.error("Invalid database technology or missing implementation");
                };
                return null;
        }
    }

    public static String getConfiguration(){
        return ConfigDataHandler.getInstance().configData.postDbArchitecture;
    }
}
