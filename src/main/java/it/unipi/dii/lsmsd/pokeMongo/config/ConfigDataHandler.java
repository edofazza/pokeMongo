package it.unipi.dii.lsmsd.pokeMongo.config;

import com.google.gson.Gson;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigDataHandler {
    public ConfigData configData;
    private static ConfigDataHandler instance;

    public static ConfigDataHandler getInstance(){
        if(instance == null){
            instance = new ConfigDataHandler();
        }
        return instance;
    }

    public ConfigDataHandler(){
        Logger.log("IMPORTING CONFIGURATION FILE");
        try{
            BufferedReader br = new BufferedReader(new FileReader("config/configFile.json"));
            configData = new Gson().fromJson(br, ConfigData.class);
        } catch (IOException ioe){
            Logger.log("LOADED DEFAULT CONFIGURATION");
            configData = new ConfigData(10, "", "", "", "", "", 1);
            ioe.printStackTrace();
        }

        System.out.println(configData.numrows);
    }
}
