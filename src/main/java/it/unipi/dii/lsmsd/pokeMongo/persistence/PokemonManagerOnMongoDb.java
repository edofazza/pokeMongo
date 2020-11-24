package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.google.gson.*;
import com.mongodb.client.*;
import com.mongodb.client.result.*;
import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import org.bson.Document;
import org.bson.conversions.Bson;


import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public class PokemonManagerOnMongoDb extends MongoDbDatabase{
    private String collectionName = "pokemon";

    private Document PokemonToDocument(Pokemon p){
        return Document.parse(new Gson().toJson(p));
    }

    private Pokemon DocumentToPokemon(Document doc){
        return new Gson().fromJson(doc.toJson(), Pokemon.class);
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
    public boolean remove(Object o) {
        MongoCollection<Document> collection = getCollection(collectionName);
        DeleteResult dr=null;
        if (o instanceof Pokemon){
            dr = collection.deleteOne(eq("pokedexIndex", ((Pokemon) o).getPokedexIndex()));
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
        UpdateResult ur=null;
        if (target instanceof Pokemon){
            ur = collection.updateOne(eq("pokedexIndex", ((Pokemon) target).getPokedexIndex()),(Bson)newValue);
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
}
