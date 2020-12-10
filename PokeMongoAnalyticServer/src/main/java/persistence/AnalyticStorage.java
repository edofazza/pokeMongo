package persistence;

import java.util.*;

public interface AnalyticStorage {
    long[] getLastLogins();

    long[] getUserNumber();

    List<Map<String, Long>> getUserNumberByCountry();

    void setLastLogin(long counted);

    void setUserNumber(long counted);

    void setUserNumberByCountry(Map<String, Long> counted);
}
