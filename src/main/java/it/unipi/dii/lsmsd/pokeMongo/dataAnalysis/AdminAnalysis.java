package it.unipi.dii.lsmsd.pokeMongo.dataAnalysis;

import java.util.List;
import java.util.Map;

public interface AdminAnalysis {
    /**
     * Gets a list of the number of users logged in the last x days, where x is configured
     * @return a list with the described information
     */
    List<Long> getLastLogins();

    /**
     * Gets a list of the total number of registered users in the last x days, where x is configured
     * @return a list with the described information
     */
    List<Long> getUserNumber();

    /**
     * Gets a list of the number of users logged in the last x days grouped by country, where x is configured
     * @return a list in which each elements correspond to a different day (sequential order).
     * Each element of the list maps a country with the corresponding number of logins
     */
    List<Map<String, Long>> getLastLoginsByCountry();
}
