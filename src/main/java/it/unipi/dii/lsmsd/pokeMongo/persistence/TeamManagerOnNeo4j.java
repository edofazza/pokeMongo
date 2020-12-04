package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;

public class TeamManagerOnNeo4j extends Neo4jDbDatabase {
    @Override
    @VisibleForTesting
    public boolean insert(ArrayList<Object> toInsert) {
        return true;
    }

    @Override
    @VisibleForTesting
    public boolean insert(Object toInsert) {
        return true;
    }

    @Override
    @VisibleForTesting
    public boolean remove(Object o) {
        return true;
    }

    @Override
    public ArrayList<Object> getAll() {
        return new ArrayList<>();
    }

    @Override
    @VisibleForTesting
    public ArrayList<Object> getWithFilter(Object filter) {
        return new ArrayList<>();
    }

    @Override
    @VisibleForTesting
    public boolean update(Object target, Object newValue) {
        return true;
    }
}
