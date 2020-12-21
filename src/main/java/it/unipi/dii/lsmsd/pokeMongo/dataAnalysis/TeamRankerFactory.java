package it.unipi.dii.lsmsd.pokeMongo.dataAnalysis;

import it.unipi.dii.lsmsd.pokeMongo.config.ConfigDataHandler;
import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManagerOnNeo4j;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

public class TeamRankerFactory {
    public static PokemonRanker buildRanker(){
        String technology = getConfiguration();
        switch (technology){
            case "Neo4j":
                return new TeamManagerOnNeo4j();
            default:
                try{
                    throw new IllegalArgumentException();
                }catch (IllegalArgumentException iae){
                    iae.printStackTrace();
                    Logger.error("Invalid database technology or missing implementation");
                };
                return null;
        }
    }

    private static String getConfiguration(){
        return ConfigDataHandler.getInstance().configData.teamDbArchitecture;
    }
}
