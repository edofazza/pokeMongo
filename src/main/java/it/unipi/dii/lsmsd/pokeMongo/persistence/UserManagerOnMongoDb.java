package it.unipi.dii.lsmsd.pokeMongo.persistence;


import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class UserManagerOnMongoDb extends MongoDbDatabase implements UserManager{
    private final String collectionName = "user";

    private Document UserToDocument(User u){
        return Document.parse(new Gson().toJson(u));
    }

    private User DocumentToUser(Document doc){
        return new Gson().fromJson(doc.toJson(), User.class);
    }

    @Override
    public boolean insert(ArrayList<Object> toInsert) {
        if(toInsert.size()==0)
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);  //also opens connection
        if(toInsert.size()==1){
            Document doc = UserToDocument((User)toInsert.get(0));
            collection.insertOne(doc);
        }
        else{
            List<Document> l = new ArrayList<>();
            for(Object o:toInsert){
                Document doc = UserToDocument((User)o);
                l.add(doc);
            }
            collection.insertMany(l);
        }
        closeConnection();
        return true;
    }

    @Override
    public boolean insert(Object toInsert) {
        if(toInsert==null)
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);  //also opens connection
        Document doc = UserToDocument((User)toInsert);
        collection.insertOne(doc);
        closeConnection();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        MongoCollection<Document> collection = getCollection(collectionName);
        DeleteResult dr;
        if (o instanceof User){
            dr = collection.deleteOne(eq("username", ((User) o).getUsername()));
        }
        else if(o instanceof Bson){
            dr = collection.deleteMany((Bson)o);
        }
        else {
            closeConnection();
            return false;
        }
        closeConnection();
        return dr.getDeletedCount()>0;
    }

    @Override
    public ArrayList<Object> getAll() {
        List<Document> docs= getCollection(collectionName).find().into(new ArrayList<>());
        ArrayList<Object> users = new ArrayList<>();
        for(Document d:docs){
            users.add(DocumentToUser(d));
        }
        closeConnection();
        return users;
    }

    @Override
    public ArrayList<Object> getWithFilter(Object filter) {
        List<Document> docs= getCollection(collectionName).find((Bson)filter).into(new ArrayList<>());
        ArrayList<Object> users = new ArrayList<>();
        for(Document d:docs){
            users.add(DocumentToUser(d));
        }
        closeConnection();
        return users;
    }

    @Override
    public boolean update(Object target, Object newValue) {
        MongoCollection<Document> collection = getCollection(collectionName);
        UpdateResult ur;
        if (target instanceof User){
            ur = collection.updateOne(eq("username", ((User) target).getUsername()),(Bson)newValue);
        }
        else if(target instanceof Bson){
            ur = collection.updateMany((Bson)target, (Bson)newValue);
        }
        else {
            closeConnection();
            return false;
        }
        closeConnection();
        return ur.getModifiedCount()>0;
    }

    @Override
    public User login(String username, String password) {
        Bson query = and(eq("username", username), eq("password", password));
        ArrayList<Object> matched = getWithFilter(query);
        if(matched.size()!=1)
            return null;
        return (User)matched.get(0);
    }

    @Override
    public User login(User toLog) {
        return login(toLog.getUsername(), toLog.getPassword());
    }
}
