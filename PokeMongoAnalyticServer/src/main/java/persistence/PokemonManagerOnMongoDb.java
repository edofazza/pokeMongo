package persistence;

import bean.Pokemon;
import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;



class PokemonManagerOnMongoDb extends MongoDbDatabase implements PokemonManager{
    private final String collectionName = "pokemon";

    private Document PokemonToDocument(Pokemon p){
        return Document.parse(new Gson().toJson(p));
    }

    private Pokemon DocumentToPokemon(Document doc){
//        System.out.println(doc);
        return new Gson().fromJson(doc.toJson(), Pokemon.class);
    }


    @VisibleForTesting
    public boolean insert(ArrayList<Object> toInsert) {
        if(toInsert.size()==0)
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);  //also opens connection
        if(toInsert.size()==1){
            if(!(toInsert.get(0) instanceof Pokemon)){
                closeConnection();
                return false;
            }
            Document doc = PokemonToDocument((Pokemon)toInsert.get(0));
            collection.insertOne(doc);
        }
        else{
            List<Document> l = new ArrayList<>();
            for(Object o:toInsert){
                if(!(o instanceof Pokemon))
                    return false;
                Document doc = PokemonToDocument((Pokemon)o);
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
        if(!(toInsert instanceof Pokemon))
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);  //also opens connection
        Document doc = PokemonToDocument((Pokemon)toInsert);
        collection.insertOne(doc);
        closeConnection();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        MongoCollection<Document> collection = getCollection(collectionName);
        DeleteResult dr;
        if (o instanceof Pokemon){
            dr = collection.deleteOne(eq("id", ((Pokemon) o).getId()));
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
        ArrayList<Object> pokemons = new ArrayList<>();
        for(Document d:docs){
            pokemons.add(DocumentToPokemon(d));
        }
        closeConnection();
        return pokemons;
    }

    @Override
    @VisibleForTesting
    public ArrayList<Object> getWithFilter(Object filter) {
        if(!(filter instanceof Bson ))
            return null;
        List<Document> docs= getCollection(collectionName).find((Bson) filter).into(new ArrayList<>());
        ArrayList<Object> pokemons = new ArrayList<>();
        for(Document d:docs){
            pokemons.add(DocumentToPokemon(d));
        }
        closeConnection();
        return pokemons;
    }

    @Override
    @VisibleForTesting
    public boolean update(Object target, Object newValue) {
        if(!(newValue instanceof Bson))
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);
        UpdateResult ur;
        if (target instanceof Pokemon){
            System.out.println( ((Pokemon) target).getId());
            ur = collection.updateOne(eq("id", ((Pokemon) target).getId()), (Bson)newValue);
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
    protected Object aggregate(List<Bson> pipeline) {
        return null;
    }


    @Override
    public ArrayList<Pokemon> getEveryPokemon(){
        ArrayList<Object> pokemons = getAll();
        ArrayList<Pokemon> result = new ArrayList<>();
        for(Object o: pokemons)
            result.add((Pokemon)o);
        return result;
    }

    public long updatePokemon(Pokemon old_p, Pokemon new_p){
        MongoCollection<Document> collection = getCollection(collectionName);
        UpdateResult ur = null;
        if (old_p instanceof Pokemon){
            System.out.println(old_p.getId());
            ur = collection.updateOne(eq("id", old_p.getId()), set("capture_rates", new_p.getCapture_rates()));
        }
        return ur.getModifiedCount();
    }
}
