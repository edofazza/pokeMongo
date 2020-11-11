package it.unipi.dii.lsmsd.pokeMongo.userRelated;

public class User {
    private String username;
    private int numberOfPokeball = 10;
    private boolean admin = true;

    public User(String username) {  //TODO: da eliminare quando si mette MongoDB
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int getNumberOfPokeball() {
        return numberOfPokeball;
    }

    public boolean isAdmin() {
        return admin;
    }
}

//TODO: Creare una interfaccia utente e distinguere tra utente admin e utente normale
