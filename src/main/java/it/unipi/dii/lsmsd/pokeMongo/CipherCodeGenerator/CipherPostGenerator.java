package it.unipi.dii.lsmsd.pokeMongo.CipherCodeGenerator;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CipherPostGenerator {
    public static void main(String[] args) {
        String s = new String();

        // ARRAY OF POSSIBLE CONTENTS
        String[] contents0 = {
                "I love ",
                "One day I will capture ",
                "I hate "
        };
        String[] contents1 = {
                "Got ya",
                "Love this pokemon",
                "It was my first pokemon",
                "When I was a child I had a card figuring this pokemon",
                "Never liked and never I will",
                "One day I will capture this pokemon",
                "Worst pokemon ever",
                "I love all pokemons",
                "This pokemon sucks...",
                "OMG loveeeeee it",
                "I am the best trainer :)",
                "Cool pokemon",
                "Garbage"
        };
        String[] contents2 = {
                " is my favorite pokemon",
                " is the ugliest",
                " is the most beautiful pokemon I have ever seen",
                " is such a cool"
        };

        // TAKE ALL THE POKEMONS FROM MONGODB
        PokemonManager pokemonManager = PokemonManagerFactory.buildManager();
        ArrayList<Pokemon> allPokemons =pokemonManager.getEveryPokemon();

        // TAKE ALL THE USERS
        UserManager userManager = UserManagerFactory.buildManager();
        ArrayList<User> allUsers = userManager.getEveryUser();

        for (User u: allUsers) {
            if (u.isAdmin())
                continue;

            for (int i = 0; i < 5; i++) {
                String content;

                // CHOOSE A POKEMON AT RANDOM
                int indexPokemon = (int) Math.round(Math.random() * allPokemons.size());
                Pokemon p = allPokemons.get(indexPokemon);

                // CHOOSE A CONTENT
                double odd = Math.random();

                int index;
                if (odd < 0.15) {
                    index = (int) Math.round(Math.random()*(contents0.length - 1));
                    content = contents0[index] + p.getName();
                } else if (odd >= 0.15 && odd < 0.85) {
                    index = (int) Math.round(Math.random()*(contents1.length - 1));
                    content = contents1[index];
                } else {
                    index = (int) Math.round(Math.random()*(contents2.length - 1));
                    content = p.getName() + contents2[index];
                }

                // CREATE QUERY
                s += "MATCH (u:User) WHERE u.username = \"" + u.getUsername() + "\" MATCH (p:Pokemon) WHERE p.name = " + p.getName() + " " +
                        "CREATE (u)-[:CREATED]->(p1:Post{creationDate: " + LocalDateTime.now() + ", content: \"" + content + "\"})-[:TOPIC]->(p)\n";

            }
        }

        System.out.println(s);
    }
}
