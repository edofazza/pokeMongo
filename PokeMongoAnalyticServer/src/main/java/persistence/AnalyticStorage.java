package persistence;

import java.util.*;

public interface AnalyticStorage {
    long[] getLastLogins();

    long[] getUserNumber();

    List<Map<String, Long>> getLastLoginsByCountry();

    void setLastLogin(long counted);

    void setUserNumber(long counted);

    void setLastLoginsByCountry(Map<String, Long> counted);
}
