package persistence;

import java.text.SimpleDateFormat;
import java.util.*;

class AnalyticStorageOnLevelDb implements AnalyticStorage{
    private final int numDays;
    private static AnalyticStorageOnLevelDb instance;

    static AnalyticStorageOnLevelDb getInstance(int numDays){
        if(instance==null){
            if(numDays<0 || numDays>365)
                instance = new AnalyticStorageOnLevelDb();
            else
                instance = new AnalyticStorageOnLevelDb(numDays);
        }
        return instance;

    }

    AnalyticStorageOnLevelDb(int days){
        this.numDays=days;
    }

    AnalyticStorageOnLevelDb(){
        this(30);
    }

    private String generateKey(String dataType, Date reference){
        if(!dataType.equals("LastLogins") && !dataType.equals("UserNumber"))
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return  sdf.format(reference) + ":" + dataType;
    }

    private String generateKey(String dataType, Date reference, String country){
        if(!dataType.equals("UserNumber"))
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return sdf.format(reference) + ":" + dataType + ":" +country;
    }

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
        return new ArrayList<>();
    }
    @Override
    public void setLastLogin(long howMany){

    }

    @Override
    public void setUserNumber(long counted) {

    }

    @Override
    public void setUserNumberByCountry(Map<String, Long> counted) {

    }
}
