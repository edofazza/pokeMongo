package it.unipi.dii.lsmsd.pokeMongo.security;

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncryptor {

    @VisibleForTesting
    public static String encryptPassword(String plainPassword){
        String s = "randomSalt";
        String encryptedPassword = cipher(plainPassword, s);
        return encryptedPassword;
    }

    public static String cipher(String pwd, String salt) {
        return DigestUtils.sha256Hex(pwd+salt);
    }
}
