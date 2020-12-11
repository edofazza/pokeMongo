package persistence;

import org.iq80.leveldb.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
        if(o instanceof List){
            List<String> keys= (List)o;
            startConnection();
            for(String key: keys){
                currentInstance.delete(bytes(key));
            }
        }
        else if(o instanceof String){
            startConnection();
            currentInstance.delete(bytes((String)o));
        }
        else
            return false;

        closeConnection();
        return true;
    }

    public boolean removeLower(String threshold){
        boolean result=true;
        startConnection();
        try(DBIterator iter = currentInstance.iterator()){
            for(iter.seekToFirst(); iter.hasNext(); iter.next()){
                String key = asString(iter.peekNext().getKey());
                if(key.compareTo(threshold)<0)
                    remove(key);
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
            result=false;
        }
        finally {
            closeConnection();
        }
        return result;
    }

    @Override
    public Object getAll() {
        startConnection();
        Map<String, String> map= new HashMap<>();
        Snapshot snapshot = currentInstance.getSnapshot();
        try(DBIterator iter = currentInstance.iterator(new ReadOptions().snapshot(snapshot))){
            for(iter.seekToFirst(); iter.hasNext(); iter.next()){
                String key = asString(iter.peekNext().getKey());
                String value = asString(iter.peekNext().getValue());
                map.put(key, value);
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return map;
    }

    @Override
    public Object getWithFilter(Object filter) {
        startConnection();
        if(!(filter instanceof String))
            return null;
        Snapshot snapshot = currentInstance.getSnapshot();
        Long result = Long.parseLong(asString(currentInstance.get(bytes((String)filter), new ReadOptions().snapshot(snapshot))));
        closeConnection();
        return result;
    }

    @Override
    public boolean update(Object target, Object newValue) {
        String key = (String)target;
        String value = (String)newValue;
        currentInstance.put(bytes(key), bytes(value));
        return true;
    }
}
