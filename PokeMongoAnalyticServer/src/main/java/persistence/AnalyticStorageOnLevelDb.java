package persistence;

import java.text.SimpleDateFormat;
import java.util.*;

class AnalyticStorageOnLevelDb extends LevelDbDatabase implements AnalyticStorage{
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

    private void removeOld(Calendar threshold){
        String litThreshold = new SimpleDateFormat("yyyy-MM-dd").format(threshold.getTime());
        removeLower(litThreshold);
    }

    private long[] getData(String datatype){
        long[] result = new long[numDays];
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        Calendar timeAgo = Calendar.getInstance();
        timeAgo.setTime(new Date());
        timeAgo.add(Calendar.DAY_OF_MONTH, -1*numDays);
        removeOld(timeAgo);
        String dataType = datatype;
        for(int i=0; i<numDays; i++){
            String key=generateKey(dataType, timeAgo.getTime());
            result[i] = (long)getWithFilter(key);
            timeAgo.add(Calendar.DAY_OF_MONTH, 1);
        }
        return result;
    }

    @Override
    public void setLastLogin(long howMany){

    }

    @Override
    public void setUserNumber(long counted) {

    }


    @Override
    public void setLastLoginsByCountry(Map<String, Long> counted) {

    }
}
