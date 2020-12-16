package it.unipi.dii.lsmsd.pokeMongo.utils;

import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;


class LoggerThread extends Thread{
    private static String logFilePath="log/logFile.txt";
    private String msg;

    LoggerThread(String msg){
        this.msg=msg;
    }

    public void run(){
        String newMsg = Instant.now().toString() + " " + msg + "\n";
        writeOnFile(newMsg);
    }

    private static synchronized void writeOnFile(String msg){
        try {
            Files.write(Paths.get(logFilePath), msg.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException i){
            i.printStackTrace();
        }
    }

}
