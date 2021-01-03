import analytic.Analyzer;
import analytic.AnalyzerFactory;
import bean.Pokemon;
import bean.PokemonAndCatchRate;
import javafx.util.Pair;
import persistence.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class ServerStartup {
    private static boolean started=false;

    public static void main(String[] args){
//       try {
//            while (true) {
//                long timeToSleep;
//                if (!started) {         //first time starts at 00:00:01
//                    Calendar c = Calendar.getInstance();
//                    c.add(Calendar.DATE, 1);
//                    c.set(Calendar.HOUR_OF_DAY, 0);
//                    c.set(Calendar.MINUTE, 0);
//                    c.set(Calendar.SECOND, 1);
//                    c.set(Calendar.MILLISECOND, 0);
//                    timeToSleep = (c.getTimeInMillis()-System.currentTimeMillis());
//                    started=true;
//                } else {
//                    timeToSleep=24*60*60*1000; //once per day
//                }
//                Thread.sleep(timeToSleep);
                updateAll();
//            }
//        }
//        catch (InterruptedException i){
//            System.out.println(i.getMessage());
//            i.printStackTrace();
//        }
    }

    private static void updateAll(){
        Analyzer a = AnalyzerFactory.buildAnalyzer();
        long lastLogins = a.getTodayLogin();
        long userNumber = a.getUserNumber();
        Map<String, Long> map = a.getLastLoginsByCountry();

        AnalyticStorage as = AnalyticStorageFactory.buildAnalyzer();
        as.setLastLogin(lastLogins);
        as.setUserNumber(userNumber);
        as.setLastLoginsByCountry(map);

        //DYNAMIC CATCH RATE STUFF
        PokemonManager pokemonManager = PokemonManagerFactory.buildManager();
        TeamManager teamManager = TeamManagerFactory.buildManager();

        long start = System.currentTimeMillis();
        ArrayList<Pokemon> pokemons = pokemonManager.getEveryPokemon();
        long end = System.currentTimeMillis();
        System.out.println("getEveryPokemon: " + (end - start));

        start = System.currentTimeMillis();
        List<Pair<String, Integer>> trainersPerPokemon = teamManager.getUsersNumberThatOwnsAPokemonNotFiltered();
        List<PokemonAndCatchRate> catchRatesOfPokemons = new ArrayList<>();
        end = System.currentTimeMillis();
        System.out.println("trainserPerPokemon: " + (end - start));

        int index = 0;
        int numTrainers;
        double new_catch_rate;
        Pokemon oldPokemon;
        List<Double> capture_rates;
        for(Pokemon p: pokemons){
            oldPokemon = new Pokemon(p.getName(), p.getTypes(), p.getId(), p.getCapture_rate(), p.getCapture_rates(), (int)p.getHeight(), (int)p.getWeight(), p.getBiology(), p.getPortrait(), p.getSprite());

            Pair<String, Integer> currentTrainers = trainersPerPokemon.get(index);
            if(trainersPerPokemon.get(index).getKey().equals(p.getName())){
                numTrainers = currentTrainers.getValue();
                index++;
            } else {
                numTrainers = 0;
            }

            new_catch_rate = p.getCapture_rate()*(1 - (numTrainers*1.0)/(userNumber + 1));

            //System.out.println(p.getName() + "|" + currentTrainers.getKey() + " " + numTrainers + " " + userNumber + " " + new_catch_rate);
            capture_rates = p.getCapture_rates();

            if(capture_rates.size() >= 30){
                while(capture_rates.size() >= 30)
                    capture_rates.remove(capture_rates.size() - 1);
            }




            capture_rates.add(0, new_catch_rate);
//            for(Double d: capture_rates){
//                System.out.println(p.getName() + " " + d.doubleValue());
//            }
            long start3 = System.currentTimeMillis();
            long count = pokemonManager.updatePokemon(oldPokemon, p);
            long end3 = System.currentTimeMillis();
            System.out.println("Updated " + count + " rows. Duration: " + (end3 - start3));
            catchRatesOfPokemons.add(new PokemonAndCatchRate(p.getName(), new_catch_rate));
        }
        teamManager.updateCatchRateOfPokemon(catchRatesOfPokemons);
    }
}
