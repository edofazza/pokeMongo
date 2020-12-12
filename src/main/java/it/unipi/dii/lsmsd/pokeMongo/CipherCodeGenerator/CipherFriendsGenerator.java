package it.unipi.dii.lsmsd.pokeMongo.CipherCodeGenerator;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class CipherFriendsGenerator {
    public static void main(String[] args) {
        String s = new String();

        // TAKE ALL THE USERS
        UserManager userManager = UserManagerFactory.buildManager();
        ArrayList<User> allUsers = userManager.getEveryUser();

        int indice = 0; // per il file
        for (User u: allUsers) {
            if (u.isAdmin())
                continue;

            int index;
            if (allUsers.size() > 3)
                index = (int) Math.round(Math.random() * 3);
            else
                index = (int) Math.round(Math.random() * (allUsers.size() - 1));

            for (int i = 0; i < index; i++) {
                int indexUser = (int) Math.round(Math.random() * (allUsers.size() - 1));

                if (allUsers.get(indexUser).isAdmin())
                    continue;

                String to = allUsers.get(indexUser).getUsername();
                if(!u.getUsername().equals(allUsers.get(indexUser).getUsername())) {
                    indice++;
                    int nFile = indice/9000;

                    if (nFile > 76)
                        nFile = 76;

                    String query = "MATCH (from:User) WHERE from.username = \"" + u.getUsername() + "\" MATCH (to:User) " +
                            " WHERE to.username = \"" + to + "\" MERGE (from)-[:FOLLOW]->(to)\n";

                    try {
                        Files.write(Paths.get("cipherCodeRepo/cipherFriends/cipherUser"+ nFile + ".txt"), query.getBytes(), StandardOpenOption.APPEND);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

}
