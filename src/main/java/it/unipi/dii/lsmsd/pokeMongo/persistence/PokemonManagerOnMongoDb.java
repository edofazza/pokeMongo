package it.unipi.dii.lsmsd.pokeMongo.persistence;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import it.unipi.dii.lsmsd.pokeMongo.bean.Pokemon;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

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
        MongoCollection<Document> collection = getCollection(collectionName);
        if(toInsert.size()==0)
            return false;
        else if(toInsert.size()==1){
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
        return true;
    }

    @Override
    public boolean remove(Object o) {
        MongoCollection<Document> collection = getCollection(collectionName);
        return false;
    }

    @Override
    public ArrayList<Object> getAll() {
        List<Document> docs= getCollection(collectionName).find().into(new ArrayList<>());
        ArrayList<Object> pokemons = new ArrayList<>();
        for(Document d:docs){
            pokemons.add((Object)DocumentToPokemon(d));
        }
        return pokemons;
    }

    @Override
    public ArrayList<Object> getWithFilter(Object filter) {
        return null;
    }

    @Override
    public boolean update(Object target, Object newValue) {
        return false;
    }
}
