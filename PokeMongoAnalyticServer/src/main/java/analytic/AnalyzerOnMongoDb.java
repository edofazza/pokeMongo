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

class AnalyzerOnMongoDb extends MongoDbDatabase implements Analyzer{
    private String collectionName = "user";
    @Override
    public ArrayList<Object> getAll() {
        return null;
    }

    @Override
    public ArrayList<Object> getWithFilter(Object filter) {
        return null;
    }

    @Override
    protected List<Document> aggregate(List<Bson> pipeline) {
        {
            MongoCollection<Document> collection = getCollection(collectionName);
            List<Document> toReturn = collection.aggregate(pipeline).into(new ArrayList<>());
            closeConnection();
            return toReturn;
        }
    }

    @Override
    public long getTodayLogin(){
        Calendar lastDay = Calendar.getInstance();
        lastDay.setTime(new Date());
        lastDay.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).format(lastDay);
        Bson match = match(and(gt("lastLogin", yesterday), ne("admin", true)));
        Bson count = group("$admin", sum("admin", 1));
        Bson project = project(fields(computed("loginNumber", "admin")));
        Document result = aggregate(Arrays.asList(match, count, project)).get(0);
        return result.getLong("loginNumber").longValue();
    }

    @Override
    public long getUserNumber() {
        Bson match = match(ne("admin", true));
        Bson count = group("$admin", sum("admin", 1));
        Bson project = project(fields(computed("userNumber", "admin")));
        Document result = aggregate(Arrays.asList(match, count, project)).get(0);
        return result.getLong("userNumber").longValue();
    }

    @Override
    public Map<String, Long> getUserNumberByCountry() {
        Bson match = match(ne("admin", true));
        Bson count = group("$country", sum("userNumber", 1));
        Bson project = project(fields(include("country", "userNumber")));
        List<Document> result = aggregate(Arrays.asList(match, count, project));
        Map<String, Long> map = new HashMap<>();
        for(Document d: result)
            map.put(d.getString("country"), d.getLong("userNumber").longValue());
        return map;
    }
}
