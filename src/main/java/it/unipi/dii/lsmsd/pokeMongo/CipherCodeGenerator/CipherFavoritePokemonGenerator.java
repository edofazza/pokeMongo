package it.unipi.dii.lsmsd.pokeMongo.CipherCodeGenerator;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;

import java.util.ArrayList;

public class CipherFavoritePokemonGenerator {
    public static void main(String[] args) {
        String s = new String();

        // TAKE ALL THE POKEMONS FROM MONGODB
        PokemonManager pokemonManager = PokemonManagerFactory.buildManager();
        ArrayList<Pokemon> allPokemons =pokemonManager.getEveryPokemon();

        // TAKE ALL THE USERS
        UserManager userManager = UserManagerFactory.buildManager();
        ArrayList<User> allUsers = userManager.getEveryUser();

        for (User u: allUsers) {
            if (u.isAdmin())
                continue;

            for (int i = 0; i < 15; i++) {
                if (Math.random() < 0.30)
                    continue;
                else {
                    // CHOOSE A POKEMON AT RANDOM
                    int index = (int) Math.round(Math.random() * (allPokemons.size() - 1));
                    Pokemon p = allPokemons.get(index);

                    // CREATE QUERY
                    s += "MATCH (from:User) WHERE from.username = \"" + u.getUsername() + "\" MATCH (to:Pokemon) " +
                            "WHERE to.name = \""+ p.getName() +"\" MERGE (from)-[:LIKES]->(to)\n";
                }
            }
        }

        System.out.println(s);

    }
}
