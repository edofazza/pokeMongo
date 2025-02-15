package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static it.unipi.dii.lsmsd.pokeMongo.persistence.Filter.*;

class PokemonManagerOnMongoDb extends MongoDbDatabase implements PokemonManager{
    private final String collectionName = "pokemon";

    private Document PokemonToDocument(Pokemon p){
        Logger.vvlog("Getting document from Pokemon\nDocument: " + new Gson().toJson(p));
        return Document.parse(new Gson().toJson(p));
    }

    private Pokemon DocumentToPokemon(Document doc){
        Logger.vvlog("Getting Pokemon from Document\nDocument: " + doc.toJson());
        return new Gson().fromJson(doc.toJson(), Pokemon.class);
    }

    private Bson queryBuilder(Map<Filter, String> params){

       Document toReturn=new Document();
       if(params.get(NAME)!=null)
           toReturn.append("name", params.get(NAME));

       if(params.get(POKEDEX_ID)!=null)
           toReturn.append("id", Integer.parseInt(params.get(POKEDEX_ID)));

       if(params.get(MIN_WEIGHT)!=null && params.get(MAX_WEIGHT)!=null)
           toReturn.append("weight", new Document("$gte", Integer.parseInt(params.get(MIN_WEIGHT))).append("$lte", Integer.parseInt(params.get(MAX_WEIGHT))));
       else if(params.get(MIN_WEIGHT)!=null)
           toReturn.append("weight", new Document("$gte", Integer.parseInt(params.get(MIN_WEIGHT))));
       else if(params.get(MAX_WEIGHT)!=null)
           toReturn.append("weight", new Document("$lte", Integer.parseInt(params.get(MAX_WEIGHT))));


        if(params.get(MIN_HEIGHT)!=null && params.get(MAX_HEIGHT)!=null)
            toReturn.append("height", new Document("$gte", Integer.parseInt(params.get(MIN_HEIGHT))).append("$lte", Integer.parseInt(params.get(MAX_HEIGHT))));
        else if(params.get(MIN_HEIGHT)!=null)
            toReturn.append("height", new Document("$gte", Integer.parseInt(params.get(MIN_HEIGHT))));
        else if(params.get(MAX_HEIGHT)!=null)
            toReturn.append("height", new Document("$lte", Integer.parseInt(params.get(MAX_HEIGHT))));

        if(params.get(TYPE1)!=null || params.get(TYPE2)!=null){
            if(params.get(TYPE1)!=null && params.get(TYPE2)!=null){
                toReturn.append("types", Document.parse("{$all : [\"" + params.get(TYPE1) + "\", \"" + params.get(TYPE2) + "\"]}"));
            }
            else if(params.get(TYPE1)!=null)
                toReturn.append("types", params.get(TYPE1));
            else if(params.get(TYPE2)!=null)
                toReturn.append("types", params.get(TYPE2));
        }


        if(params.get(MIN_CATCH_RATE)!=null && params.get(MAX_CATCH_RATE)!=null)
            toReturn.append("capture_rate", new Document("$gte", Integer.parseInt(params.get(MIN_CATCH_RATE))).append("$lte", Integer.parseInt(params.get(MAX_CATCH_RATE))));
        else if(params.get(MIN_CATCH_RATE)!=null)
            toReturn.append("capture_rate", new Document("$gte", Integer.parseInt(params.get(MIN_CATCH_RATE))));
        else if(params.get(MAX_CATCH_RATE)!=null)
            toReturn.append("capture_rate", new Document("$lte", Integer.parseInt(params.get(MAX_CATCH_RATE))));
        Logger.vlog("Query built: " + toReturn.toJson());
        return toReturn;
    }


    @Override
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
            Logger.vvlog("ADDED " + doc.toJson());
            collection.insertOne(doc);
        }
        else{
            List<Document> l = new ArrayList<>();
            for(Object o:toInsert){
                if(!(o instanceof Pokemon))
                    return false;
                Document doc = PokemonToDocument((Pokemon)o);
                Logger.vvlog("ADDED " + doc.toJson());
                l.add(doc);
            }
            collection.insertMany(l);
        }
        return true;
    }

    @Override
    @VisibleForTesting
    public boolean insert(Object toInsert) {
        if(!(toInsert instanceof Pokemon))
            return false;
        MongoCollection<Document> collection = getCollection(collectionName);  //also opens connection
        Document doc = PokemonToDocument((Pokemon)toInsert);
        Logger.vvlog("ADDED " + doc.toJson());
        collection.insertOne(doc);
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

        Logger.vvlog("DELETED " + dr.getDeletedCount() + " pokemon");
        return dr.getDeletedCount()>0;
    }

    @Override
    public ArrayList<Object> getAll() {
        List<Document> docs= getCollection(collectionName).find().into(new ArrayList<>());
        ArrayList<Object> pokemons = new ArrayList<>();
        for(Document d:docs){
            pokemons.add(DocumentToPokemon(d));
        }
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
            ur = collection.updateOne(eq("id", ((Pokemon) target).getId()),(Bson)newValue);
        }
        else if(target instanceof Bson){
            ur = collection.updateMany((Bson)target, (Bson)newValue);
        }
        else {
            return false;
        }
        Logger.vvlog("UPDATED " + ur.getModifiedCount() + " pokemon");
        return ur.getModifiedCount()>0;
    }


    @Override
    public ArrayList<Pokemon> searchWithFilter(Map<Filter, String> parameters) {
        ArrayList<Filter> keys = new ArrayList<>(parameters.keySet());
        Bson query = queryBuilder(parameters);
        Logger.vvlog("SEARCH WITH FILTER query: " + query);
        ArrayList<Pokemon> result = new ArrayList<>();
        ArrayList<Object> matched = getWithFilter(query);
        for(Object o:matched)
            result.add((Pokemon)o);
        return result;
    }

    @Override
    public ArrayList<Pokemon> getEveryPokemon(){
        ArrayList<Object> pokemons = getAll();
        ArrayList<Pokemon> result = new ArrayList<>();
        for(Object o: pokemons)
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

    @Override
    public boolean removePokemon(String name) {
        return remove(eq("name", name));
    }
}
