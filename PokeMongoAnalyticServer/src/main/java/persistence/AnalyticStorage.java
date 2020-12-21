package persistence;

import java.util.*;

public interface AnalyticStorage {

    /**
     * Writes into the Analytic Storage the number of logged users in the last day
     * @param counted number of users that logged in the last day
     */
    void setLastLogin(long counted);

    /**
     * Writes into the Analytic Storage the total number of registered users until the last day
     * @param counted number of registered users until the last day
     */
    void setUserNumber(long counted);

    /**
     * Writes into the Analytic Storage the number of logged users in the last day per country
     * @param counted maps each country with the number of users that logged in the last day
     */
    void setLastLoginsByCountry(Map<String, Long> counted);
}
