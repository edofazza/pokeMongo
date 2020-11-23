package it.unipi.dii.lsmsd.pokeMongo.persistence;

import java.util.ArrayList;

public interface Database {
    boolean insert(ArrayList<Object> toInsert);
    boolean remove(Object o);
    ArrayList<Object> getAll();
    ArrayList<Object> getWithFilter(Object filter);
    boolean update(Object target, Object newValue);
}
