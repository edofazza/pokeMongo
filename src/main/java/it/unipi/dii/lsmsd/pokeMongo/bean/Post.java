package it.unipi.dii.lsmsd.pokeMongo.bean;

import java.util.Date;

public class Post {
    public String authorUsername;
    public String content;
    public Date publishDate;
    public String pokemonName;

    public String getAuthorUsername() {
        return authorUsername;
    }

    public String getContent() {
        return content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getPokemonName() {
        return pokemonName;
    }
}
