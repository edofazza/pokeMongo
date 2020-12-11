package analytic;

import java.util.Map;

public interface Analyzer {
    long getTodayLogin();

    long getUserNumber();

    Map<String, Long> getUserNumberByCountry();

    Map<String, Long> getLastLoginsByCountry();
}
