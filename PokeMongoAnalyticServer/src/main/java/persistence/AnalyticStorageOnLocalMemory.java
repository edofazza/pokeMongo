package persistence;

import java.util.*;

class AnalyticStorageOnLocalMemory implements AnalyticStorage{
    private final int numDays;
    private static AnalyticStorageOnLocalMemory instance;
    private static int indexLastLogin=0;
    private static int indexUserCounter=0;
    private static int indexUserCounterByCountry=0;

    private static long[] circularLastLoginsCounter;

    private static long[] circularUserCounter;

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
        circularLastLoginsCounter = new long[days];
        circularUserCounter = new long[days];
        circularUserCounterByCountry = new ArrayList<>();
    }

    AnalyticStorageOnLocalMemory(){
        this(30);
    }


    private static void setIndex(int index){
        index++;
    }

    private long[] getFromCircularArray(int index, long[] array){
        long[] toReturn = new long[numDays];
        for(int i=0, cursor=(index+1)%numDays; i<numDays && cursor!=index;cursor=(cursor+1)%numDays){
            if(array[cursor]!=0) {
                toReturn[i] = array[cursor];
                i++;
            }
        }
        return toReturn;
    }


    @Override
    public long[] getLastLogins() {
        return getFromCircularArray(indexLastLogin ,circularLastLoginsCounter);
    }

    @Override
    public long[] getUserNumber() {
        return getFromCircularArray(indexUserCounter, circularUserCounter);
    }

    @Override
    public List<Map<String, Long>> getUserNumberByCountry() {
        List<Map<String, Long>> toReturn = new ArrayList<>();
        for(int i=0, cursor=(indexUserCounterByCountry+1)%numDays;
                i<numDays && cursor!=indexUserCounterByCountry;
                cursor=(cursor+1)%numDays)
        {
            if(circularUserCounterByCountry.get(cursor)!=null) {
                toReturn.add(i, circularUserCounterByCountry.get(cursor));
                i++;
            }
        }
        return toReturn;
    }

    @Override
    public void setLastLogin(long counted) {
        setIndex(indexLastLogin);
        circularLastLoginsCounter[indexLastLogin]=counted;
    }

    @Override
    public void setUserNumber(long counted) {
        setIndex(indexUserCounter);
        circularUserCounter[indexUserCounter]=counted;
    }

    @Override
    public void setUserNumberByCountry(Map<String, Long> counted) {
        setIndex(indexUserCounterByCountry);
        circularUserCounterByCountry.add(indexUserCounterByCountry, counted);
    }
}
