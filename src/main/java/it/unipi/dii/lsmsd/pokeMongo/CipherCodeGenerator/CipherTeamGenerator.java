package it.unipi.dii.lsmsd.pokeMongo.CipherCodeGenerator;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


public class CipherTeamGenerator {
    public static void main(String[] args) {

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

            for (int i = 0; i < 6; i++) {
                if (Math.random() < 0.75)
                    continue;
                else {
                    // CHOOSE A POKEMON AT RANDOM
                    int index = (int) Math.round(Math.random() * (allPokemons.size() - 1));
                    Pokemon p = allPokemons.get(index);

                    int nFile = indice/9000;
                    indice++;

                    if (nFile > 76)
                        nFile = 76;

                    String query = "MATCH (n:User) WHERE n.username = \"" + u.getUsername() + "\" MATCH (p:Pokemon) " +
                            "WHERE p.name = \"" + p.getName() + "\" CREATE (n)-[:HAS {slot: " + i + "}]->(p)\n";

                    String query2 = u.getUsername() + "," + p.getName() + "," + i + "\n";

                    try {
                        //Files.write(Paths.get("cipherCodeRepo/cipherTeam/cipherUser"+ nFile + ".txt"), query.getBytes(), StandardOpenOption.APPEND);
                        Files.write(Paths.get("cipherCodeRepo/cipherTeam/cipherTeamWhole.csv"), query2.getBytes(), StandardOpenOption.APPEND);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }

    }
}
