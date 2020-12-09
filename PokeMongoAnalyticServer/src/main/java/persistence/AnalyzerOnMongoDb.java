package persistence;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.computed;
import static com.mongodb.client.model.Projections.fields;

public class AnalyzerOnMongoDb extends MongoDbDatabase implements Analyzer{
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
    protected Object aggregate(List<Bson> pipeline) {
        {
            MongoCollection<Document> collection = getCollection(collectionName);
            Document toReturn = collection.aggregate(pipeline).first();
            closeConnection();
            return toReturn;
        }
    }

    @Override
    public int getTodayLogin(){
        Calendar lastDay = Calendar.getInstance();
        lastDay.setTime(new Date());
        lastDay.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).format(lastDay);
        Bson match = match(and(gt("lastLogin", yesterday), ne("admin", true)));
        Bson count = group("$admin", sum("admin", 1));
        Bson project = project(fields(computed("loginNumber", "admin")));
        Document result = (Document)Arrays.asList(match, count, project);
        return result.getInteger("loginNumber").intValue();
    }

    @Override
    public int getUserNumber() {
        Bson match = match(ne("admin", true));
        Bson count = group("$admin", sum("admin", 1));
        Bson project = project(fields(computed("userNumber", "admin")));
        Document result = (Document)Arrays.asList(match, count, project);
        return result.getInteger("userNumber").intValue();
    }

    @Override
    public int getUserNumberByCountry() {
        Bson match = match(ne("admin", true));
        Bson count = group("$country", sum("country", 1));
        Bson project = project(fields(computed("userNumber", "admin")));
        //Document result = (Document)Arrays.asList(match, count, project);
        //return result.getInteger("userNumber").intValue();
        return 0;
    }
}
