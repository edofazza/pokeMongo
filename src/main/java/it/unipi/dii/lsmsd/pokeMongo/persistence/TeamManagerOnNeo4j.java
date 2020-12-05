package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.google.common.annotations.VisibleForTesting;
import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import org.neo4j.driver.Record;

import java.util.ArrayList;

import static org.neo4j.driver.Values.parameters;

public class TeamManagerOnNeo4j extends Neo4jDbDatabase implements TeamManager{


    @VisibleForTesting
    // eventualmente insertAPokemonIntoTeam(Team t, int slot)
    public boolean insertAPokemonIntoTeam(User u, Pokemon p, int slot) {
        String query = "MATCH (n:User) WHERE n.username = $username " +
                "MATCH (p:Pokemon) WHERE p.name = $pokemon CREATE (n)-[:HAS {slot: $slot}]->(p)";
        return insert(query, parameters("username", u.getUsername(), "pokemon", p.getName(), "slot", slot));
    }

    public boolean deletePokemonFromTeamBySlot(User u, int slot){
        String query = "MATCH (n:User)-[w:HAS]->(p:Pokemon) WHERE n.username = $username and w.slot = $slot DELETE w";
        return remove(query, parameters("username", u.getUsername(), "slot", slot));
    }

    public boolean deletePokemon(){
        //TODO
        return true;
    }

    public boolean addPokemon(){
        //TODO
        return true;
    }

    public boolean deleteUser(){
        //TODO
        return true;
    }

    public boolean removeUser(){
        //TODO
        return true;
    }

    public boolean addFollow(User from, User to){
        //TODO
        return true;
    }

    public boolean removeFollow(User from, User to){
        //TODO
        return true;
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
