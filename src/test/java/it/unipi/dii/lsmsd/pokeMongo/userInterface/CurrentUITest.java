package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CurrentUITest {

    @Test
    public void WHEN_changeScene_invoked_with_Different_scene_name_THEN_IllegalArgumentException_Thrown(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            CurrentUI.changeScene(SceneNames.valueOf("PROVA"));
        });
    }
}
