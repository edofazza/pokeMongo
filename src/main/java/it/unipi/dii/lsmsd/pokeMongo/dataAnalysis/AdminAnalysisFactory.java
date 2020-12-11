package it.unipi.dii.lsmsd.pokeMongo.dataAnalysis;

import it.unipi.dii.lsmsd.pokeMongo.config.ConfigDataHandler;
import it.unipi.dii.lsmsd.pokeMongo.persistence.AdminAnalysisOnMongoDb;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerOnMongoDb;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

public class AdminAnalysisFactory {
    public static AdminAnalysis buildRanker(){
        String technology = getConfiguration();
        switch (technology){
            case "MongoDb":
                return new AdminAnalysisOnMongoDb(getDaysToVisualize());
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
        return ConfigDataHandler.getInstance().configData.analyticMemorySupport;
    }

    private static int getDaysToVisualize(){
        return ConfigDataHandler.getInstance().configData.daysToVisualize;
    }
}
