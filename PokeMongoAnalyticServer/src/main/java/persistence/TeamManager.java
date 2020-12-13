package persistence;


import bean.Pokemon;
import bean.PokemonAndCatchRate;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public interface TeamManager {
//
//    /**
//     * Inserts a Pokemon into a user's team
//     * @param trainer user that has just caught pokemon
//     * @param caught Pokemon to add to trainer's team
//     * @param slot position in which the Pokemon caught will be stored
//     * @return true if the Pokemon is correctly added to the team
//     * @throws SlotAlreadyOccupiedException if the slot selected is already occupied by another pokemon, this exception is thrown
//     */
//    boolean insertAPokemonIntoTeam(User trainer, Pokemon caught, int slot);
//
//
//    void isFreeSlot(User u, int slot) throws SlotAlreadyOccupiedException;
//
//    /**
//     * Removes a Pokemon from a user's team
//     * @param trainer User from whose team the Pokemon is deleted
//     * @param slot position in the team of the Pokemon to remove
//     * @return true if Pokemon is successfully deleted from trainer's team
//     */
//    boolean deletePokemonFromTeamBySlot(User trainer, int slot);
//
//
//    /**
//     * Removes a Pokemon from the database
//     * @param pokemonName name of the Pokemon we want to delete
//     * @return true if Pokemon is successfully deleted
//     */
//    boolean deletePokemon(String pokemonName);
//
//
//    /**
//     * Adds a Pokemon to the database, if ot doesn't exist already
//     * @param toAdd Pokemon to insert into the database
//     * @return true if a Pokemon is successfully added
//     * @throws DuplicatePokemonException if a Pokemon with the same name already exists, this exception is thrown
//     */
//    boolean addPokemon(Pokemon toAdd) throws DuplicatePokemonException;
//
//
//    /**
//     * Retrieves a user's team
//     * @param trainer user whose team we want to retrieve
//     * @return an array of 6 Pokemon that is the trainer's team
//     */
//    Pokemon[] getUserTeam(User trainer);
//
//
//    /**
//     * Retrieves all the teams of all friends of a user
//     * @param u User whose friends' team are retrieved
//     * @return a list of Pokemon indicating friends' team
//     */
//    ArrayList<Pokemon> getFriendsTeam(User u);


    int getUsersNumberThatOwnAPokemon(Pokemon p);
    List<Pair<String, Integer>> getUsersNumberThatOwnsAPokemonNotFiltered();
    void updateCatchRateOfPokemon(List<PokemonAndCatchRate> catch_rates);
}
