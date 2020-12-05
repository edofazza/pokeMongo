package it.unipi.dii.lsmsd.pokeMongo.exceptions;

public class DuplicatePokemonException extends Exception{
    public DuplicatePokemonException(){
        super();
    }

    public DuplicatePokemonException(String text){
        super(text);
    }
}
