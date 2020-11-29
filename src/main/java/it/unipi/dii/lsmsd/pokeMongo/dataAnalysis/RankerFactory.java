package it.unipi.dii.lsmsd.pokeMongo.dataAnalysis;

import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerOnMongoDb;

import java.io.File;

public class RankerFactory {
    File configurationFile;
    public static UserRanker buildRanker(){
        String technology = getConfiguration();
        switch (technology){
            default:
                return new UserManagerOnMongoDb();
        }
    }

    public static String getConfiguration(){
        return "";
    }
}
