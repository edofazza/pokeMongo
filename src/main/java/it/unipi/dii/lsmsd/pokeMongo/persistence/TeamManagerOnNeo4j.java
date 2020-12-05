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

    public boolean deletePokemon(Pokemon p){
        String query = "MATCH (p:Pokemon) OPTIONAL MATCH (p)<-[h:HAS]-(:User) WHERE p.name = $name DELETE p, h";
        return remove(query, parameters("name", p.getName()));
    }

    public boolean addPokemon(Pokemon p){
        String query = "MERGE (b:Pokemon { name: $name, type: " + p.getTypesSingleStringForCipher() +
                ",sprite: $sprite, capture_rate: $capture_rate})";

        return insert(query, parameters("name", p.getName(), "sprite", p.getSprite(), "capture_rate", p.getCapture_rate()));
    }

    public boolean deleteUser(User u){
        return deleteUser(u.getUsername());
    }

    // TODO: eliminare poi anche tutte le relazioni di follow
    public boolean deleteUser(String username){
        String query = "MATCH (u:User) WHERE u.username = $username " +
                "OPTIONAL MATCH (u)-[h:HAS]->(:Pokemon) OPTIONAL MATCH (:User)-[fo:FOLLOW]->(u)-[f:FOLLOW]->(:User) " +
                "OPTIONAL MATCH (:User)-[fo:FOLLOW]->(u) DELETE u, h, f, fo";
        return remove(query, parameters("username", username));
    }

    //Eventualmente se il bean non è stato ancora creato si può passare direttamente lo username proposto in fase di
    //registrazione
    public boolean addUser(User u){
        String query = "MERGE (u:User { username: $username})";
        return insert(query, parameters("username", u.getUsername()));
    }

    public boolean addFollow(User from, User to){
        String query = "MATCH (from:User) WHERE from.username = $username" +
                "MATCH (to:User) WHERE to.username = $username2 MERGE (from)-[:FOLLOW]->(to)";
        return insert(query, parameters("username", from.getUsername(), "username2", to.getUsername()));
    }

    public boolean removeFollow(User from, User to){
        String query = "MATCH (from:User)-[w:FOLLOW]->(to:User) WHERE to.username = $username and from.username = $username2 DELETE w";
        return remove(query, parameters("username", from.getUsername(), "username2", to.getUsername()));
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
