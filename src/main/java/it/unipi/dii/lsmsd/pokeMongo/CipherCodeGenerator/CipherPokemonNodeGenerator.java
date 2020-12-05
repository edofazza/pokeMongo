package it.unipi.dii.lsmsd.pokeMongo.CipherCodeGenerator;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManagerOnMongoDb;

import java.util.ArrayList;

public class CipherPokemonNodeGenerator {
    public static void main(String[] args) {
        PokemonManagerOnMongoDb pokemonManagerOnMongoDb = new PokemonManagerOnMongoDb();
        ArrayList<Object> pokemons =  pokemonManagerOnMongoDb.getAll();

        String query = new String();

        for (Object p: pokemons) {
            String name = ((Pokemon)p).getName();
            String type = ((Pokemon)p).getTypesSingleStringForCipher();
            String sprite = ((Pokemon)p).getSprite();
            double catchRate = ((Pokemon)p).getCapture_rate();

            query += "CREATE (" + name + ":Pokemon { name: \"" + name + "\", " +
                    "type: [" + type + "], sprite: \"" + sprite + "\", " +
                    "capture_rate: "+ catchRate + "})\n";
        }

        System.out.println(query);
    }
}
