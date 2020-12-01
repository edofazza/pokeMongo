package it.unipi.dii.lsmsd.pokeMongo.utils;

import it.unipi.dii.lsmsd.pokeMongo.config.ConfigDataHandler;

public class Logger {
    public static void warning(String text){
        if(ConfigDataHandler.getInstance().configData.verbosityLevel >= 1)
            System.out.println("[WARNING] " + text);
    }

    public static void error(String text){
        if(ConfigDataHandler.getInstance().configData.verbosityLevel >= 1)
            System.out.println("[ERROR] " + text);
    }

    public static void log(String text){
        if(ConfigDataHandler.getInstance().configData.verbosityLevel >= 1)
            System.out.println("[LOG] " + text);

    }

    public static void vlog(String text){
        if(ConfigDataHandler.getInstance().configData.verbosityLevel >= 2)
            System.out.println("[VLOG] " + text);
    }

    public static void vvlog(String text){
        if(ConfigDataHandler.getInstance().configData.verbosityLevel >= 3)
            System.out.println("[VVLOG] " + text);
    }


}
