package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
        return getConnection().getDatabase(dbName);
    }

    public MongoCollection<Document> getCollection(String name){
        return getDatabase().getCollection(name);
    }

    public void dropCollection(String name){
        getCollection(name).drop();
    }

    @Override
    public abstract boolean insert(ArrayList<Object> toInsert);

    @Override
    public abstract boolean remove(Object o);

    @Override
    public abstract ArrayList<Object> getAll();

    @Override
    public abstract ArrayList<Object> getWithFilter(Object filter);

    @Override
    public abstract boolean update(Object target, Object newValue);
}
