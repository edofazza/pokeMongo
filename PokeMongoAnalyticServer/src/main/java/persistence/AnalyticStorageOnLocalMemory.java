package persistence;

import java.util.Map;

class AnalyticStorageOnLocalMemory implements AnalyticStorage{
    private final int numDays;
    private static AnalyticStorageOnLocalMemory instance;
    private static int index;

    private static int[] circularLastLoginsCounter;

    private static int[] circularUserCounter;

    private static Map<String, Integer>[] circularUserCounterByCountry;

    static AnalyticStorageOnLocalMemory getInstance(int numDays){
        if(instance==null){
            if(numDays<0 || numDays>365)
                instance = new AnalyticStorageOnLocalMemory();
            else
                instance = new AnalyticStorageOnLocalMemory(numDays);
        }
        return instance;
    }

    AnalyticStorageOnLocalMemory(int days){
        this.numDays=days;
    }

    AnalyticStorageOnLocalMemory(){
        this(30);
    }
}
