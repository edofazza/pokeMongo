package writeThreads;

import analytic.Analyzer;
import analytic.AnalyzerFactory;
import persistence.AnalyticStorage;
import persistence.AnalyticStorageFactory;

import java.util.Map;

public class UserCounterByCountry extends Thread{

    public void run(){
        Analyzer analyzer = AnalyzerFactory.buildAnalyzer();
        Map<String, Long> userNumberByCountry = analyzer.getUserNumberByCountry();
        System.out.println(userNumberByCountry.get(0));
        AnalyticStorage analyticStorage = AnalyticStorageFactory.buildAnalyzer();
        analyticStorage.setUserNumberByCountry(userNumberByCountry);
    }
}
