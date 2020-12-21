package analytic;

import java.util.Map;

public interface Analyzer {
    /**
     * Counts number of users logged in the last day
     * @return result of counting
     */
    long getTodayLogin();

    /**
     * Counts the total number of users registered at the service until the last past day
     * @return result of counting
     */
    long getUserNumber();


    /**
     * Counts the number of users that logged in the last day, grouped by country
     * @return a data structure that maps each country with the relative number of logged users
     */
    Map<String, Long> getLastLoginsByCountry();
}
