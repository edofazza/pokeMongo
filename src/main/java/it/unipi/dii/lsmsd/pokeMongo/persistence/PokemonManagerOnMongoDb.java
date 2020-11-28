package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.*;

public class PokemonManagerOnMongoDb extends MongoDbDatabase implements PokemonManager{
    private final String collectionName = "pokemon";

    private Document PokemonToDocument(Pokemon p){
        return Document.parse(new Gson().toJson(p));
    }

    private Pokemon DocumentToPokemon(Document doc){
        return new Gson().fromJson(doc.toJson(), Pokemon.class);
    }

    private Bson translateToBson(Filter f, String value){
        try{
            switch(f){
                case NAME:
                    return eq("name", value);
                case POKEDEX_ID:
                    return eq("id", Integer.parseInt(value));
                case MIN_WEIGHT:
                    return gte("weight", Integer.parseInt(value));
                case MAX_WEIGHT:
                    return lte("weight", Integer.parseInt(value));
                case MIN_HEIGHT:
                    return gte("height", Integer.parseInt(value));
                case MAX_HEIGHT:
                    return lte("height", Integer.parseInt(value));
                case TYPE1:
                    return in("types", value);
                case TYPE2:
                    return in("types", value);
                case MIN_CATCH_RATE:
                    return gte("capture_rate", Double.parseDouble(value));
                case MAX_CATCH_RATE:
                    return lte("capture_rate", Double.parseDouble(value));
                /*case MIN_POINTS:
                    return gte("capture_rate", Double.parseDouble(value));
                case MAX_POINTS:
                    return lte("capture_rate", Double.parseDouble(value));*/
                default:
                    throw new Exception("String not recognized");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public boolean insert(ArrayList<Object> toInsert) {
        if(toInsert.size()==0)
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);  //also opens connection
        if(toInsert.size()==1){
            Document doc = PokemonToDocument((Pokemon)toInsert.get(0));
            collection.insertOne(doc);
        }
        else{
            List<Document> l = new ArrayList<>();
            for(Object o:toInsert){
                Document doc = PokemonToDocument((Pokemon)o);
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
    public ArrayList<Object> getWithFilter(Object filter) {
        List<Document> docs= getCollection(collectionName).find((Bson)filter).into(new ArrayList<>());
        ArrayList<Object> pokemons = new ArrayList<>();
        for(Document d:docs){
            pokemons.add(DocumentToPokemon(d));
        }
        closeConnection();
        return pokemons;
    }

    @Override
    public boolean update(Object target, Object newValue) {
        MongoCollection<Document> collection = getCollection(collectionName);
        UpdateResult ur;
        if (target instanceof Pokemon){
            ur = collection.updateOne(eq("id", ((Pokemon) target).getId()),(Bson)newValue);
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
    public ArrayList<Pokemon> searchWithFilter(Map<Filter, String> parameters) {
        ArrayList<Filter> keys = new ArrayList<>(parameters.keySet());
        Bson query;
        if(parameters.size()>1){
            Bson[] conditions= new Bson[parameters.size()];
            for(int i=0; i< parameters.size(); i++){
                Filter key = keys.get(0);
                conditions[i] = translateToBson(key, parameters.get(key));
            }
            query = and(conditions);

        }
        else{
            Filter key = keys.get(0);
            query = translateToBson(key,parameters.get(key));
        }
        ArrayList<Pokemon> result = new ArrayList<>();
        ArrayList<Object> matched = getWithFilter(query);
        for(Object o:matched)
            result.add((Pokemon)o);
        return result;
    }

    @Override
    public boolean addPokemon(Pokemon toAdd) {
        return insert(toAdd);
    }

    @Override
    public boolean addPokemon(List<Pokemon> toAdd) {
        return insert(toAdd);
    }

    @Override
    public boolean removePokemon(Pokemon toRemove) {
        return remove(toRemove);
    }
}
