package it.unipi.dii.lsmsd.pokeMongo.bean;

import java.time.LocalDateTime;
import java.util.Date;

public class Post {
    public String authorUsername;
    public String content;
    public LocalDateTime publishDate;
    public String pokemonName;

    public Post(String authorUsername, String content, LocalDateTime publishDate, String pokemonName){
        this.authorUsername = authorUsername;
        this.content = content;
        this.publishDate = publishDate;
        this.pokemonName = pokemonName;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public String getPokemonName() {
        return pokemonName;
    }
}
