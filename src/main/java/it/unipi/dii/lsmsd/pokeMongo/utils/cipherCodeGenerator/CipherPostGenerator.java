package it.unipi.dii.lsmsd.pokeMongo.utils.cipherCodeGenerator;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

        String[] contents3 = {
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

        // TAKE ALL THE POKEMONS FROM MONGODB
        PokemonManager pokemonManager = PokemonManagerFactory.buildManager();
        ArrayList<Pokemon> allPokemons =pokemonManager.getEveryPokemon();

        // TAKE ALL THE USERS
        UserManager userManager = UserManagerFactory.buildManager();
        ArrayList<User> allUsers = userManager.getEveryUser();

        int indice = 0;
        for (User u: allUsers) {
            if (u.isAdmin())
                continue;

            int nPost = (int) Math.floor(Math.random() * 3);
            for (int i = 0; i < nPost; i++) {
                String content;

                // CHOOSE A POKEMON AT RANDOM
                int indexPokemon = (int) Math.round(Math.random() * (allPokemons.size() -1));
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
                LocalDateTime localDateTime = LocalDateTime.now();
                //s += "MATCH (u:User) WHERE u.username = \"" + u.getUsername() + "\" MATCH (p:Pokemon) WHERE p.name = " + p.getName() + " " +
                  //      "CREATE (u)-[:CREATED]->(p1:Post{creationDate: \"" + localDateTime + "\", content: \"" + content + "\"})-[:TOPIC]->(p)\n";

                int nFile = indice/7000;
                indice++;

                if (nFile > 76)
                    nFile = 76;

                String query = "MATCH (u:User) WHERE u.username = \"" + u.getUsername() + "\" MATCH (p:Pokemon) WHERE p.name = " + p.getName() + " " +
                        "CREATE (u)-[:CREATED]->(p1:Post{creationDate: \"" + localDateTime + "\", content: \"" + content + "\"})-[:TOPIC]->(p)\n";

                String query3 = u.getUsername() + "," + p.getName() + "," + localDateTime + "," + content + "\n";
                try {
                    //Files.write(Paths.get("cipherCodeRepo/cipherPost/cipherUser"+ nFile + ".txt"), query.getBytes(), StandardOpenOption.APPEND);
                    Files.write(Paths.get("cipherCodeRepo/cipherPost/cipherPostWhole.csv"), query3.getBytes(), StandardOpenOption.APPEND);
                }catch (IOException e) {
                    e.printStackTrace();
                }

                //////////////////////////////////////////////////////////////////////////////////////////////////
                // ADD SOME COMMENTS TO IT
                int numOfAnswers = (int) Math.floor(Math.random() * 2);

                for (int j = 0; j < numOfAnswers; j++) {
                    User user = allUsers.get((int) Math.floor(Math.random()*(allUsers.size() - 1)));

                    if (user.isAdmin())
                        continue;

                    // CHOOSE A CONTENT
                    double oddSub = Math.random();
                    String contentSub;

                    int indexSub;
                    if (oddSub < 0.15) {
                        indexSub = (int) Math.round(Math.random()*(contents0.length - 1));
                        contentSub = contents0[indexSub] + p.getName();
                    } else if (oddSub >= 0.15 && oddSub < 0.85) {
                        indexSub = (int) Math.round(Math.random()*(contents3.length - 1));
                        contentSub = contents3[indexSub];
                    } else {
                        indexSub = (int) Math.round(Math.random()*(contents2.length - 1));
                        contentSub = p.getName() + contents2[indexSub];
                    }

                    /*s += "MATCH (u:User) WHERE u.username = " + user.getUsername() + " " +
                            "MATCH (uTopic:User)-[:CREATED]->(pTopic:Post)-[:TOPIC]->(pokTopic:Pokemon) " +
                            "WHERE uTopic.username = \"" + u.getUsername() +
                            "\" and pTopic.creationDate = \"" +  localDateTime + "\" " +
                            "and pTopic.content = \"" + content + "\" and pokTopic.name = " +
                            p.getName() + " CREATE (u)-[:CREATED]->(p1:Post{content: \"" +
                            contentSub + "\", creationDate: \"" + LocalDateTime.now() + "\"})-[:TOPIC]->(pTopic)\n";*/

                    int nFile2 = indice/6000;
                    indice++;

                    if (nFile2 > 76)
                        nFile2 = 76;

                    String query2 = "MATCH (u:User) WHERE u.username = " + user.getUsername() + " " +
                            "MATCH (uTopic:User)-[:CREATED]->(pTopic:Post)-[:TOPIC]->(pokTopic:Pokemon) " +
                            "WHERE uTopic.username = \"" + u.getUsername() +
                            "\" and pTopic.creationDate = \"" +  localDateTime + "\" " +
                            "and pTopic.content = \"" + content + "\" and pokTopic.name = " +
                            p.getName() + " CREATE (u)-[:CREATED]->(p1:Post{content: \"" +
                            contentSub + "\", creationDate: \"" + LocalDateTime.now() + "\"})-[:TOPIC]->(pTopic)\n";

                    String query4 = user.getUsername() + "," + u.getUsername() + "," + localDateTime + "," + content + ","
                            + p.getName() + "," + contentSub + "," + LocalDateTime.now() + "\n";

                    try {
                        //Files.write(Paths.get("cipherCodeRepo/cipherPost/cipherUser"+ nFile2 + ".txt"), query2.getBytes(), StandardOpenOption.APPEND);
                        Files.write(Paths.get("cipherCodeRepo/cipherPost/cipherSubpostWhole.csv"), query4.getBytes(), StandardOpenOption.APPEND);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
