package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TypeComboBox extends ComboBox {
    public TypeComboBox(int x, int y) throws IOException {
        Logger.vvlog("Creating TypeComboBox at (" + x + ", " + y + ")");
        relocate(x, y);
        List<String> myList = Files.lines(Paths.get("types.txt")).collect(Collectors.toList());
        setItems(FXCollections.observableArrayList(myList));
        setValue("");

        getStyleClass().add("CountryComboBox");
    }
}


