package it.unipi.dii.lsmsd.pokeMongo.dataAnalysis;

import java.util.List;
import java.util.Map;

public interface AdminAnalysis {
    long[] getLastLogins();

    long[] getUserNumber();

    List<Map<String, Long>> getLastLoginsByCountry();
}
