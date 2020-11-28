package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import javafx.scene.media.MediaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//TODO riguarda
public class MusicPlayerTest {

    @Test
    public void WHEN_MusicPlayer_instanciated_with_empty_parameter_THEN_Throwable_Thrown(){
        Assertions.assertThrows(Throwable.class, () -> {
            MusicPlayer musicPlayer = new MusicPlayer("");
        });
    }

    @Test
    public void WHEN_changeSong_invoked_with_empty_parameter_THEN_Throwable_Thrown(){
        Assertions.assertThrows(Throwable.class, () -> {
            MusicPlayer musicPlayer = new MusicPlayer("catchemAll.mp3");
            musicPlayer.changeSong("");
        });
    }
}
