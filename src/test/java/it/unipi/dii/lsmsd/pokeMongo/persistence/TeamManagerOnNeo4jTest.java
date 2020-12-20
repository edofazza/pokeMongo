package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TeamManagerOnNeo4jTest {
    @Test
    public void WHEN_getPokemons_invoked_with_non_ArrayOfRecords_parameter_THEN_ClassCastException_thrown(){
        Assertions.assertThrows(ClassCastException.class, ()->{
            TeamManagerOnNeo4j teamManagerOnNeo4j = new TeamManagerOnNeo4j();

            Object c = new Character('c');
            ArrayList<Object> cList = new ArrayList<>();
            cList.add(c);
            ArrayList<Pokemon> arrayList = new ArrayList<Pokemon>();
            teamManagerOnNeo4j.getPokemons(arrayList, cList);
        });
    }
}
