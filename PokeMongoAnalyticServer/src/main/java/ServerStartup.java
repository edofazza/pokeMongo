import analytic.Analyzer;
import analytic.AnalyzerFactory;
import persistence.AnalyticStorage;
import persistence.AnalyticStorageFactory;

import java.util.Calendar;
import java.util.Map;

public class ServerStartup {
    private static boolean started=false;

    public static void main(String[] args){
       /*try {
            while (true) {
                long timeToSleep;
                if (!started) {         //first time starts at 00:00:01
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DATE, 1);
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    c.set(Calendar.MINUTE, 0);
                    c.set(Calendar.SECOND, 1);
                    c.set(Calendar.MILLISECOND, 0);
                    timeToSleep = (c.getTimeInMillis()-System.currentTimeMillis());
                    started=true;
                } else {
                    timeToSleep=24*60*60*1000; //once per day
                }
                Thread.sleep(timeToSleep);*/
                updateAll();/*
            }
        }
        catch (InterruptedException i){
            System.out.println(i.getMessage());
            i.printStackTrace();
        }*/
    }

    private static void updateAll(){
        Analyzer a = AnalyzerFactory.buildAnalyzer();
        long lastLogins = a.getTodayLogin();
        long userNumber = a.getUserNumber();
        Map<String, Long> map = a.getLastLoginsByCountry();

        AnalyticStorage as = AnalyticStorageFactory.buildAnalyzer();
        as.setLastLogin(lastLogins);
        as.setUserNumber(userNumber);
        as.setLastLoginsByCountry(map);
    }
}
