package persistence;

import org.iq80.leveldb.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static org.iq80.leveldb.impl.Iq80DBFactory.*;

public class LevelDbDatabase implements Database{
    private DB currentInstance;

    @Override
    public void startConnection() {
        Options options = new Options();
        options.createIfMissing(true);
        try{
            currentInstance = factory.open(new File("AnalyticDb.txt"), options);
        }catch (IOException ioe){
            System.out.println("Failed to open db");
            closeConnection();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void closeConnection() {
        if(currentInstance!=null){
            try {
                currentInstance.close();
                currentInstance = null;
            }catch (IOException ioe){
                System.out.println("Failed to close db");
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public boolean insert(Object toInsert) {
        startConnection();
        Map<String, String> map= (Map)toInsert;
        for(Map.Entry<String,String> entry : map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            currentInstance.put(bytes(key), bytes(value));
        }
        closeConnection();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        startConnection();
        Map<String, String> map= (Map)o;
        for(Map.Entry<String,String> entry : map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            currentInstance.delete(bytes(key));
        }
        closeConnection();
        return true;
    }

    @Override
    public ArrayList<Object> getAll() {
        return null;
    }

    @Override
    public ArrayList<Object> getWithFilter(Object filter) {
        return null;
    }

    @Override
    public boolean update(Object target, Object newValue) {
        String key = (String)target;
        String value = (String)newValue;
        currentInstance.put(bytes(key), bytes(value));
        return true;
    }
}