package it.unipi.dii.lsmsd.pokeMongo.security;

import com.google.common.annotations.VisibleForTesting;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncryptor {

    @VisibleForTesting
    public static String encryptPassword(String plainPassword){
        String s = "randomSalt";
        String encryptedPassword = cipher(plainPassword, s);
        Logger.vlog("ENCRYPTION | encrypt-pw: " + encryptedPassword);
        return encryptedPassword;
    }

    public static String cipher(String pwd, String salt) {
        return DigestUtils.sha256Hex(pwd+salt);
    }
}
