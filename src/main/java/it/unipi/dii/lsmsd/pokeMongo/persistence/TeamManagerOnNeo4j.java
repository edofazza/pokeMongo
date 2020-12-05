package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.google.common.annotations.VisibleForTesting;
import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.TransactionWork;

import java.util.ArrayList;

import static org.neo4j.driver.Values.parameters;

public class TeamManagerOnNeo4j extends Neo4jDbDatabase implements TeamManager{


    @VisibleForTesting
    // eventualmente insertAPokemonIntoTeam(Team t, int slot)
    public boolean insertAPokemonIntoTeam(User u, Pokemon p, int slot) {
        String query = "MATCH (n:User) WHERE n.username = $username " +
                "MATCH (p:Pokemon) WHERE p.name = $pokemon CREATE (n)-[:HAS {$slot: 1}]->(p)";
        return insert(query);
    }



    @VisibleForTesting
    //eventualmente ritorna un Team
    public User getUserTeam(User target) {
        Pokemon[] team = new Pokemon[6];
        String query = "MATCH (u:User)-[h:HAS]->(p:Pokemon) WHERE u.username = $username RETURN p.name, p.type, p.sprite, h.slot";
        ArrayList<Object> res = getWithFilter(query);
        for(int i=0; i<6; i++){
            Object o = res.get(i);
            Record r =(Record)o;
            String name = r.get("p.name").asString();
            String[] type = (r.get("p.type").asList()).toArray(new String[]{});
            String sprite = r.get("p.sprite").asString();
            int slot = r.get("h.slot").asInt();
            Pokemon pokemon = new Pokemon(name, type, sprite, slot);
            team[i]=pokemon;
        }
        target.addTeam(team);
        return target;

    }

}
