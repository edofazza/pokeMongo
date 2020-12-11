package persistence;

import bean.Analytic;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.addToSet;
import static com.mongodb.client.model.Updates.set;

public class AnalyticStorageOnMongoDb extends MongoDbDatabase implements AnalyticStorage{
    private String collectionName="analytic";
    private Analytic myAnalytic;
    private int numDays;

    AnalyticStorageOnMongoDb(int numDays){
        this.numDays = numDays;
    }

    AnalyticStorageOnMongoDb(){
        this(30);
    }


    private Document AnalyticToDocument(Analytic a){
        return Document.parse(new Gson().toJson(a));
    }

    private Analytic DocumentToAnalytic(Document d){
        return new Gson().fromJson(d.toJson(), Analytic.class);
    }


    public long[] getLastLogins() {
        long[] result = new long[numDays];
        ArrayList<Object> al= getAll();
        for(int i=0; i<numDays; i++){
            result[i]=DocumentToAnalytic((Document)al.get(i)).getLastLogins();
        }
        return result;
    }


    public long[] getUserNumber() {
        long[] result = new long[numDays];
        ArrayList<Object> al= getAll();
        for(int i=0; i<numDays; i++){
            result[i]=DocumentToAnalytic((Document)al.get(i)).getUserCounter();
        }
        return result;
    }


    public List<Map<String, Long>> getLastLoginsByCountry() {
        return null;
    }



    @Override
    public void setLastLogin(long counted) {
        if(myAnalytic==null)
            myAnalytic = new Analytic();
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        String litYesterday = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(yesterday.getTime());
        myAnalytic.setDate(litYesterday);
        myAnalytic.setLastLogins(counted);
    }

    @Override
    public void setUserNumber(long counted) {
        if(myAnalytic==null)
            myAnalytic = new Analytic();
        myAnalytic.setUserCounter(counted);
    }

    @Override
    public void setLastLoginsByCountry(Map<String, Long> counted) {
        if(myAnalytic==null)
            myAnalytic = new Analytic();
        myAnalytic.setCountry(counted);
        insert(myAnalytic);
        removeOld();
    }

    private void removeOld(){
        Calendar old = Calendar.getInstance();
        old.add(Calendar.DAY_OF_MONTH, -1 * numDays);
        String threshold = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(old.getTime());
        Bson filter = lt("date", threshold);
        remove(filter);
    }





    @Override
    public boolean insert(Object toInsert) {
        if(!(toInsert instanceof Analytic))
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);  //also opens connection

        //TODO: Added Password Encryption, watch effects
        Analytic analyticToInsert = (Analytic) toInsert;
        Document doc = AnalyticToDocument(analyticToInsert);
        collection.insertOne(doc);
        closeConnection();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        MongoCollection<Document> collection = getCollection(collectionName);
        DeleteResult dr;
        if (o instanceof Analytic){
            dr = collection.deleteOne(eq("date", ((Analytic) o).getDate()));
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
        ArrayList<Object> analytics = new ArrayList<>();
        for(Document d:docs){
            analytics.add(DocumentToAnalytic(d));
        }
        closeConnection();
        return analytics;
    }

    @Override
    public ArrayList<Object> getWithFilter(Object filter) {
        if(!(filter instanceof Bson))
            return null;
        List<Document> docs= getCollection(collectionName).find((Bson)filter).into(new ArrayList<>());
        ArrayList<Object> analytics = new ArrayList<>();
        for(Document d:docs){
            analytics.add(DocumentToAnalytic(d));
        }
        closeConnection();
        return analytics;
    }

    @Override
    public boolean update(Object target, Object newValue) {
        MongoCollection<Document> collection = getCollection(collectionName);
        Document d = (Document)target;
        collection.updateOne(d, (Bson)newValue);
        closeConnection();
        return true;
    }

    @Override
    protected Object aggregate(List<Bson> pipeline) {
        return null;
    }
}
