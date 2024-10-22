package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CountryComboBox extends ComboBox {

    /**
     *
     * @param x the x axis position
     * @param y the y axis position
     * @throws IOException possible caused by reading a file
     */
    public CountryComboBox(int x, int y) throws IOException {
        Logger.vvlog("Creating CountryComboBox at (" + x + ", " + y + ")");
        relocate(x, y);
        List<String> myList = Files.lines(Paths.get("txt/countries.txt")).collect(Collectors.toList());
        setItems(FXCollections.observableArrayList(myList));
        setValue("");

        getStyleClass().add("CountryComboBox");
    }
}
