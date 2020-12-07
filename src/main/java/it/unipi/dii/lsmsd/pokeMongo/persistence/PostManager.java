package it.unipi.dii.lsmsd.pokeMongo.persistence;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.Post;

import java.util.List;

public interface PostManager {

    /**
     * Store a new Post in the system
     * @param p  Post to be stored permanently
     * @return true if the Post was correctly inserted
     */
    boolean insertPost(Post p);

    /**
     * Delete a Post from the system
     * @param p  Post to be deleted
     * @return true if the Post was correctly removed
     */
    boolean deletePost(Post p);

    /**
     * Modify a previous post by replacing it with a new one
     * @param p_old  The old Post object
     * @param p_new  The new Post object
     * @return true if the Post was correctly replaced
     */
    boolean modifyPost(Post p_old, Post p_new);


    /**
     * Check if a Post already exists in the system
     * @param p  Post to be checked his existence
     * @return true if the post exists
     */
    boolean existPost(Post p);


    /**
     * Obtain all Posts dedicated to a specific pokemon
     * @param p Pokemon which represents the topic of the Posts to be searched
     * @return List of Posts referred to p
     */
    List<Post> getPostsByPokemon(Pokemon p);
}
