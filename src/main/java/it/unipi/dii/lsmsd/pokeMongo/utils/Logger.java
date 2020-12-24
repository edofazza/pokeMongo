package it.unipi.dii.lsmsd.pokeMongo.utils;

import com.google.common.annotations.VisibleForTesting;
import it.unipi.dii.lsmsd.pokeMongo.config.ConfigDataHandler;

public class Logger {

    @VisibleForTesting
    public static void warning(String text){
        if (nullMsg(text))
            return;

        if(ConfigDataHandler.getInstance().configData.verbosityLevel >= 1){
            LoggerThread lt = new LoggerThread("[WARNING] " + text);
            lt.start();
        }

    }

    public static void error(String text){
        if (nullMsg(text))
            return;

        if(ConfigDataHandler.getInstance().configData.verbosityLevel >= 1){
            LoggerThread lt = new LoggerThread("[ERROR] " + text);
            lt.start();
        }
    }

    public static void log(String text){
        if (nullMsg(text))
            return;

        if(ConfigDataHandler.getInstance().configData.verbosityLevel >= 1){
            LoggerThread lt = new LoggerThread("[LOG] " + text);
            lt.start();
        }
    }

    public static void vlog(String text){
        if (nullMsg(text))
            return;

        if(ConfigDataHandler.getInstance().configData.verbosityLevel >= 2){
            LoggerThread lt = new LoggerThread("[VLOG] " + text);
            lt.start();
        }
    }

    public static void vvlog(String text){
        if (nullMsg(text))
            return;

        if(ConfigDataHandler.getInstance().configData.verbosityLevel >= 3){
            LoggerThread lt = new LoggerThread("[VVLOG] " + text);
            lt.start();
        }
    }

    private static boolean nullMsg(String text) {
        if (text == null) {
            warning("NullPointerException avoided");
            return true;
        }
        return false;
    }

}
