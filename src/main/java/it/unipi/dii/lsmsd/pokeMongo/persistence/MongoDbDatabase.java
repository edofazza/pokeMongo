package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import org.bson.Document;

import java.util.ArrayList;

public abstract class MongoDbDatabase implements Database{
    private MongoClient connection;

    private final String provaLocalCluster1 = "mongodb://localhost:27018,localhost:27019,localhost:27020/";
    private final String provaLocalCluster2 = "?retryWrites=true?w=majority&wtimeout=10000";

    //read-your-writes consistency as guaranteed by https://docs.mongodb.com/manual/reference/read-concern/#read-concern-levels
    private final String clusterAddress = "mongodb://172.16.3.85:27017,172.16.3.86:27017,172.16.3.87:27017/";
    private final String retryWrites = "retryWrites=true";
    private final String writeConcern = "w=majority";
    private final String writeTimeout = "wtimeout=10000";
    private final String readPreference = "readPreference=primaryPreferred&maxStalenessSeconds=120";
    private final String readConcern = "readConcernLevel=majority";

    private String host="localhost";
    private int port = 27017;
    private String dbName="pokeMongo";

    @Override
    public void startConnection(){
        Logger.vlog("Starting connection with MongoDB");

        connection=MongoClients.create(clusterAddress + "?" + retryWrites + "&" + writeConcern + "&" + writeTimeout
                                    + "&" + readPreference + "&" + readConcern);

        // FOR LOCAL CLUSTER ONLY WITH RUNNING PROCESSES ON POWERSHELL
        // connection = MongoClients.create(provaLocalCluster1 + provaLocalCluster2);

    }

    @Override
    public void closeConnection() {
        Logger.vlog("Closing connection with MongoDB");
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
        Logger.vlog("Getting " + dbName + " Database");
        return getConnection().getDatabase(dbName);
    }

    public MongoCollection<Document> getCollection(String name){
        Logger.vlog("Getting " + name + " collection");
        return getDatabase().getCollection(name);
    }

    public void dropCollection(String name){
        Logger.vlog("Dropping " + name + " Database");
        getCollection(name).drop();
    }

    @Override
    public abstract boolean insert(ArrayList<Object> toInsert);

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
}
