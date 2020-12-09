package persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
//import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import org.bson.Document;

import java.util.ArrayList;

public abstract class MongoDbDatabase implements Database{
    private MongoClient connection;

    private String host="localhost";
    private int port = 27017;
    private String dbName="pokeMongo";

    @Override
    public void startConnection(){
        connection=MongoClients.create("mongodb://" + host + ":" + port);
    }

    @Override
    public void closeConnection() {
        //Logger.vlog("Closing connection with MongoDB");
        if(connection!=null) {
            connection.close();
            connection = null;
        }
    }

    private MongoClient getConnection() {
        if(connection==null)
            startConnection();
        return connection;
    }

    private MongoDatabase getDatabase(){
        //Logger.vlog("Getting " + dbName + " Database");
        return getConnection().getDatabase(dbName);
    }

    public MongoCollection<Document> getCollection(String name){
        //Logger.vlog("Getting " + name + " collection");
        return getDatabase().getCollection(name);
    }

    public void dropCollection(String name){
        //Logger.vlog("Dropping " + name + " Database");
        getCollection(name).drop();
    }

    public boolean insert(ArrayList<Object> toInsert){
        showError();
        return false;
    }

    @Override
    public boolean insert(Object toInsert){
        showError();
        return false;
    }

    @Override
    public boolean remove(Object o){
        showError();
        return false;
    }

    @Override
    public abstract ArrayList<Object> getAll();

    @Override
    public abstract ArrayList<Object> getWithFilter(Object filter);

    @Override
    public boolean update(Object target, Object newValue){
        showError();
        return false;
    }

    private void showError(){
        Thread.currentThread().interrupt();
        System.out.println("WRITE ACCESS NOT ALLOWED FOR THREAD " + Thread.currentThread().getName());
    }
}
