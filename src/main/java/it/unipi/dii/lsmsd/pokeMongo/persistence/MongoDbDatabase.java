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

    private String provaLocalCluster1 = "mongodb://localhost:27018,localhost:27019,localhost:27020/";
    private String provaLocalCluster2 = "?retryWrites=true?w=majority&wtimeout=10000";

    private String host="localhost";
    private int port = 27017;
    private String dbName="pokeMongo";

    @Override
    public void startConnection(){
        Logger.vlog("Starting connection with MongoDB");

        connection=MongoClients.create("mongodb://" + host + ":" + port);
        //connection = MongoClients.create(provaLocalCluster1 + provaLocalCluster2);
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
