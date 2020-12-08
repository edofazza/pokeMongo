package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.Post;
import it.unipi.dii.lsmsd.pokeMongo.exceptions.DuplicatePostException;
import javafx.util.Pair;
import org.neo4j.driver.Record;
import static org.neo4j.driver.Values.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

public class PostManagerOnNeo4j extends Neo4jDbDatabase implements PostManager{
    public boolean insertPost(Post p) throws DuplicatePostException{
        if(existPost(p))
            throw new DuplicatePostException();
        String query = "MATCH (u:User) WHERE u.username = $username MATCH (p:Pokemon) WHERE p.name = $name " +
                "CREATE (u)-[:CREATED]->(p1:Post{creationDate: $date, content: $content})-[:TOPIC]->(p)";

        return insert(query, parameters("username", p.getAuthorUsername(), "name", p.getPokemonName(), "date", p.getPublishDate(), "content", p.getContent()));
    }

    public boolean deletePost(Post p){
        String query = "MATCH (u:User)-[:CREATED]->(p:Post)-[:TOPIC]->(p1:Pokemon) WHERE u.username = $username and p.creationDate = $date and p.content = $content and p1.name = $name DETACH DELETE p";

        return remove(query, parameters("username", p.getAuthorUsername(), "name", p.getPokemonName(), "date", p.getPublishDate(), "content", p.getContent()));
    }


    public boolean modifyPost(Post p_old, Post p_new){
        //String query = "MATCH MATCH (u:User)-[:CREATED]->(p:Post)-[:TOPIC]->(p1:Pokemon) WHERE u.username = $username and p.creationDate = $date and content = $content and p1.name = $name SET p.creationDate = $date2 and content = $content2";
        return true;
    }


    public boolean existPost(Post p){
        String query = "MATCH (u:User)-[:CREATED]->(p:Post)-[:TOPIC]->(p1:Pokemon) " +
                "WHERE u.username = $username and p.creationDate = $date and p.content = $content " +
                "and p1.name = $name return count(p) as post_count";
        ArrayList<Object> res = getWithFilter(query, parameters("username", p.getAuthorUsername(), "name", p.getPokemonName(), "date", p.getPublishDate(), "content", p.getContent()));
        Record d = (Record)res.get(0);
        return (d.get("post_count").asInt() > 0);
    }

    public List<Pair<Post, Integer>> getPostsByPokemon(String p){
        String query = "MATCH (u:User)-[:CREATED]->(p:Post)-[:TOPIC]->(p1:Pokemon) WHERE p1.name = $name OPTIONAL MATCH (p:Post)<-[w:RESPONSE]-(p2:Post)" +
                "RETURN u.username, p.content, p.creationDate, p1.name, count(w) as tot_replies ORDER BY p.creationDate";
        ArrayList<Object> res = getWithFilter(query, parameters("name", p));
        List<Pair<Post,Integer>> return_list = new ArrayList<>();
        for(Object o: res){
            Record d = (Record) o;
            Integer tot_replies = new Integer(d.get("tot_replies").asInt());
            Post post = new Post(d.get("u.username").asString(), d.get("p.content").asString(), d.get("p.creationDate").asLocalDateTime(), d.get("p1.name").asString());
            Pair<Post, Integer> postIntegerPair = new Pair<>(post, tot_replies);
            return_list.add(postIntegerPair);
        }
        return return_list;
    }

    public List<Pair<Post, Integer>> getPostsByPost(Post p){
        //TODO implementation
        return null;
    }

}
