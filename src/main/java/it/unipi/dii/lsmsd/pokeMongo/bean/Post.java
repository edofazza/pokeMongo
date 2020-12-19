package it.unipi.dii.lsmsd.pokeMongo.bean;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post {
    private String authorUsername;
    private String content;
    private LocalDateTime publishDate;
    private String pokemonName;


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

    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formatDateTime = publishDate.format(formatter);
        Logger.vlog(formatDateTime);
        return formatDateTime;
    }
}
