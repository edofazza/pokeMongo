package persistence;

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

}
