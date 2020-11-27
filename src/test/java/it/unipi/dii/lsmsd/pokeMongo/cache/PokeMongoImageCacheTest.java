package it.unipi.dii.lsmsd.pokeMongo.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokeMongoImageCacheTest {
    private PokeMongoImageCache pkic;

    @BeforeEach
    public void init(){
        pkic = PokeMongoImageCache.getInstance();
    }

    @Test
    public void WHEN_getInstance_invoked_twice_THEN_same_instance_returned(){
        PokeMongoImageCache pkic2 = PokeMongoImageCache.getInstance();
        Assertions.assertEquals(pkic, pkic2);
    }
}
