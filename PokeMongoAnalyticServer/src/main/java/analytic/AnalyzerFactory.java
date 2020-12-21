package analytic;

import conf.ConfigDataHandler;

public class AnalyzerFactory {

    public static Analyzer buildAnalyzer(){
        String technology = getConfiguration();
        switch (technology){
            case "MongoDb":
                return new AnalyzerOnMongoDb();
            default:
                try{
                    throw new IllegalArgumentException();
                }catch (IllegalArgumentException iae){
                    iae.printStackTrace();
                };
                return null;
        }
    }

    private static String getConfiguration(){return ConfigDataHandler.getInstance().configData.userDbArchitecture;}
}
