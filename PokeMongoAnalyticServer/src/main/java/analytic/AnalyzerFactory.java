package analytic;

import conf.ConfigDataHandler;

public class AnalyzerFactory {

    public static Analyzer buildAnalyzer(){
        String technology = getConfiguration();
        //Logger.vlog("Obtaining technology: " + technology);
        switch (technology){
            case "MongoDb":
                return new AnalyzerOnMongoDb();
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

    private static String getConfiguration(){return ConfigDataHandler.getInstance().configData.userDbArchitecture;}
}
