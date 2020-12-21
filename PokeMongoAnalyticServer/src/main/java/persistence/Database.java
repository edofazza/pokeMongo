package persistence;


/**
 * Shared interface among Databases, defines remote connections and structures of basic CRUD operations
 */
public interface Database {

    /**
     * starts the connection with the remote Database
     */
    void startConnection();

    /**
     * closes the connection with the remote Database
     */
    void closeConnection();

    /**
     * insert 1 object into the database
     * @param toInsert ArrayList of arbitrary objects to insert into the Database
     * @return true if exactly one element has been added
     */
    boolean insert(Object toInsert);

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
    Object getAll();

    /**
     *
     * @param filter query or filter to submit to the db
     * @return a list of retrieved elements
     */
    Object getWithFilter(Object filter);

    /**
     *
     * @param target query or object to update
     * @param newValue new value for the target
     * @return true if at least one object is updated
     */
    boolean update(Object target, Object newValue);
}
