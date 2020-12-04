package it.unipi.dii.lsmsd.pokeMongo.persistence;


import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

import java.util.ArrayList;

import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;
import org.neo4j.driver.types.Path;

public abstract class Neo4jDbDatabase implements Database {
    private Driver driver;

    private String uri = "neo4j://localhost:7687";
    private String user = "neo4j";
    private String password = "";

    public void startConnection(){
        Logger.vlog("Starting connection with MongoDB");

        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void closeConnection() {
        driver.close();
    }

    private Driver getConnection() {
        if(driver==null)
            startConnection();
        return driver;
    }

    /*private MongoDatabase getDatabase(){
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
    }*/

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
