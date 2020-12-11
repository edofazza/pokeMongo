import writeThreads.*;

public class ServerStartup {
    private static boolean started=false;

    public static void main(String[] args){
       /* try {
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
                updateAll();
          /*  }
        }
        catch (InterruptedException i){
            System.out.println(i.getMessage());
            i.printStackTrace();
        }*/
    }

    private static void updateAll(){

    }
}
