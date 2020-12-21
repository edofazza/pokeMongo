package it.unipi.dii.lsmsd.pokeMongo.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class User {
    private boolean admin;
    private String username;
    private String email;
    private String password;
    private String surname;
    private String name;
    private String birthDay;
    private String country;
    private String teamName;
    private String lastLogin;
    private int dailyPokeball;

    private Pokemon[] team;

    private double points = 0;

    public User(boolean admin, String surname, String name, String username, String password, String email,
                        Date birthDay, String country){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        this.admin=admin;
        this.surname=surname;
        this.name=name;
        this.username=username;
        this.password=password;
        this.email=email;
        this.birthDay=sdf.format(birthDay);
        this.country=country;
        this.teamName="Team name";
        this.lastLogin=sdf.format(new Date());
        this.dailyPokeball=10;
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
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            Date toReturn = sdf.parse(birthDay);
            return toReturn;
        }catch(ParseException pe){
            return null;
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public Date getLastLogin() {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            Date toReturn = sdf.parse(lastLogin);
            return toReturn;
        }catch(ParseException pe){
            return null;
        }
    }

    public int getDailyPokeball() {
        return dailyPokeball;
    }

    public double getPoints() {
        return points;
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

    public void setPoints(Double points) {
        this.points = points;
    }

    public void resetDailyPokeball() {
        this.dailyPokeball = 10;
    }

    public void decrementDailyPokeball() {
        this.dailyPokeball--;
    }

    public void addTeam(Pokemon[] team) {
        this.team=team;

        resetPoint();
    }

    public Pokemon getFromTeam(int i) {
        return team[i];
    }

    public void removeFromTeam(int i) {
        team[i] = null;

        resetPoint();
    }

    public void addToTeam(Pokemon p, int i) {
        team[i] = p;

        resetPoint();
    }


    /**
     * Calculated the points associated to the team
     */
    private void resetPoint() {
        points = 0;
        double times = 1.5;
        boolean noTimes = false;
        ArrayList<String> types = new ArrayList<>();

        for (Pokemon p: team) {
            if (p == null) {
                noTimes = true;
                continue;
            }

            String[] tyArray = p.getTypes();

            if(types.indexOf(tyArray[0]) >= 0)
                noTimes = true;

            if (tyArray.length > 1)
                if (types.indexOf(tyArray[1]) >= 0) {
                    noTimes = true;
                    types.add(tyArray[1]);
                }

            points += (256 - p.getCapture_rate());

            types.add(tyArray[0]);
        }

        if (!noTimes)
            points *= times;
    }

}
