package it.unipi.dii.lsmsd.pokeMongo.exceptions;

public class DuplicateUserException extends Exception{
    public DuplicateUserException(){
        super();
    }

    public DuplicateUserException(String text){
        super(text);
    }
}
