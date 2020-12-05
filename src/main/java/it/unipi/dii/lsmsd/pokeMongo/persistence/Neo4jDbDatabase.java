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
