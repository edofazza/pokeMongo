package it.unipi.dii.lsmsd.pokeMongo.persistence;

import java.util.ArrayList;


/**
 * Shared interface among Databases, defines structures of basic CRUD operations
 */
public interface Database {
    /**
     * insert 1 ore more objects into the database
     * @param toInsert ArrayList of arbitrary objects to insert into the Database
     * @return true if at least one element has been added
     */
    boolean insert(ArrayList<Object> toInsert);

    /**
     * remove 1 ore more objects from the database
     * @param o is an Object to remove or a query to submit
     * @return true if at least one element has been removed
     */
    boolean remove(Object o);

    /**
     * gets all the elements
     * @return a list of retrieved elements
     */
    ArrayList<Object> getAll();

    /**
     *
     * @param filter query or filter to submit to the db
     * @return a list of retrieved elements
     */
    ArrayList<Object> getWithFilter(Object filter);

    /**
     *
     * @param target query or object to update
     * @param newValue new value for the target
     * @return true if at least one object is updated
     */
    boolean update(Object target, Object newValue);
}
