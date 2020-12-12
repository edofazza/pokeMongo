package conf;

import com.google.gson.Gson;

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
        try{
            BufferedReader br = new BufferedReader(new FileReader("PokeMongoAnalyticServer/conf/configFile.json"));
            configData = new Gson().fromJson(br, ConfigData.class);
        } catch (IOException ioe){
            configData = new ConfigData(10, "", "", "MongoDb", "InMemory", 30, "neo4j://localhost:7687", "neo4j://172.16.3.86:7687", "neo4j", "root", "Neo4j", "MongoDb");
            ioe.printStackTrace();
        }
    }
}
