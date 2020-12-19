package it.unipi.dii.lsmsd.pokeMongo.config;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigDataHandler {
    public ConfigData configData;
    private static ConfigDataHandler instance;

    /**
     * Singleton
     * @return the instance related to the ConfigDataHandler
     */
    public static ConfigDataHandler getInstance(){
        if(instance == null){
            instance = new ConfigDataHandler();
        }
        return instance;
    }

    /**
     * Loads the configuration file and create a new <code>ConfigData</code>
     */
    public ConfigDataHandler(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("config/configFile.json"));
            configData = new Gson().fromJson(br, ConfigData.class);
        } catch (IOException ioe){
            configData = new ConfigData(10, "", "", "MongoDb", "MongoDb", "Neo4j", "Neo4j","Neo4j", "MongoDb", 30,1, "neo4j://localhost:7687", "neo4j://172.16.3.86:7687/", "neo4j", "root", 9);
            ioe.printStackTrace();
        }
    }
}
