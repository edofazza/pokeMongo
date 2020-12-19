package persistence;

import bean.Pokemon;
import bean.PokemonAndCatchRate;
import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.util.Pair;
import org.bson.*;
import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;

import static org.neo4j.driver.Values.parameters;

public class TeamManagerOnNeo4j extends Neo4jDbDatabase implements TeamManager{

    //FOR DYNAMIC CATCH RATE
    public int getUsersNumberThatOwnAPokemon(Pokemon p){
        String query = "MATCH (p:Pokemon)<-[w:HAS]-(u:User) WHERE p.name = $name RETURN count(u) as user_numbers";
        ArrayList<Object> res = getWithFilter(query, parameters("name", p.getName()));
        Record r = (Record)res.get(0);
        int num = r.get("user_numbers").asInt();
        return num;
    }

    public List<Pair<String, Integer>> getUsersNumberThatOwnsAPokemonNotFiltered(){
        String query = " MATCH (p:Pokemon)<-[w:HAS]-(u:User) \n" +
                "RETURN p.name, count(u) as user_count";
        ArrayList<Object> res = getWithFilter(query);
        List<Pair<String, Integer>> return_list = new ArrayList<>();
        for(Object o: res){
            Record r = (Record) o;
            int num = r.get("user_count").asInt();
            String name = r.get("p.name").asString();
            return_list.add(new Pair<>(name, num));
        }
        return return_list;
    }

    public void updateCatchRateOfPokemon(List<PokemonAndCatchRate> catch_rates){
        String start = "[";
        for(int i=0; i<catch_rates.size(); i++){
            start += "{name:\"" + catch_rates.get(i).name + "\"," + "catchRate:" + catch_rates.get(i).catchRate + "}";
            if(i < catch_rates.size() - 1)
                start += ",";
        }
        start += "]";
        System.out.println(start);


        String query = "UNWIND " + start + "as catch " +
         "MATCH (p:Pokemon{name: catch.name}) SET p.capture_rate = catch.catchRate";
        update(query, parameters());

    }

}
