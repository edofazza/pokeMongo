package persistence;

import bean.Analytic;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
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

    private Document AnalyticToDocument(Analytic a){
        return Document.parse(new Gson().toJson(a));
    }

    private Analytic DocumentToAnalytic(Document d){
        return new Gson().fromJson(d.toJson(), Analytic.class);
    }


    @Override
    public long[] getLastLogins() {
        return new long[0];
    }

    @Override
    public long[] getUserNumber() {
        return new long[0];
    }

    @Override
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
    public ArrayList<Object> getAll() {
        MongoCollection<Document> collection = getCollection(collectionName);
        ArrayList<Object> result = collection.find().into(new ArrayList<>());
        closeConnection();
        return result;
    }

    @Override
    public ArrayList<Object> getWithFilter(Object filter) {
        MongoCollection<Document> collection = getCollection(collectionName);
        ArrayList<Object> result = collection.find((Bson)filter).into(new ArrayList<>());
        closeConnection();
        return result;
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
