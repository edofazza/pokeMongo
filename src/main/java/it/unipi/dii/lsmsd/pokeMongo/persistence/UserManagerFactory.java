package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.config.*;

public class UserManagerFactory {
    public static UserManager buildManager(){
        String technology = getConfiguration();
        switch (technology){
            case "MongoDb":
                return new UserManagerOnMongoDb();
            default:
                try{
                    throw new IllegalArgumentException();
                }catch (IllegalArgumentException iae){
                    iae.printStackTrace();
                    //log error
                };
                return null;
        }
    }

    public static String getConfiguration(){
        return ConfigDataHandler.getInstance().configData.userDbArchitecture;
    }
}