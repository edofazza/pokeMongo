package it.unipi.dii.lsmsd.pokeMongo.security;

import com.google.common.annotations.VisibleForTesting;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncryptor {

    /**
     *
     * @param plainPassword the password inserted by the user (without encryption)
     * @return a string containing the encrypted password
     */
    @VisibleForTesting
    public static String encryptPassword(String plainPassword){
        String s = "randomSalt";
        String encryptedPassword = cipher(plainPassword, s);
        Logger.vlog("ENCRYPTION | encrypt-pw: " + encryptedPassword);
        return encryptedPassword;
    }

    /**
     *
     * @param pwd password inserted by the user
     * @param salt a salt we added for the encryption
     * @return a string that is the digested password
     */
    public static String cipher(String pwd, String salt) {
        return DigestUtils.sha256Hex(pwd+salt);
    }
}
