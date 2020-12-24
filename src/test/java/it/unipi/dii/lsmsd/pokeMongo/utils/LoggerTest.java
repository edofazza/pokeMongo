package it.unipi.dii.lsmsd.pokeMongo.utils;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LoggerTest {

    @Test
    public void WHEN_warning_invoked_with_null_params_THEN_NullPointerException_thrown() {
        Assertions.assertDoesNotThrow( ()->{
            Logger.warning(null);
        });
    }
}
