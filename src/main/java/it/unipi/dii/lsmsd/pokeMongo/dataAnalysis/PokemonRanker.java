package it.unipi.dii.lsmsd.pokeMongo.dataAnalysis;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;

import java.util.ArrayList;

public interface PokemonRanker {
    /**
     * Ranks most used Pokemon in the world
     * @return ArrayList of most used pokemon sorted by popularity
     */
    ArrayList<Pokemon> getBestPokemon();


    /**
     * Ranks most used Pokemon in a specific country
     * @param country we are interested in discovering most used Pokemon
     * @return ArrayList of most used Pokemon in a country ranked by popularity
     */
    ArrayList<Pokemon> getBestPokemon(String country);
}
