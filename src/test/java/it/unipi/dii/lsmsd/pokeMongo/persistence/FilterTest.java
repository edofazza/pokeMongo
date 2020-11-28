package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.google.common.annotations.VisibleForTesting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FilterTest {

    @Test
    public void WHEN_InterfaceStringToFilter_invoked_with_different_String_THEN_No_exception_Thrown(){
        Assertions.assertDoesNotThrow(()->{
            Filter.interfaceStringToFilter("prova");
        });
    }

    @Test
    public void WHEN_FilterToInterfaceString_invoked_with_different_Filter_THEN_Exception_Thrown(){
        Assertions.assertThrows(Exception.class, ()->{
            Filter filter = Filter.valueOf("PROVA");
            Filter.FilterToInterfaceString(filter);
        });
    }
}
