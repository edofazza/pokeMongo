package it.unipi.dii.lsmsd.pokeMongo.persistence;


import it.unipi.dii.lsmsd.pokeMongo.config.ConfigDataHandler;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;

import java.util.ArrayList;

import org.neo4j.driver.*;

public abstract class Neo4jDbDatabase implements Database {
    public Driver driver;

    private String uri = ConfigDataHandler.getInstance().configData.localUri;
    private String user = ConfigDataHandler.getInstance().configData.userNeo4j;
    private String password = ConfigDataHandler.getInstance().configData.passwordNeo4j;

    public void startConnection(){
        Logger.vlog("Starting connection with Neo4j");

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

    public boolean insert(ArrayList<Object> toInsert){
        getConnection();
        for(Object query: toInsert){
            if(!(query instanceof String)){
                closeConnection();
                return false;
            }
            try (Session session = driver.session()) {
                session.writeTransaction((TransactionWork<Void>) tx -> {
                    tx.run((String)query);
                    return null;
                });
            }
        }
        closeConnection();
        return true;
    }

    @Override
    public boolean insert(Object query){
        if(!(query instanceof String)){
            return false;
        }
        startConnection();
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run((String)query);
                return null;
            });
        }
        closeConnection();
        return true;
    }

    //TODO non è un override di database
    public boolean insert(Object query, Object value){
        if(!(query instanceof String) || !(value instanceof Value)){
            return false;
        }
        startConnection();
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run((String)query, (Value)value);
                return null;
            });
        }
        closeConnection();
        return true;
    }

    @Override
    public boolean remove(Object query){
        if(!(query instanceof String))
            return false;
        startConnection();
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run((String)query);
                return null;
            });
        }
        closeConnection();
        return true;
    }

    //TODO non è un override di database
    public boolean remove(Object query, Object value){
        if(!(query instanceof String) || !(value instanceof Value))
            return false;
        startConnection();
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run((String)query, (Value)value);
                return null;
            });
        }
        closeConnection();
        return true;
    }

    @Override
    public ArrayList<Object> getAll(){
        ArrayList<Object> records = new ArrayList<>();
        startConnection();
        try (Session session = driver.session()) {
            session.readTransaction((TransactionWork<Void>) tx -> {
                Result result = tx.run("MATCH (n) RETURN n");
                while (result.hasNext()) {
                    Record r = result.next();
                    records.add(r);
                }
                return null;
            });
        }
        closeConnection();
        return records;
    }

    @Override
    public ArrayList<Object> getWithFilter(Object query){
        if(!(query instanceof String))
            return null;
        ArrayList<Object> records = new ArrayList<>();
        startConnection();
        try (Session session = driver.session()) {
            session.readTransaction((TransactionWork<Void>) tx -> {
                Result result = tx.run((String)query);
                while (result.hasNext()) {
                    Record r = result.next();
                    records.add(r);
                }
                return null;
            });
        }
        closeConnection();
        return records;
    }

    @Override
    public boolean update(Object target, Object newValue){
        if(!(target instanceof String) || !(newValue instanceof String))
            return false;
        startConnection();
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run(target + " " + newValue);
                return null;
            });
        }
        closeConnection();
        return true;
    }

}
