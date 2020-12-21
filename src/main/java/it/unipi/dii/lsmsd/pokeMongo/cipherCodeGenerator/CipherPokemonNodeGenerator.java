package it.unipi.dii.lsmsd.pokeMongo.cipherCodeGenerator;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManagerFactory;

import java.util.ArrayList;

public class CipherPokemonNodeGenerator {
    public static void main(String[] args) {
        PokemonManager pokemonManager = PokemonManagerFactory.buildManager();
        ArrayList<Pokemon> pokemons =  pokemonManager.getEveryPokemon();

        String query = new String();

        for (Pokemon p: pokemons) {
            String name = (p).getName();
            String type = (p).getTypesSingleStringForCipher();
            String sprite = (p).getSprite();
            double catchRate = (p).getCapture_rate();

            query += "CREATE (" + name + ":Pokemon { name: \"" + name + "\", " +
                    "type: [" + type + "], sprite: \"" + sprite + "\", " +
                    "capture_rate: "+ catchRate + "})\n";
        }

        System.out.println(query);
    }
}
