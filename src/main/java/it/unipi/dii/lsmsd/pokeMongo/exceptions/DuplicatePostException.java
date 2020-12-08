package it.unipi.dii.lsmsd.pokeMongo.exceptions;

public class DuplicatePostException extends Exception{
    public DuplicatePostException(){
        super();
    }

    public DuplicatePostException(String text){
        super(text);
    }
}
