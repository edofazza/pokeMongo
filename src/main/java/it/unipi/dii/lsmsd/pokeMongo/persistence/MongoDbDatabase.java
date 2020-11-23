package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class MongoDbDatabase implements Database{
    private static MongoClient connection;
    private static String host="localhost";
    private static int port = 27017;
    private static String dbName="pokeMongo";

    private static MongoClient getConnection() {
        if(connection==null)
            connection=MongoClients.create("mongodb://" + host + ":" + port);
        return connection;
    }

    public static void closeConnection() {
        connection.close();
        connection=null;
    }

    private static MongoDatabase getDatabase(){
        return connection.getDatabase(dbName);
    }

    public static MongoCollection<Document> getCollection(String name){
        return getDatabase().getCollection(name);
    }

    public static void dropCollection(String name){
        getCollection(name).drop();
    }

    @Override
    public boolean insert(ArrayList<Object> toInsert) {

        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
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
        return false;
    }
}
