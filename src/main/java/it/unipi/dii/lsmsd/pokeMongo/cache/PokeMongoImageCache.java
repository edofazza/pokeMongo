package it.unipi.dii.lsmsd.pokeMongo.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.image.Image;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class PokeMongoImageCache implements PokeMongoCache {
    //Singleton
    private static PokeMongoImageCache instance;
    private AsyncLoadingCache<String, Image> cache;

    /**
     * Singleton
     * @return the private PokeMongoImageCache.
     */
    public static PokeMongoImageCache getInstance() {
        if (instance == null) {
            instance = new PokeMongoImageCache();
        }
        return instance;
    }

    PokeMongoImageCache(){
        cache = Caffeine.newBuilder()
                .expireAfterAccess(10, TimeUnit.MINUTES) //After this time without read/write the resource is deallocated
                .maximumSize(2000) // Max number of images stored
                //.recordStats()
                .buildAsync(k -> PokemonImage.get(k));
    }

    public CompletableFuture<Image> getDataIfPresent(String url){
        Logger.vlog("Attemp to get image at: " + url);
        return cache.get(url);
    }
}