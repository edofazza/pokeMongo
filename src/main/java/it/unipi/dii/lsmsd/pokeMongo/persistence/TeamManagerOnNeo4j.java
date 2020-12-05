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

public class TeamManagerOnNeo4j extends Neo4jDbDatabase {
    @Override
    @VisibleForTesting
    public boolean insert(ArrayList<Object> toInsert) {
        return true;
    }

    @Override
    @VisibleForTesting
    public boolean insert(Object toInsert) {
        String query = "MATCH (n:User) WHERE n.username = $username " +
                "MATCH (p:Pokemon) WHERE p.name = $pokemon CREATE (n)-[:HAS {slot: 1}]->(p)";
        //Result result = tx.run(query, parameters("username", user.getUsername(), "pokemon", pokemon.getName()));
        return true;
    }

    @Override
    @VisibleForTesting
    public boolean remove(Object o) {
        return true;
    }

    @Override
    public ArrayList<Object> getAll() {
        return new ArrayList<>();
    }

    @Override
    @VisibleForTesting
    public ArrayList<Object> getWithFilter(Object filter) {
        User user = (User)filter;

        ArrayList<Object> team = new ArrayList<>();

        try (Session session = driver.session()){
            session.readTransaction((TransactionWork<Void>) tx -> {

                String query = "MATCH (u:User)-[h:HAS]->(p:Pokemon) WHERE u.username = $username RETURN p.name, p.type, p.sprite, h.slot";
                Result result = tx.run(query, parameters("username", user.getUsername()));
                while (result.hasNext()) {
                    Record r = result.next();
                    String name = r.get("p.name").asString();
                    String[] type = (r.get("p.type").asList()).toArray(new String[]{});
                    String sprite = r.get("p.sprite").asString();
                    int slot = r.get("h.slot").asInt();

                    Pokemon pokemon = new Pokemon(name, type, sprite, slot);
                    team.add(pokemon);
                }
                return null;
            });
        }

        return team;
    }

    @Override
    @VisibleForTesting
    public boolean update(Object target, Object newValue) {
        return true;
    }
}
