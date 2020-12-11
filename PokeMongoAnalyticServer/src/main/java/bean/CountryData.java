package bean;

import java.io.Serializable;

public class CountryData implements Serializable {
    private String name;
    private long lastLogins;

    CountryData(String name, long lastLogins) {
        this.lastLogins = lastLogins;
        this.name = name;
    }

    public long getLastLogins() {
        return lastLogins;
    }

    public String getName() {
        return name;
    }
}
