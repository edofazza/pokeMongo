package it.unipi.dii.lsmsd.pokeMongo.CipherCodeGenerator;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;

import java.util.ArrayList;

public class CipherFriendsGenerator {
    public static void main(String[] args) {
        String s = new String();

        // TAKE ALL THE USERS
        UserManager userManager = UserManagerFactory.buildManager();
        ArrayList<User> allUsers = userManager.getEveryUser();

        for (User u: allUsers) {
            if (u.isAdmin())
                continue;

            int index;
            if (allUsers.size() > 15)
                index = (int) Math.round(Math.random() * 15);
            else
                index = (int) Math.round(Math.random() * allUsers.size());

            for (int i = 0; i < index; i++) {
                int indexUser = (int) Math.round(Math.random() * allUsers.size());

                if (allUsers.get(indexUser).isAdmin())
                    continue;

                String to = allUsers.get(indexUser).getUsername();
                if(!u.getUsername().equals(allUsers.get(indexUser).getUsername()))
                    s += "MATCH (from:User) WHERE from.username = \"" + u.getUsername() + "\" MATCH (to:User) " +
                            " WHERE to.username = " + to + " MERGE (from)-[:FOLLOW]->(to)\n";
            }

        }

        System.out.println(s);
    }

}
