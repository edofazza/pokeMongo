package it.unipi.dii.lsmsd.pokeMongo.CipherCodeGenerator;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.*;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CipherSubPostGenerator {
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
                "Garbage",
                "Me too",
                "Shut up",
                "You sucks",
                "Good point",
                "Good point bro",
                "I believe the same",
                "Why???",
                "What!?",
                "Are you kidding me?",
                "I hope it's a joke"
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


        for (Pokemon p: allPokemons) {
            // GET THE POST FOR A SPECIFIC POKEMON
            List<Pair<Post, Integer>> posts = PostManagerFactory.buildManager().getPostsByPokemon(p.getName());

            // FOR EVERY POST
                // SELECT THE POST
                // SELECT AT RANDOM A RANGE OF USER (# OF SUBPOST)
                // FOR FOR IT
                // CHOOSE AT RANDOM A USER
                // CHECK IF ADMIN
                // CHOOSE AT RANDOM A CONTENT
                // ADD THE SUBPOST
            for (Pair<Post, Integer> pair: posts) {
                Post post = pair.getKey();

                int numOfAnswers = (int) Math.floor(Math.random() * 5);

                for (int i = 0; i < numOfAnswers; i++) {
                    User user = allUsers.get((int) Math.floor(Math.random()*(allUsers.size() - 1)));

                    if (user.isAdmin())
                        continue;

                    // CHOOSE A CONTENT
                    double odd = Math.random();
                    String content;

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

                    s += "MATCH (u:User) WHERE u.username = " + user.getUsername() + " " +
                            "MATCH (uTopic:User)-[:CREATED]->(pTopic:Post)-[:TOPIC]->(pokTopic:Pokemon) " +
                            "WHERE uTopic.username = \"" + post.getAuthorUsername() +
                            "\" and pTopic.creationDate = \"" +  post.getPublishDate() + "\" " +
                            "and pTopic.content = \"" + post.getContent() + "\" and pokTopic.name = " +
                            post.getPokemonName() + " CREATE (u)-[:CREATED]->(p1:Post{content: \"" +
                            content + "\", creationDate: \"" + LocalDateTime.now() + "\"})-[:TOPIC]->(pTopic)\n";
                }

            }

        }

        System.out.println(s);
    }

}
