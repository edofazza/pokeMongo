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


    public boolean insertResponse(Post newPost, Post topic) throws DuplicatePostException{
        if(existResponse(newPost))
            throw new DuplicatePostException();
        String query = "MATCH (u:User) WHERE u.username = $username MATCH (uTopic:User)-[:CREATED]-(pTopic:Post)-[:TOPIC]->(pokTopic:Pokemon) " +
                "WHERE uTopic.username = $username2 and pTopic.creationDate = $date2 and pTopic.content = $content2 and pokTopic.name = $name2" +
                "CREATE (u)-[:CREATED]->(p1:Post{content: $content, creationDate: $date})-[:TOPIC]->(uTopic)";

        return insert(query, parameters("username", newPost.getAuthorUsername(), "content", newPost.getPokemonName(),
                "date", newPost.getPublishDate(), "username2", topic.getAuthorUsername(), "date2", topic.getPublishDate(), "content2", topic.getPublishDate(), "name2", topic.getPokemonName()));
    }



    public boolean deletePost(Post p){
        String query = "MATCH (u:User)-[:CREATED]->(p:Post)-[:TOPIC]->(p1:Pokemon) " +
                " WHERE u.username = $username and p.creationDate = $date and p.content = $content and p1.name = $name " +
                " OPTIONAL MATCH (u1:User)-[:CREATED]->(p1:Post)-[:TOPDETACH DELETE p";

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

    //TODO: non è il massimo ma per adesso lasciamola così
    public boolean existResponse(Post p){
        String query = "MATCH (u:User)-[:CREATED]->(p:Post) " +
                "WHERE u.username = $username and p.creationDate = $date and p.content = $content " +
                "return count(p) as post_count";
        ArrayList<Object> res = getWithFilter(query, parameters("username", p.getAuthorUsername(), "date", p.getPublishDate(), "content", p.getContent()));
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

    public List<Post> getPostsByPost(Post p){
        String query = "MATCH (u:User)-[:CREATED]->(p:Post)-[:TOPIC]->(p1:Post) MATCH (u1:User)-[:CREATED]->(p1:Post)-[:TOPIC]->(pok:Pokemon)"  +
                "WHERE p1.content = $content and p1.creationDate = $date and u1.username = $username and pok.name = $name" +
                "RETURN u.username, p.content, p.creationDate, p1.name ORDER BY p.creationDate";
        ArrayList<Object> res = getWithFilter(query, parameters("content", p.getContent(), "date", p.getPublishDate(), "username", p.getAuthorUsername(), "name", p.getPokemonName()));
        List<Post> return_list = new ArrayList<>();
        for(Object o: res){
            Record d = (Record) o;
            Post post = new Post(d.get("u.username").asString(), d.get("p.content").asString(), d.get("p.creationDate").asLocalDateTime(), d.get("p1.name").asString());
            return_list.add(post);
        }
        return return_list;
    }

}
