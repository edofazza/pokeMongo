package persistence;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Aggregates.*;

public class AnalyzerOnMongoDb extends MongoDbDatabase{
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

    public int getTodaysLogin(){
        Calendar lastDay = Calendar.getInstance();
        lastDay.setTime(new Date());
        lastDay.add(Calendar.DATE, -1);
        //Bson match = match(gt());
        return 0;
    }
}
