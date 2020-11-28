package it.unipi.dii.lsmsd.pokeMongo.utils;


import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FormValidatorPokeMongoTest {
    private FormValidatorPokeMongo formValidatorPokeMongo;

    @BeforeEach
    public void init(){
        formValidatorPokeMongo = new FormValidatorPokeMongo();
    }

    @Test
    public void WHEN_isPersonNoun_invoked_with_empty_string_THEN_false_returned(){
        Assertions.assertFalse(formValidatorPokeMongo.isPersonNoun(""));
    }

    @Test
    public void WHEN_handleName_invoked_with_null_params_THEN_NullPointerException_thrown(){
        Assertions.assertThrows(NullPointerException.class, ()->{
            formValidatorPokeMongo.handleName(null, null);
        });
    }

    @Test
    public void WHEN_isValidEmail_invoked_with_empty_string_THEN_false_returned(){
        Assertions.assertFalse(formValidatorPokeMongo.isValidEmail(""));
    }

    @Test
    public void WHEN_handleEmail_invoked_with_null_params_THEN_NullPointerException_thrown(){
        Assertions.assertThrows(NullPointerException.class, ()->{
            formValidatorPokeMongo.handleEmail(null, null);
        });
    }

    @Test
    public void WHEN_isValidPassword_invoked_with_empty_string_THEN_false_returned(){
        Assertions.assertFalse(formValidatorPokeMongo.isValidPassword(""));
    }

    @Test
    public void WHEN_handlePassword_invoked_with_null_params_THEN_NullPointerException_thrown(){
        Assertions.assertThrows(NullPointerException.class, ()->{
            formValidatorPokeMongo.handlePassword(null, null);
        });
    }

    @Test
    public void WHEN_handleConfirmField_invoked_with_null_params_THEN_NullPointerException_thrown(){
        Assertions.assertThrows(NullPointerException.class, ()->{
            formValidatorPokeMongo.handleConfirmField(null, null, null);
        });
    }

    @Test
    public void WHEN_handleBirthday_invoked_with_null_params_THEN_NullPointerException_thrown(){
        Assertions.assertThrows(NullPointerException.class, ()->{
            formValidatorPokeMongo.handleBirthday(null, null);
        });
    }
}
