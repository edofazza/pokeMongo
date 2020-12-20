package it.unipi.dii.lsmsd.pokeMongo.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UserManagerOnMongoDbTest {

    @Test
    public void WHEN_insert_invoked_with_non_User_parameter_THEN_ClassCastException_thrown(){
        Assertions.assertDoesNotThrow( ()->{
            UserManagerOnMongoDb userManagerOnMongoDb = new UserManagerOnMongoDb();
            Object c = new Character('c');
            userManagerOnMongoDb.insert(c);
        });
    }

    @Test
    public void WHEN_insert_with_arraylist_invoked_with_non_User_parameter_THEN_Exception_not_thrown(){
        Assertions.assertDoesNotThrow(()->{
            UserManagerOnMongoDb userManagerOnMongoDb = new UserManagerOnMongoDb();
            ArrayList<Object> al = new ArrayList<>();
            Object c = new Character('c');
            al.add(c);
            userManagerOnMongoDb.insert(al);
        });
    }

    @Test
    public void WHEN_getWithFilter_invoked_with_non_Bson_parameter_THEN_Exception_not_thrown(){
        Assertions.assertDoesNotThrow( ()->{
            UserManagerOnMongoDb userManagerOnMongoDb = new UserManagerOnMongoDb();
            Object c = new Character('c');
            userManagerOnMongoDb.getWithFilter(c);
        });
    }

    @Test
    public void WHEN_update_invoked_with_non_Bson_parameter_newvalue_THEN_ClassCastException_thrown(){
        Assertions.assertDoesNotThrow( ()->{
            UserManagerOnMongoDb userManagerOnMongoDb = new UserManagerOnMongoDb();
            Object c = new Character('c');
            userManagerOnMongoDb.getWithFilter(c);
        });
    }
}
