package persistence;

import java.util.*;

class AnalyticStorageOnLocalMemory implements AnalyticStorage{
    private final int numDays;
    private static AnalyticStorageOnLocalMemory instance;
    private static int index=0;
    private static int howManyValid=0;

    private static int[] circularLastLoginsCounter;

    private static int[] circularUserCounter;

    private static List<Map<String, Long>> circularUserCounterByCountry;

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
        circularLastLoginsCounter = new int[days];
        circularUserCounter = new int[days];
        circularUserCounterByCountry = new ArrayList<>();
    }

    AnalyticStorageOnLocalMemory(){
        this(30);
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
    public void setLastLogin(long counted) {

    }

    @Override
    public void setUserNumber(long counted) {

    }

    @Override
    public void setUserNumberByCountry(Map<String, Long> counted) {

    }
}
