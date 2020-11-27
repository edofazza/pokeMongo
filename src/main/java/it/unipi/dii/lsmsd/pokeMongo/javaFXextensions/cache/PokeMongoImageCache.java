package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import javafx.scene.image.Image;
import com.github.benmanes.caffeine.cache.Cache;
import java.util.concurrent.TimeUnit;

public class PokeMongoImageCache implements PokeMongoCache {
    //Singleton
    private PokeMongoImageCache instance;
    private Cache<String, Image> cache;

    public PokeMongoImageCache getInstance() {
        if (instance == null) {
            instance = new PokeMongoImageCache();
        }
        return instance;
    }

    public void initializeCache(){
        cache = Caffeine.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES) //After this time without read/write the resource is deallocated
                .maximumSize(200) //In numero di immagini
                .recordStats() //Should be commented at release stage, now it could be useful
                .build();
    }

    public Image getDataIfPresent(String url){
        Image image = cache.getIfPresent(url);
        if(image == null){
            image = new Image(url);
            cache.put(url, image);
        }
        return image;
    }
}