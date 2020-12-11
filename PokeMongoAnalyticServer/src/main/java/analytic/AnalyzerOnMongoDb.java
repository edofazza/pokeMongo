package analytic;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;
import persistence.MongoDbDatabase;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

class AnalyzerOnMongoDb extends MongoDbDatabase implements Analyzer{
    private String collectionName = "user";

    @Override
    public boolean insert(Object toInsert) { return false;}

    @Override
    public ArrayList<Object> getAll() {
        return null;
    }

    @Override
    public ArrayList<Object> getWithFilter(Object filter) {
        return null;
    }

    @Override
    public boolean update(Object target, Object newValue) { return false;}

    @Override
    public boolean remove(Object o) { return false;}

    @Override
    protected List<Document> aggregate(List<Bson> pipeline) {
        MongoCollection<Document> collection = getCollection(collectionName);
        List<Document> toReturn = collection.aggregate(pipeline).into(new ArrayList<>());
        closeConnection();
        return toReturn;
    }

    @Override
    public long getTodayLogin(){
        Calendar lastDay = Calendar.getInstance();
        lastDay.setTime(new Date());
        lastDay.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).format(lastDay.getTime());
        Bson match = match(and(gte("lastLogin", yesterday), ne("admin", true)));
        Bson count = group("$admin", sum("loginNumber", 1));
        Bson project = project(fields(include("loginNumber")));
        Document result = aggregate(Arrays.asList(match, count, project)).get(0);
        return result.getInteger("loginNumber").longValue();
    }

    @Override
    public long getUserNumber() {
        Bson match = match(ne("admin", true));
        Bson count = group("$admin", sum("userNumber", 1));
        Bson project = project(fields(include("userNumber")));
        Document result = aggregate(Arrays.asList(match, count, project)).get(0);
        return result.getInteger("userNumber").longValue();
    }

    @Override
    public Map<String, Long> getLastLoginsByCountry() {
        Calendar lastDay = Calendar.getInstance();
        lastDay.setTime(new Date());
        lastDay.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).format(lastDay.getTime());
        Bson match = match(and(gte("lastLogin", yesterday), ne("admin", true)));
        Bson count = group("$country", sum("lastLogin", 1));
        Bson sort = sort(descending("lastLogin"));
        Bson limit = limit(15);
        Bson project = project(fields(include("country", "lastLogin")));
        List<Document> result = aggregate(Arrays.asList(match, count, sort, limit, project));
        Map<String, Long> map = new HashMap<>();
        for(Document d: result)
            map.put(d.getString("country"), d.getInteger("lastLogin").longValue());
        return map;
    }
}
