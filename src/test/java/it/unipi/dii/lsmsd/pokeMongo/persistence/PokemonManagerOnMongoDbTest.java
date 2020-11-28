package it.unipi.dii.lsmsd.pokeMongo.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PokemonManagerOnMongoDbTest {
    @Test
    public void WHEN_insert_invoked_with_non_Pokemon_parameter_THEN_ClassCastException_thrown(){
        Assertions.assertThrows(ClassCastException.class, ()->{
            PokemonManagerOnMongoDb pokemonManagerOnMongoDb = new PokemonManagerOnMongoDb();
            Object c = new Character('c');
            pokemonManagerOnMongoDb.insert(c);
        });
    }

    @Test
    public void WHEN_insert_with_arraylist_invoked_with_non_Pokemon_parameter_THEN_ClassCastException_thrown(){
        Assertions.assertThrows(ClassCastException.class, ()->{
            PokemonManagerOnMongoDb pokemonManagerOnMongoDb = new PokemonManagerOnMongoDb();
            ArrayList<Object> al = new ArrayList<>();
            Object c = new Character('c');
            al.add(c);
            pokemonManagerOnMongoDb.insert(al);
        });
    }

    @Test
    public void WHEN_getWithFilter_invoked_with_non_Bson_parameter_THEN_ClassCastException_thrown(){
        Assertions.assertThrows(ClassCastException.class, ()->{
            PokemonManagerOnMongoDb pokemonManagerOnMongoDb = new PokemonManagerOnMongoDb();
            Object c = new Character('c');
            pokemonManagerOnMongoDb.getWithFilter(c);
        });
    }

    @Test
    public void WHEN_update_invoked_with_non_Bson_parameter_newvalue_THEN_ClassCastException_thrown(){
        Assertions.assertThrows(ClassCastException.class, ()->{
            PokemonManagerOnMongoDb pokemonManagerOnMongoDb = new PokemonManagerOnMongoDb();
            Object c = new Character('c');
            pokemonManagerOnMongoDb.getWithFilter(c);
        });
    }
}
