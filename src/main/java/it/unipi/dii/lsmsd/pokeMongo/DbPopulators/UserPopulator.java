package it.unipi.dii.lsmsd.pokeMongo.DbPopulators;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserPopulator {
    private static String pathNames;
    private static String pathSurnames;
    private static String pathCountries;

    public static void main(String[] args){
        List<User> l = new ArrayList<>();
        String name, surname, country, email, username, password;
        Boolean admin=false;
        Date birthday;
        for(int i=0; i<20000; i++){
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
                birthday = new Date((long)new Date().getTime() - (long)Math.random()*3*1000*1000*1000);
            }
            catch(Exception ioe){i--; continue;}
            if(i%20 == 0) {
                //make the insertMany
                //empty l
            }
        }
    }
}
