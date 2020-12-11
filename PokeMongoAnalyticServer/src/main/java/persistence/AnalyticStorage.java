package persistence;

import java.util.*;

public interface AnalyticStorage {

    void setLastLogin(long counted);

    void setUserNumber(long counted);

    void setLastLoginsByCountry(Map<String, Long> counted);
}
