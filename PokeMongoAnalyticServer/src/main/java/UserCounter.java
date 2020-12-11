import analytic.Analyzer;
import analytic.AnalyzerFactory;
import persistence.AnalyticStorage;
import persistence.AnalyticStorageFactory;

public class UserCounter extends Thread{

    public void run(){
        Analyzer analyzer = AnalyzerFactory.buildAnalyzer();
        long userNumber = analyzer.getUserNumber();
        System.out.println(userNumber);
        AnalyticStorage analyticStorage = AnalyticStorageFactory.buildAnalyzer();
        analyticStorage.setUserNumber(userNumber);
    }
}
