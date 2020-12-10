package analytic;

import java.util.Map;

public interface Analyzer {
    int getTodayLogin();

    int getUserNumber();

    Map<String, Integer> getUserNumberByCountry();
}
