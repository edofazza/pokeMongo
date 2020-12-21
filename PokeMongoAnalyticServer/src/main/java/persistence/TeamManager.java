package persistence;


import bean.Pokemon;
import bean.PokemonAndCatchRate;
import javafx.util.Pair;

import java.util.*;

public interface TeamManager {

    /**
     * Counts how many users own a specific Pokemon
     * @param p Pokemon on wich owners are counted
     * @return number of users that own p
     */
    int getUsersNumberThatOwnAPokemon(Pokemon p);

    /**
     * Maps each Pokemon with the number of users that own ut
     * @return a List of pairs with this mapping
     */
    List<Pair<String, Integer>> getUsersNumberThatOwnsAPokemonNotFiltered();

    /**
     * updates catch rates of each Pokemon
     * @param catch_rates maps each Pokemon with the new value of the catch rate
     */
    void updateCatchRateOfPokemon(List<PokemonAndCatchRate> catch_rates);
}
