package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;

import java.util.*;
import java.util.Map;

public interface PokemonManager {

    /**
     * <h2>>Searches all the Pokemon inside the DB that satisfy ALL the conditions specified</h2>
     * @param parameters <h3>set of filters to apply</h3>.
     *                   <p>Key is a it.unipi.dii.lsmsd.pokeMongo.persistence.Filter object</p>
     *                   <p>Value is a String value</p>
     * @return an Arraylist containing all the Pokemon that satisfy search conditions
     */
    ArrayList<Pokemon> searchWithFilter(Map<Filter, String> parameters);

    /**
     * Adds a Pokemon to the db
     * @param toAdd Pokemon we want to add
     * @return true if exactly one Pokemon has been added to the db
     */
    boolean addPokemon(Pokemon toAdd);

    /**
     * Adds one or more Pokemon to the db
     * @param toAdd List of Pokemon we want to add
     * @return true if every Pokemon within the specified list have been added
     */
    boolean addPokemon(List<Pokemon> toAdd);

    /**
     * Removes a Pokemon from the db
     * @param toRemove Pokemon we want to remove
     * @return true if exactly one Pokemon has been removed
     */
    boolean removePokemon(Pokemon toRemove);

    /**
     * Removes a Pokemon from the db
     * @param name name of thePokemon we want to remove
     * @return true if exactly one Pokemon has been removed
     */
    boolean removePokemon(String name);
}
