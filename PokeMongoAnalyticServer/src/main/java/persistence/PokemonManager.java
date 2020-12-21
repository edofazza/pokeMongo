package persistence;



import bean.Pokemon;
import java.util.ArrayList;

public interface PokemonManager {

    /**
     * <h2>Searches all the Pokemon inside the DB</h2>
     * @return an Arraylist containing all the Pokemon that satisfy search conditions
     */
    ArrayList<Pokemon> getEveryPokemon();

    /**
     * <h2>Updates one or more fields of one or more Pokemon</h2>
     * @param target query to target Pokemon to update
     * @param newValue query to express new value of the field(s)
     * @return true if the update is correctly submitted
     */
    boolean update(Object target, Object newValue);

    /**
     * <h2>Updates old_p fields according to new_p</h2>
     * @param old_p Pokemon to modify
     * @param new_p Pokemon with updated fields
     * @return number of modified Pokemon
     */
    long updatePokemon(Pokemon old_p, Pokemon new_p);

}
