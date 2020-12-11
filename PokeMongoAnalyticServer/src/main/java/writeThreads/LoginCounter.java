package writeThreads;

import analytic.Analyzer;
import analytic.AnalyzerFactory;
import persistence.AnalyticStorage;
import persistence.AnalyticStorageFactory;

public class LoginCounter extends Thread{

    public void run(){
        Analyzer analyzer = AnalyzerFactory.buildAnalyzer();
        long todayLogin = analyzer.getTodayLogin();
        System.out.println(todayLogin);
        AnalyticStorage analyticStorage = AnalyticStorageFactory.buildAnalyzer();
        analyticStorage.setLastLogin(todayLogin);
    }
}
