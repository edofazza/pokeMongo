package it.unipi.dii.lsmsd.pokeMongo.utils;


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
    public void WHEN_isValidEmail_invoked_with_empty_string_THEN_false_returned(){
        Assertions.assertFalse(formValidatorPokeMongo.isValidEmail(""));
    }

    @Test
    public void WHEN_isValidPassword_invoked_with_empty_string_THEN_false_returned(){
        Assertions.assertFalse(formValidatorPokeMongo.isValidPassword(""));
    }


}
