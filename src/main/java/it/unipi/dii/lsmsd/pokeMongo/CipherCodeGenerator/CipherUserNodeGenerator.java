package it.unipi.dii.lsmsd.pokeMongo.CipherCodeGenerator;

import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.PokemonManagerFactory;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class CipherUserNodeGenerator {
    public static void main(String[] args) {
        UserManager userManager = UserManagerFactory.buildManager();
        ArrayList<User> users =  userManager.getEveryUser();

        String query = new String();
        String query2;
        int i=0;
        for (User u: users) {
            if (u.isAdmin())
                continue;

            i++;
            String username = u.getUsername();
            String country = u.getCountry();
            query = "CREATE (" + "p" + i + ":User { username: \"" + username + "\", country: \" " + country + "\"})\n";
            query2 = username + ", " + country + "\n";

            //System.out.print("CREATE (" + "p" + i + ":User { username: \"" + username + "\"})\n");

            //int nFile = i/9000;

            try {
                //Files.write(Paths.get("cipherCodeRepo/cipherUserCode/cipherUser"+ nFile + ".txt"), query.getBytes(), StandardOpenOption.APPEND);
                Files.write(Paths.get("cipherCodeRepo/cipherUserCode/cipherUserWhole.csv"), query2.getBytes(), StandardOpenOption.APPEND);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(query);
    }
}
