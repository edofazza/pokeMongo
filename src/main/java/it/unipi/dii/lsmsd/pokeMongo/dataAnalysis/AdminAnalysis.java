package it.unipi.dii.lsmsd.pokeMongo.dataAnalysis;

import java.util.List;
import java.util.Map;

public interface AdminAnalysis {
    List<Long> getLastLogins();

    List<Long> getUserNumber();

    List<Map<String, Long>> getLastLoginsByCountry();
}
