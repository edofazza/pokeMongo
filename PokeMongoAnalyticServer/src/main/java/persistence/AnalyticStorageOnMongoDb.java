package persistence;

import java.util.List;
import java.util.Map;

public class AnalyticStorageOnMongoDb implements AnalyticStorage{
    @Override
    public long[] getLastLogins() {
        return new long[0];
    }

    @Override
    public long[] getUserNumber() {
        return new long[0];
    }

    @Override
    public List<Map<String, Long>> getUserNumberByCountry() {
        return null;
    }

    @Override
    public List<Map<String, Long>> getLastLoginsByCountry() {
        return null;
    }

    @Override
    public void setLastLogin(long counted) {

    }

    @Override
    public void setUserNumber(long counted) {

    }

    @Override
    public void setUserNumberByCountry(Map<String, Long> counted) {

    }

    @Override
    public void setLastLoginsByCountry(Map<String, Long> counted) {

    }
}
