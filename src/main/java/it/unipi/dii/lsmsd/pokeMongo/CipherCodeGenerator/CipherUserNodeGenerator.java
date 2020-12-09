package it.unipi.dii.lsmsd.pokeMongo.CipherCodeGenerator;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;

import java.util.ArrayList;

public class CipherUserNodeGenerator {
    public static void main(String[] args) {
        UserManager userManager = UserManagerFactory.buildManager();
        ArrayList<User> users =  userManager.getEveryUser();

        String query = new String();

        int i=0;
        for (User u: users) {
            i++;
            String username = u.getUsername();
            query += "CREATE (" + "p" + i + ":User { username: \"" + username + "\"})\n";
        }
        System.out.println(query);
    }
}
