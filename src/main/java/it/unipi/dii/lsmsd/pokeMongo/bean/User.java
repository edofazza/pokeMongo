package it.unipi.dii.lsmsd.pokeMongo.bean;

import it.unipi.dii.lsmsd.pokeMongo.persistence.TeamManagerOnNeo4j;

import java.util.ArrayList;
import java.util.Date;

public class User {
    private boolean admin;
    private String username;
    private String email;
    private String password;
    private String surname;
    private String name;
    private Date birthDay;
    private String country;
    private String teamName;
    private Date lastLogin;
    private int dailyPokeball;

    private Pokemon[] team;

    public User(boolean admin, String surname, String name, String username, String password, String email,
                        Date birthDay, String country){
        this.admin=admin;
        this.surname=surname;
        this.name=name;
        this.username=username;
        this.password=password;
        this.email=email;
        this.birthDay=birthDay;
        this.country=country;
        this.teamName="Team name";
        this.lastLogin=new Date();
        this.dailyPokeball=10;

        //addTeam();
    }

    public User(String username, String password){
        this(false, null, null, username, password, null, null, null);
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getUsername() {
        return  username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname(){
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getTeamName() {
        return teamName;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public int getDailyPokeball() {
        return dailyPokeball;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void resetDailyPokeball() {
        this.dailyPokeball = 10;
    }

    public void decrementDailyPokeball() {
        this.dailyPokeball--;
    }


    public void addTeam() {/*
        if (team == null)
            team = new Pokemon[6];
        else
            return;

        TeamManagerOnNeo4j teamManagerOnNeo4j = new TeamManagerOnNeo4j();
        ArrayList<Object> pokemons = teamManagerOnNeo4j.getWithFilter(this);
        for (Object p: pokemons) {
            Pokemon pokemon = (Pokemon)p;
            team[pokemon.getSlot()] = pokemon;
        }

        for (Pokemon p: team)
            if (p != null)
                System.out.println(p.getName());
    }

    public Pokemon getFromTeam(int i) {
        return team[i];
        */
    }
}
