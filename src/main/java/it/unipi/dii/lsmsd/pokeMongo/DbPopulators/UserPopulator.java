package it.unipi.dii.lsmsd.pokeMongo.DbPopulators;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.persistence.UserManagerOnMongoDb;

import java.io.*;
import java.util.*;

public class UserPopulator {
    private final static String pathNames="txt/names.txt";
    private static long namesLength;
    private final static String pathSurnames="txt/surnames.txt";
    private static long surnamesLength;
    private final static String pathCountries="tzt/countries.txt";
    private static long countriesLength;

    public static void main(String[] args){
        ArrayList<Object> l = new ArrayList<>();
        //UserNetworkManager userNetworkManager = UserNetworkManagerFactory.buildManager();
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

            for (int i = 0; i < (150 * 1000); i++) {
                try {
                    nameFile.seek((long) (Math.random() * namesLength));
                    nameFile.readLine();
                    name = nameFile.readLine();

                    surnamesFile.seek((long) (Math.random() * surnamesLength));
                    surnamesFile.readLine();
                    surname = surnamesFile.readLine();
                    countriesFile.readLine();

                    countriesFile.seek((long) (Math.random() * countriesLength));
                    countriesFile.readLine();
                    country = countriesFile.readLine();

                    email = name + "." + surname + "@lsmdb.unipi.it";

                    password = name + surname + "000";
                    birthday = new Date(new Date().getTime() - (long) (Math.random() * 1500000000000D + 100000000000D));
                    Calendar c = Calendar.getInstance();
                    c.setTime(birthday);
                    username = name + "_" + surname + c.get(Calendar.YEAR); //use substring
                    User u = new User(admin, surname, name, username, password, email, birthday, country);
                    l.add(u);

                    if (i % 20 == 0) {
                        UserManagerOnMongoDb managerOnMongoDb = new UserManagerOnMongoDb();
                        managerOnMongoDb.insert(l);
                        l.clear();
                    }
                    //userNetworkManager.addUser(u);
                }catch (Exception e){
                    i--;
                    continue;
                }
            }
        }catch (Exception e){
            System.out.println("Error");
        }

    }
}
