package bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class Analytic {
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
        this(null, 0, 0, null);
    }

    public void setCountry(Map<String, Long> logins) {
        int i=0;
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
}

class CountryData{
    private String name;
    private long lastLogins;

    CountryData(String name, long lastLogins){
        this.lastLogins=lastLogins;
        this.name=name;
    }
}
