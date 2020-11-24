package it.unipi.dii.lsmsd.pokeMongo.DbPopulators;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerOnMongoDb;

import java.io.*;
import java.util.*;

public class UserPopulator {
    private static String pathNames="names.txt";
    private static String pathSurnames="surnames.txt";
    private static String pathCountries="countries.txt";

    public static void main(String[] args){
        ArrayList<Object> l = new ArrayList<>();
        String name, surname, country, email, username, password;
        Boolean admin=false;
        Date birthday;
        for(int i=0; i<(250*1000); i++){
            try(RandomAccessFile nameFile = new RandomAccessFile(pathNames, "r");
                RandomAccessFile surnamesFile = new RandomAccessFile(pathSurnames, "r");
                RandomAccessFile countriesFile = new RandomAccessFile(pathCountries, "r"))
            {
                nameFile.readLine();
                name = nameFile.readLine();
                surnamesFile.readLine();
                surname = surnamesFile.readLine();
                countriesFile.readLine();
                country=countriesFile.readLine();
                email = name + "." + surname + "@lsmdb.unipi.it";
                username=name + "_" + surname;
                password = name + surname + "000";
                birthday = new Date(new Date().getTime() - (long)(Math.random()*1600000000000L));
                User u = new User(admin, surname, name, username, password, email, birthday, country);
                l.add(u);
            }
            catch(Exception ioe){i--; continue;}
            if(i%20 == 0) {
                UserManagerOnMongoDb managerOnMongoDb = new UserManagerOnMongoDb();
                managerOnMongoDb.insert(l);
                l.clear();
            }
        }
    }
}
