import java.util.Calendar;

public class ServerStartup {
    private static boolean started=false;

    public static void main(String[] args){
        try {
            while (true) {
                long timeToSleep;
                if (!started) {
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DAY_OF_MONTH, 1);
                    c.set(Calendar.HOUR_OF_DAY, 0);
                    c.set(Calendar.MINUTE, 0);
                    c.set(Calendar.SECOND, 0);
                    c.set(Calendar.MILLISECOND, 0);
                    timeToSleep = (c.getTimeInMillis()-System.currentTimeMillis());
                } else {
                    timeToSleep=24*60*60*1000; //once per day
                }
                Thread.sleep(timeToSleep);
                Thread loginCounter = new LoginCounter();
                Thread userCounter = new UserCounter();
                Thread userCounterByCountry = new UserCounterByCountry();
                loginCounter.start();
                userCounter.start();
                userCounterByCountry.start();
            }
        }
        catch (InterruptedException i){
            System.out.println(i.getMessage());
            i.printStackTrace();
        }
    }
}
