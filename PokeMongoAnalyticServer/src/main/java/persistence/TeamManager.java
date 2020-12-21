package persistence;


import bean.Pokemon;
import bean.PokemonAndCatchRate;
import javafx.util.Pair;

import java.util.*;

public interface TeamManager {

    int getUsersNumberThatOwnAPokemon(Pokemon p);
    List<Pair<String, Integer>> getUsersNumberThatOwnsAPokemonNotFiltered();
    void updateCatchRateOfPokemon(List<PokemonAndCatchRate> catch_rates);
}
