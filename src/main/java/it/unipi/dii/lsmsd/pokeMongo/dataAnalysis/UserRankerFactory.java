package it.unipi.dii.lsmsd.pokeMongo.dataAnalysis;

import it.unipi.dii.lsmsd.pokeMongo.config.*;
import it.unipi.dii.lsmsd.pokeMongo.persistence.*;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

public class UserRankerFactory {
    public static UserRanker buildRanker(){
        String technology = getConfiguration();
        switch (technology){
            case "MongoDb":
                return new UserManagerOnMongoDb();
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

    private static String getConfiguration(){
        return ConfigDataHandler.getInstance().configData.userDbArchitecture;
    }
}
