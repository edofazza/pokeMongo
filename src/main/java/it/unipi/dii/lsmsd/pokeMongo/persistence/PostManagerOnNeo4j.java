package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.Post;

import java.util.List;

public class PostManagerOnNeo4j extends Neo4jDbDatabase implements PostManager{
    public boolean insertPost(Post p){
        //TODO implementation
        return true;
    }

    public boolean deletePost(Post p){
        //TODO implementation
        return true;
    }

    public boolean modifyPost(Post p_old, Post p_new){
        //TODO implementation
        return true;
    }


    public boolean existPost(Post p){
        //TODO implementation
        return true;
    }

    public List<Post> getPostsByPokemon(Pokemon p){
        //TODO implementation
        return null;
    }

}
