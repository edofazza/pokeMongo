package persistence;


import conf.ConfigDataHandler;

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
                };
                return null;
        }
    }

    public static String getConfiguration(){
        return ConfigDataHandler.getInstance().configData.pokemonDbArchitecture;
    }
}