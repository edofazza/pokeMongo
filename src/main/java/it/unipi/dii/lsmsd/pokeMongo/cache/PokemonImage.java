package it.unipi.dii.lsmsd.pokeMongo.cache;

import javafx.scene.image.Image;

public class PokemonImage extends Image {
    public String url;

    PokemonImage(String url){
        super(url);
        this.url = url;
    }

    // standard constructors/getters
    public static PokemonImage get(String url){
        return new PokemonImage(url);
    }
}
