package it.unipi.dii.lsmsd.pokeMongo.persistence;


import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import it.unipi.dii.lsmsd.pokeMongo.bean.User;
import it.unipi.dii.lsmsd.pokeMongo.dataAnalysis.UserRanker;
import it.unipi.dii.lsmsd.pokeMongo.security.PasswordEncryptor;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;

public class UserManagerOnMongoDb extends MongoDbDatabase implements UserManager, UserRanker {
    private final String collectionName = "user";

    private Document UserToDocument(User u){
        return Document.parse(new Gson().toJson(u));
    }

    private User DocumentToUser(Document doc){
        return new Gson().fromJson(doc.toJson(), User.class);
    }

    private List<User> aggregate(List<Bson> steps){
        MongoCollection<Document> collection = getCollection(collectionName);
        List<Document> result = collection.aggregate(steps).into(new ArrayList<>());
        List<User> toReturn = new ArrayList<>();
        for(Document d:result){
            toReturn.add(DocumentToUser(d));
        }
        closeConnection();
        return toReturn;
    }

    @Override
    @VisibleForTesting
    public boolean insert(ArrayList<Object> toInsert) {
        if(toInsert.size()==0)
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);  //also opens connection

        //TODO: Added Password Encryption, watch effects
        for(Object o: toInsert){
            if(!(o instanceof User)){
                closeConnection();
                return false;
            }
            User userToInsert = (User)o;
            userToInsert.setPassword(PasswordEncryptor.encryptPassword(userToInsert.getPassword()));
        }

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
    @VisibleForTesting
    public boolean insert(Object toInsert) {
        if(!(toInsert instanceof User))
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);  //also opens connection

        //TODO: Added Password Encryption, watch effects
        User userToInsert = (User)toInsert;
        userToInsert.setPassword(PasswordEncryptor.encryptPassword(userToInsert.getPassword()));
        Document doc = UserToDocument(userToInsert);
        collection.insertOne(doc);
        closeConnection();
        return true;
    }

    @Override
    @VisibleForTesting
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
    @VisibleForTesting
    public ArrayList<Object> getWithFilter(Object filter) {
        if(!(filter instanceof Bson))
            return null;
        List<Document> docs= getCollection(collectionName).find((Bson)filter).into(new ArrayList<>());
        ArrayList<Object> users = new ArrayList<>();
        for(Document d:docs){
            users.add(DocumentToUser(d));
        }
        closeConnection();
        return users;
    }

    @Override
    @VisibleForTesting
    public boolean update(Object target, Object newValue) {
        if(!(newValue instanceof Bson))
            return false;
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
        //TODO: Added Password Encryption, watch effects
        Bson query = and(eq("username", username), eq("password", PasswordEncryptor.encryptPassword(password)));
        ArrayList<Object> matched = getWithFilter(query);
        if(matched.size()!=1)
            return null;
        User logger = (User)matched.get(0);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
        Date now = new Date();

        // Compare dates and update pokeball in case
        Date oldLastLogin = logger.getLastLogin();
        Calendar oldCal = Calendar.getInstance();
        oldCal.setTime(oldLastLogin);
        oldCal.add(Calendar.DATE, 1);
        oldCal.set(Calendar.HOUR_OF_DAY, 0);
        oldCal.set(Calendar.MINUTE, 0);
        oldCal.set(Calendar.MILLISECOND, 0);

        Calendar newCal = Calendar.getInstance();
        newCal.setTime(new Date());

        if(newCal.after(oldCal)) {
            updateNumberOfPokeballTo10(logger);
            logger.resetDailyPokeball();
        }
        // End

        String dateString = sdf.format(now);
        update(query, set("lastLogin", dateString.substring(0,1).toUpperCase() + dateString.substring(1)));
        return logger;
    }

    @Override
    public User login(User toLog) {
        return login(toLog.getUsername(), toLog.getPassword());
    }

    @Override
    public boolean register(User toRegister) {
        Bson query = eq("username", toRegister.getUsername());
        ArrayList<Object> alreadyPresent=getWithFilter(query);
        if(alreadyPresent.size()>0)
            return false;
        return insert(toRegister);
    }

    @Override
    public boolean changeEmail(User target, String newEmail) {
        return update(target, set("email", newEmail));
    }

    @Override
    public boolean changePassword(User target, String newPassword) {
        //TODO: Added Password Encryption, watch effects
        return update(target, set("password", PasswordEncryptor.encryptPassword(newPassword)));
    }

    @Override
    public boolean changeCountry(User target, String newCountry) {
        return update(target, set("country", newCountry));
    }

    @Override
    public boolean removeUser(User target) {
        if(target.isAdmin())
            return false;
        return remove(target);
    }

    @Override
    public boolean removeUser(String username) {
        Bson query = eq("username", username);
        ArrayList<Object> target = getWithFilter(query);
        if(target.size()!=1 || ((User)(target.get(0))).isAdmin())
            return false;
        return remove(query);
    }

    @Override
    public boolean verifyOldPassword(User involved, String password) {
        Bson query = eq("username", involved.getUsername());
        ArrayList<Object> matches=getWithFilter(query);
        if(matches.size()!=1)
            return false;
        return ((User)matches.get(0)).getPassword().equals(PasswordEncryptor.encryptPassword(password));
    }

    @Override
    public boolean changeTeamName(User involved, String newName) {
        return update(involved, set("teamName", newName));
    }

    @Override
    public boolean updatePoints(User target, double points) {
        return update(target, set("points", points));
    }

    @Override
    public boolean updateNumberOfPokeball(User target) {
        return update(target, set("dailyPokeball", target.getDailyPokeball()));
    }

    @Override
    public boolean updateNumberOfPokeballTo10(User target) {
        return update(target, set("dailyPokeball", 10));
    }

    @Override
    public boolean decrementPokeball(User target) {return update(target, inc("dailyPokeball", -1));}

    @Override
    public void logout(User toLogOut) {
        update(toLogOut, set("dailyPokeball", toLogOut.getDailyPokeball()));
    }




    @Override
    public List<User> bestWorldTeams() {
        Bson match = eq("admin", false);
        Bson sort = sort(descending("points", "birthDay"));
        Bson limit = limit(20);
        Bson project = project(fields(excludeId(), include("username", "teamName", "points", "birthDay", "country")));
        return aggregate(Arrays.asList(match, sort, limit, project));
    }

    @Override
    public List<User> bestFriendsTeams(User current) {
        Bson sort = sort(descending("points", "birthDay"));
        Bson limit = limit(20);
        List<User> friends = /*getFriends(User current)*/new ArrayList<>();
        Bson match = match(and(in("username", friends), eq("admin", false)));
        Bson project = project(fields(excludeId(), include("username", "teamName", "points", "birthDay", "country")));
        return aggregate(Arrays.asList(match, sort, limit, project));
    }

    @Override
    public List<User> bestCountryTeams(String country) {
        Bson match = match(and(eq("country", country), eq("admin", false)));
        Bson sort = sort(descending("points", "birthDay"));
        Bson limit = limit(20);
        Bson project = project(fields(excludeId(), include("username", "teamName", "points", "birthDay", "country")));
        return aggregate(Arrays.asList(match, sort, limit, project));
    }
}
