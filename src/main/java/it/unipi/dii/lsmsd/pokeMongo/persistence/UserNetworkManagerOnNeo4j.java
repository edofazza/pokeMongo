package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.exceptions.DuplicateUserException;
import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

class UserNetworkManagerOnNeo4j extends Neo4jDbDatabase implements UserNetworkManager{

    @Override
    public boolean deleteUser(User u){
        return deleteUser(u.getUsername());
    }

    @Override
    public boolean deleteUser(String username){
        String query = "MATCH (u:User) WHERE u.username = $username OPTIONAL MATCH (u)-[:CREATED]->(p:Post) " +
                "OPTIONAL MATCH (p)<-[:TOPIC]-(p1:Post)" +
                "DETACH DELETE u, p, p1";
        return remove(query, parameters("username", username));
    }

    @Override
    public boolean addUser(User u) throws DuplicateUserException{
        if(userAlreadyExists(u))
            throw new DuplicateUserException();

        String query = "MERGE (u:User { username: $username, country: $country})";
        return insert(query, parameters("username", u.getUsername(), "country", u.getCountry()));
    }

    private boolean userAlreadyExists(User u){
        String controlQuery = "MATCH (p:User) WHERE p.username = $username RETURN count(p) as user_count";
        ArrayList<Object> res = getWithFilter(controlQuery, parameters("username",u.getUsername()));
        Record d = (Record)res.get(0);
        return (d.get("user_count").asInt() > 0);
    }

    @Override
    public boolean addFollow(User from, User to){
        String query = "MATCH (from:User) WHERE from.username = $username " +
                "MATCH (to:User) WHERE to.username = $username2 MERGE (from)-[:FOLLOW]->(to)";
        return insert(query, parameters("username", from.getUsername(), "username2", to.getUsername()));
    }

    @Override
    public boolean removeFollow(User from, User to){
        String query = "MATCH (from:User)-[w:FOLLOW]->(to:User) WHERE to.username = $username and from.username = $username2 DELETE w";
        return remove(query, parameters("username", to.getUsername(), "username2", from.getUsername()));
    }

    @Override
    /**
     * It doesn't create a bean object yet
     * @param target
     * @result List of usernames
     */
    public List<String> getFollowersUsernames(User target){
        List<String> followersUsernames = new ArrayList<String>();
        String query = "MATCH (to:User)-[h:FOLLOW]->(from:User) WHERE from.username = $username RETURN to.username";
        ArrayList<Object> res = getWithFilter(query, parameters("username", target.getUsername()));
        for(Object o: res){
            Record r =(Record)o;
            String username = r.get("to.username").asString();
            followersUsernames.add(username);
        }
        return followersUsernames;
    }

    @Override
    public List<String> getFollowing(User target){
        List<String> following = new ArrayList<>();
        String query = "MATCH (to:User)<-[h:FOLLOW]-(from:User) WHERE from.username = $username RETURN to.username";
        ArrayList<Object> res = getWithFilter(query, parameters("username", target.getUsername()));
        for(Object o: res){
            Record r =(Record)o;
            String username = r.get("to.username").asString();
            following.add(username);
        }
        return following;
    }

    @Override
    public boolean updateCountry(User target, String newCountry) {
        String query = "MATCH (n:User) WHERE n.username = $username " +
                "SET n.country = $country";
        return update(query, parameters("username", target.getUsername(), "country", newCountry));
    }

    @Override
    public boolean addLikeToPokemon(User target, String p){
        String query = "MATCH (from:User) WHERE from.username = $username " +
                "MATCH (to:Pokemon) WHERE to.name = $name2 MERGE (from)-[:LIKES]->(to)";
        return insert(query, parameters("username", target.getUsername(), "name2", p));
    }

    @Override
    public boolean removeLikeToPokemon(User target, String p){
        String query = "MATCH (from:User)-[w:LIKES]->(to:Pokemon) WHERE from.username = $username and to.name = $name2 DELETE w";
        return remove(query, parameters("username", target.getUsername(), "name2", p));
    }

    @Override
    public boolean isFavorite(String from, String to) {
        String query = "MATCH (from:User)-[:LIKES]->(to:Pokemon) WHERE from.username = $from AND to.name = $to RETURN count(*) AS like";
        ArrayList<Object> res = getWithFilter(query, parameters("from", from, "to", to));

        Record r = (Record)res.get(0);
        return r.get("like").asInt() == 1;
    }

    @Override
    public List<String> getLikedPokemonNames(User u){
        List<String> liked = new ArrayList<>();
        String query = "MATCH (to:Pokemon)<-[h:LIKES]-(from:User) WHERE from.username = $username RETURN to.name";
        ArrayList<Object> res = getWithFilter(query, parameters("username", u.getUsername()));
        for(Object o: res){
            Record r =(Record)o;
            String username = r.get("to.name").asString();
            liked.add(username);
        }
        return liked;
    }
    
    @Override
    public List<String> getSuggestedUser(User u) {
        List<String> usernameList = new ArrayList<>();
        String query = "MATCH (u:User)-[:FOLLOW]->(u1:User)-[:FOLLOW]->(u2:User) where NOT (u)-[:FOLLOW]->(u2) and u2 <> u and u.username = $username return u2.username LIMIT 20";
        ArrayList<Object> res = getWithFilter(query, parameters("username", u.getUsername()));
        for(Object o: res){
            Record r =(Record)o;
            String username = r.get("u2.username").asString();
            usernameList.add(username);
        }
        return usernameList;
    }


    @Override
    public List<String> getSuggestedUserByFavoritesPokemon(User u) {
        List<String> usernameList = new ArrayList<>();
        String query = "MATCH (p:Pokemon)<-[:LIKES]-(u:User)-[:FOLLOW]->(u1:User)-[:FOLLOW]->(u2:User)-[:LIKES]->(p2:Pokemon) where NOT (u)-[:FOLLOW]->(u2) and u2 <> u and u.username = $username and p2 = p return u2.username LIMIT 20";
        ArrayList<Object> res = getWithFilter(query, parameters("username", u.getUsername()));
        for(Object o: res){
            Record r =(Record)o;
            String username = r.get("u2.username").asString();
            usernameList.add(username);
        }
        return usernameList;
    }


    @Override
    public List<String> getUserBySearch(String pattern) {
        List<String> usernameList = new ArrayList<>();
        String query = "MATCH (u:User) WHERE u.username STARTS WITH $pattern RETURN u.username LIMIT 20";
        ArrayList<Object> res = getWithFilter(query, parameters("pattern", pattern));
        for(Object o: res){
            Record r =(Record)o;
            String username = r.get("u.username").asString();
            usernameList.add(username);
        }
        return usernameList;

    }

    @Override
    public boolean isFollowing(String from, String to) {
        String query = "MATCH (from:User)-[:FOLLOW]->(to:User) WHERE from.username = $from AND to.username = $to RETURN count(*) AS follow";
        ArrayList<Object> res = getWithFilter(query, parameters("from", from, "to", to));

        Record r = (Record)res.get(0);
        return r.get("follow").asInt() == 1;
    }
}
