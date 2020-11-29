package it.unipi.dii.lsmsd.pokeMongo.config;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
        try{
            BufferedReader br = new BufferedReader(new FileReader("config/configFile.json"));
            configData = new Gson().fromJson(br, ConfigData.class);
        } catch (IOException ioe){
            configData = new ConfigData(10, "", "");
            ioe.printStackTrace();
        }

        System.out.println(configData.numrows);
    }
}
