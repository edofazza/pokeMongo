package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.config.*;

public class PokemonManagerFactory {
    public static PokemonManager buildManager(){
        String technology = getConfiguration();
        switch (technology){
            case "MongoDb":
                return new PokemonManagerOnMongoDb();
            default:
                try{
                    throw new IllegalArgumentException();
                }catch (IllegalArgumentException iae){
                    iae.printStackTrace();
                    //log error
                };
                return null;
        }
    }

    public static String getConfiguration(){
        return ConfigDataHandler.getInstance().configData.pokemonDbArchitecture;
    }
}