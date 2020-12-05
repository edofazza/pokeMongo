package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.google.common.annotations.VisibleForTesting;
import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.exceptions.DuplicatePokemonException;
import it.unipi.dii.lsmsd.pokeMongo.exceptions.DuplicateUserException;
import it.unipi.dii.lsmsd.pokeMongo.exceptions.SlotAlreadyOccupiedException;
import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class TeamManagerOnNeo4j extends Neo4jDbDatabase implements TeamManager{


    @VisibleForTesting
    // eventualmente insertAPokemonIntoTeam(Team t, int slot)
    public boolean insertAPokemonIntoTeam(User u, Pokemon p, int slot) throws SlotAlreadyOccupiedException{
        if(slotAlreadyOccupied(u, slot))
            throw new SlotAlreadyOccupiedException();

        String query = "MATCH (n:User) WHERE n.username = $username " +
                "MATCH (p:Pokemon) WHERE p.name = $pokemon CREATE (n)-[:HAS {slot: $slot}]->(p)";
        return insert(query, parameters("username", u.getUsername(), "pokemon", p.getName(), "slot", slot));
    }

    private boolean slotAlreadyOccupied(User u, int slot){
        String controlQuery = "MATCH (n:User)-[w:HAS {slot: $slot}]->(p:Pokemon) WHERE n.username = $username RETURN count(w) as link_count";
        ArrayList<Object> res = getWithFilter(controlQuery, parameters("slot", slot, "username", u.getUsername()));
        Record d = (Record)res.get(0);
        return (d.get("link_count").asInt() > 0);
    }

    public boolean deletePokemonFromTeamBySlot(User u, int slot){
        String query = "MATCH (n:User)-[w:HAS]->(p:Pokemon) WHERE n.username = $username and w.slot = $slot DELETE w";
        return remove(query, parameters("username", u.getUsername(), "slot", slot));
    }

    public boolean deletePokemon(Pokemon p){
        String query = "MATCH (p:Pokemon) OPTIONAL MATCH (p)<-[h:HAS]-(:User) WHERE p.name = $name DELETE p, h";
        return remove(query, parameters("name", p.getName()));
    }

    public boolean addPokemon(Pokemon p) throws DuplicatePokemonException{
        if(pokemonAlreadyExist(p))
            throw new DuplicatePokemonException();

        String query = "MERGE (b:Pokemon { name: $name, type: " + p.getTypesSingleStringForCipher() +
                ",sprite: $sprite, capture_rate: $capture_rate})";

        return insert(query, parameters("name", p.getName(), "sprite", p.getSprite(), "capture_rate", p.getCapture_rate()));
    }

    private boolean pokemonAlreadyExist(Pokemon p){
        String controlQuery = "MATCH (p:Pokemon) WHERE p.name = $name RETURN count(p) as pokemon_count";
        ArrayList<Object> res = getWithFilter(controlQuery, parameters("name",p.getName()));
        Record d = (Record)res.get(0);
        return (d.get("pokemon_count").asInt() > 0);
    }


    @VisibleForTesting
    //eventualmente ritorna un Team
    public Pokemon[] getUserTeam(User target) {
        Pokemon[] team = new Pokemon[6];
        String query = "MATCH (u:User)-[h:HAS]->(p:Pokemon) WHERE u.username = $username RETURN p.name, p.type, p.sprite, h.slot";
        ArrayList<Object> res = getWithFilter(query, parameters("username", target.getUsername()));
        for(Object o: res){
            Record r =(Record)o;
            String name = r.get("p.name").asString();
            String[] type = (r.get("p.type").asList()).toArray(new String[]{});
            String sprite = r.get("p.sprite").asString();
            int slot = r.get("h.slot").asInt();
            Pokemon pokemon = new Pokemon(name, type, sprite, slot);
            team[slot]=pokemon;
        }
        return team;
    }

}
