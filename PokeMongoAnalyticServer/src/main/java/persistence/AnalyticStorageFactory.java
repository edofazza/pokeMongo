package persistence;

import conf.ConfigDataHandler;

public class AnalyticStorageFactory {
    public static AnalyticStorage buildAnalyzer(){
        String technology = getTechnologyConfiguration();
        int daysToRemember = getDaysConfiguration();
        //Logger.vlog("Obtaining technology: " + technology);
        switch (technology){
            case "LevelDb":
                return AnalyticStorageOnLevelDb.getInstance(daysToRemember);
            case "InMemory":
                return AnalyticStorageOnLocalMemory.getInstance(daysToRemember);
            case "MongoDb":
                return new AnalyticStorageOnMongoDb();
            default:
                try{
                    throw new IllegalArgumentException();
                }catch (IllegalArgumentException iae){
                    iae.printStackTrace();
                    //Logger.error("Invalid database technology or missing implementation");
                };
                return null;
        }
    }

    private static String getTechnologyConfiguration(){
        return ConfigDataHandler.getInstance().configData.analyticMemorySupport;
    }

    private static int getDaysConfiguration() {
        return ConfigDataHandler.getInstance().configData.daysToRemember;
    }
}
