package persistence;


import bean.Pokemon;
import bean.PokemonAndCatchRate;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public interface TeamManager {
    int getUsersNumberThatOwnAPokemon(Pokemon p);
    List<Pair<String, Integer>> getUsersNumberThatOwnsAPokemonNotFiltered();
    void updateCatchRateOfPokemon(List<PokemonAndCatchRate> catch_rates);
}
