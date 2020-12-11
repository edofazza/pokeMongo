package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.google.gson.Gson;

import it.unipi.dii.lsmsd.pokeMongo.bean.Analytic;
import it.unipi.dii.lsmsd.pokeMongo.dataAnalysis.AdminAnalysis;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;


public class AdminAnalysisOnMongoDb extends MongoDbDatabase implements AdminAnalysis {
    private String collectionName="analytic";
    private int numDays;

    public AdminAnalysisOnMongoDb(int numDays){
        this.numDays = numDays;
    }

    public AdminAnalysisOnMongoDb(){
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
        List<Map<String, Long>> result = new ArrayList<>();
        ArrayList<Object> al = getAll();
        for(Object o: al){
            Analytic a = DocumentToAnalytic((Document)o);
            Map<String, Long> map = new HashMap<>();
            for(int i=0; i<a.getCountry().length; i++){
                map.put(a.getCountry()[i].getName(), a.getCountry()[i].getLastLogins());
            }
            result.add(map);
        }
        return result;
    }





    @Override
    public boolean insert(ArrayList<Object> toInsert) {
        return false;
    }

    @Override
    public boolean insert(Object toInsert) {
        return true;
    }

    @Override
    public boolean remove(Object o) {
            return false;
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
        return false;
    }

    protected Object aggregate(List<Bson> pipeline) {
        return null;
    }
}
