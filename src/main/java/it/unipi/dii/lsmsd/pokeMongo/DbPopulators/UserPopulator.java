package it.unipi.dii.lsmsd.pokeMongo.DbPopulators;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerOnMongoDb;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManager;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserNetworkManagerFactory;

import java.io.*;
import java.util.*;

public class UserPopulator {
    private final static String pathNames="names.txt";
    private static long namesLength;
    private final static String pathSurnames="surnames.txt";
    private static long surnamesLength;
    private final static String pathCountries="countries.txt";
    private static long countriesLength;

    public static void main(String[] args){
        ArrayList<Object> l = new ArrayList<>();
        String name, surname, country, email, username, password;
        Boolean admin=false;
        Date birthday;
        try {
            RandomAccessFile nameFile = new RandomAccessFile(pathNames, "r");
            RandomAccessFile surnamesFile = new RandomAccessFile(pathSurnames, "r");
            RandomAccessFile countriesFile = new RandomAccessFile(pathCountries, "r");
            namesLength = nameFile.length();
            surnamesLength=surnamesFile.length();
            countriesLength=countriesFile.length();

            for (int i = 0; i < (250 * 1000); i++) {
                nameFile.seek((long)(Math.random()*namesLength));
                nameFile.readLine();
                name = nameFile.readLine();

                surnamesFile.seek((long)(Math.random()*surnamesLength));
                surnamesFile.readLine();
                surname = surnamesFile.readLine();
                countriesFile.readLine();

                countriesFile.seek((long)(Math.random()*countriesLength));
                countriesFile.readLine();
                country = countriesFile.readLine();

                email = name + "." + surname + "@lsmdb.unipi.it";
                username = name + "_" + surname;
                password = name + surname + "000";
                birthday = new Date(new Date().getTime() - (long) (Math.random() * 1500000000000D + 100000000000D));
                User u = new User(admin, surname, name, username, password, email, birthday, country);
                l.add(u);

                if (i % 20 == 0) {
                    UserManagerOnMongoDb managerOnMongoDb = new UserManagerOnMongoDb();
                    managerOnMongoDb.insert(l);
                    l.clear();
                }

                UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
                userNetworkManager.addUser(u);
            }
        }catch (Exception e){
            System.out.println("Error");
        }

    }
}
