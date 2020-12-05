package it.unipi.dii.lsmsd.pokeMongo.exceptions;

public class SlotAlreadyOccupiedException extends Exception{
    public SlotAlreadyOccupiedException(){
        super();
    }

    public SlotAlreadyOccupiedException(String text){
        super(text);
    }
}
