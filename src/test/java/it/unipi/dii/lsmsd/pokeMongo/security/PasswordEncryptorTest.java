package it.unipi.dii.lsmsd.pokeMongo.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordEncryptorTest {
    private PasswordEncryptor passwordEncryptor;

    @BeforeEach
    public void init(){
        passwordEncryptor = new PasswordEncryptor();
    }

    @Test
    public void WHEN_encrypt_password_invoked_twice_with_same_input_THEN_same_output_returned(){
        String out1 = PasswordEncryptor.encryptPassword("prova1");
        String out2 = PasswordEncryptor.encryptPassword("prova1");
        Assertions.assertEquals(out1, out2);
    }

    @Test
    public void WHEN_encrypt_password_invoked_with_empty_string_THEN_non_empty_output_returned(){
        String out = PasswordEncryptor.encryptPassword("");
        Assertions.assertNotEquals(out, "");
    }


}
