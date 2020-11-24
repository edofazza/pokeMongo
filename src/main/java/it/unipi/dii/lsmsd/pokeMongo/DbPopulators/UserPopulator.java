package it.unipi.dii.lsmsd.pokeMongo.DbPopulators;

import it.unipi.dii.lsmsd.pokeMongo.bean.User;

import java.util.*;

public class UserPopulator {
    private static String pathNames;
    private static String pathSurnames;
    private static String pathCountries;

    public static void main(String[] args){
        List<User> l = new ArrayList<>();
        for(int i=0; i<20000; i++){
            //generate randomly a user
            //add it to l
            if(i%20 == 0) {
                //make the insertMany
                //empty l
            }
        }
    }
}
