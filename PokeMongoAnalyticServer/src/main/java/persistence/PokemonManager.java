package persistence;



import bean.Pokemon;

import java.util.*;

public interface PokemonManager {

    /**
     * <h2>>Searches all the Pokemon inside the DB</h2>
     * @return an Arraylist containing all the Pokemon that satisfy search conditions
     */
    ArrayList<Pokemon> getEveryPokemon();

    boolean update(Object target, Object newValue);
    long updatePokemon(Pokemon old_p, Pokemon new_p);

}
