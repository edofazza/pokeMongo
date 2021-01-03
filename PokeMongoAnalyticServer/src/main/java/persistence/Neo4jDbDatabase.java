package persistence;


import conf.ConfigDataHandler;
import org.neo4j.driver.*;

import java.util.ArrayList;

public abstract class Neo4jDbDatabase implements Database {
    public static Driver driver;


    private String uri = ConfigDataHandler.getInstance().configData.remoteUriNeo4j;

//    private String uri = ConfigDataHandler.getInstance().configData.localUriNeo4j;

    private String user = ConfigDataHandler.getInstance().configData.userNeo4j;
    private String password = ConfigDataHandler.getInstance().configData.passwordNeo4j;

    public void startConnection(){

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
                return false;
            }
            try (Session session = driver.session()) {
                session.writeTransaction((TransactionWork<Void>) tx -> {
                    tx.run((String)query);
                    return null;
                });
            }
        }
        return true;
    }

    @Override
    public boolean insert(Object query){
        if(!(query instanceof String)){
            return false;
        }
        getConnection();
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run((String)query);
                return null;
            });
        }
        return true;
    }

    public boolean insert(Object query, Object value){
        if(!(query instanceof String) || !(value instanceof Value)){
            return false;
        }
        getConnection();
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run((String)query, (Value)value);
                return null;
            });
        }
        return true;
    }

    @Override
    public boolean remove(Object query){
        if(!(query instanceof String))
            return false;
        getConnection();
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run((String)query);
                return null;
            });
        }
        return true;
    }


    @Override
    public ArrayList<Object> getAll(){
        ArrayList<Object> records = new ArrayList<>();
        getConnection();
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
        return records;
    }

    @Override
    public ArrayList<Object> getWithFilter(Object query){
        if(!(query instanceof String))
            return null;
        ArrayList<Object> records = new ArrayList<>();
        getConnection();
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
        return records;
    }

    public ArrayList<Object> getWithFilter(Object query, Object value){
        if(!(query instanceof String))
            return null;
        ArrayList<Object> records = new ArrayList<>();
        getConnection();
        try (Session session = driver.session()) {
            session.readTransaction((TransactionWork<Void>) tx -> {
                Result result = tx.run((String)query, (Value)value);
                while (result.hasNext()) {
                    Record r = result.next();
                    records.add(r);
                }
                return null;
            });
        }
        return records;
    }

    @Override
    public boolean update(Object target, Object newValue){
        if(!(target instanceof String) || !(newValue instanceof Value))
            return false;
        getConnection();
        try (Session session = driver.session()) {
            session.writeTransaction((TransactionWork<Void>) tx -> {
                tx.run((String)target, (Value)newValue);
                return null;
            });
        }
        return true;
    }

}
