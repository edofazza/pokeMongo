package it.unipi.dii.lsmsd.pokeMongo.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Analytic implements Serializable{
    private String date;
    private long lastLogins;
    private long userCounter;
    private CountryData[] country;

    public Analytic(Date date, long lastLogins, long userCounter, Map<String, Long> logins){
        this.date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(date);
        this.lastLogins=lastLogins;
        this.userCounter = userCounter;
        country = new CountryData[logins.size()];
        int i=0;
        for(Map.Entry<String, Long> el : logins.entrySet()) {
            country[i] = new CountryData(el.getKey(), el.getValue());
            i++;
        }
    }

    public Analytic(){
        this(new Date(), 0, 0, new HashMap<>());
    }

    public void setCountry(Map<String, Long> logins) {
        int i=0;
        country = new CountryData[logins.size()];
        for(Map.Entry<String, Long> el : logins.entrySet()) {
            country[i] = new CountryData(el.getKey(), el.getValue());
            i++;
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLastLogins(long lastLogins) {
        this.lastLogins = lastLogins;
    }

    public void setUserCounter(long userCounter) {
        this.userCounter = userCounter;
    }

    public String getDate() {
        return date;
    }

    public long getLastLogins() {
        return lastLogins;
    }

    public long getUserCounter() {
        return userCounter;
    }

    public CountryData[] getCountry() {
        return country;
    }
}


