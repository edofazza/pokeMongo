package persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
//import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.*;

public abstract class MongoDbDatabase implements Database{
    private MongoClient connection;

    private String host="localhost";
    private int port = 27017;
    private String dbName="pokeMongo";

    private final String clusterAddress = "mongodb://172.16.3.85:27017,172.16.3.86:27017,172.16.3.87:27017/";
    private final String retryWrites = "retryWrites=true";
    private final String writeConcern = "w=majority&journal=true";
    private final String writeTimeout = "wtimeout=10000";
    private final String readPreference = "readPreference=secondaryPreferred&maxStalenessSeconds=120";
    private final String readConcern = "readConcernLevel=snapshot";

    @Override
    public void startConnection(){
        //connection=MongoClients.create(clusterAddress + "?" + retryWrites + "&" + writeConcern + "&" +
        //        writeTimeout + "&" + readPreference + "&" + readConcern);
        connection= MongoClients.create("mongodb://" + host + ":" + port);
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


    @Override
    public abstract boolean insert(Object toInsert);

    @Override
    public abstract boolean remove(Object o);

    @Override
    public abstract ArrayList<Object> getAll();

    @Override
    public abstract ArrayList<Object> getWithFilter(Object filter);

    @Override
    public abstract boolean update(Object target, Object newValue);

    protected abstract Object aggregate(List<Bson> pipeline);

    private void showError(){
        Thread.currentThread().interrupt();
        System.out.println("WRITE ACCESS NOT ALLOWED FOR THREAD " + Thread.currentThread().getName());
    }
}
