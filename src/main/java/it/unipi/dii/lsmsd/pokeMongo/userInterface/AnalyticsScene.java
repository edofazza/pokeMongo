package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.dataAnalysis.AdminAnalysisFactory;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.charts.LineChartThirtyDaysFactory;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox.CountryComboBox;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.persistence.AdminAnalysisOnMongoDb;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalyticsScene extends PokeSceneWithHeaderAndBackButton {
    CountryComboBox countryComboBox;
    LineChart<Number, Number> loginByCountry;

    public AnalyticsScene() {

        Logger.log("SHOWING ANALYTICS PAGE");


        displayCountryButton();
        displayCharts();

    }

    private void displayCountryButton() {
        FieldRelatedLabel fieldRelatedLabel = new FieldRelatedLabel("Choose a country", 650, 142);

        try {
            countryComboBox = new CountryComboBox(650, 170);
            countryComboBox.setOnAction( e -> changeCountry());

            sceneNodes.getChildren().addAll(fieldRelatedLabel, countryComboBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayCharts() {
        displayNumUsersChart(10, 50, "Number of Users");

        displayLogIn(10, 320, "Number of Logins");

        displayLogInByCountry(650, 200, "Number of logins in ");
    }

    private void displayNumUsersChart(int x, int y, String yLabel) {
        LineChart<Number, Number> nUser = LineChartThirtyDaysFactory.getLineChartThirtyDays(570, 260, x, y, yLabel,300, 0, 50);
        //long[] array = AdminAnalysisFactory.buildRanker().getLastLogins();

        List<Long> list = AdminAnalysisFactory.buildRanker().getLastLogins();

        //for (long l: array)
        /*for (int i = 0; i < 30; i++)
            list.add(Long.parseLong("55"));*/

        LineChartThirtyDaysFactory.addDataToLineChartLong(nUser, list);

        sceneNodes.getChildren().addAll(nUser);
    }

    private void displayLogIn(int x, int y, String yLabel) {
        LineChart<Number, Number> nLogin = LineChartThirtyDaysFactory.getLineChartThirtyDays(570, 260, x, y, yLabel,300, 0, 50);
        //long[] array = AdminAnalysisFactory.buildRanker().getLastLogins();

        List<Long> list = new ArrayList<>();

        //for (long l: array)
        for (int i = 0; i < 30; i++)
            list.add(Long.parseLong("55"));

        LineChartThirtyDaysFactory.addDataToLineChartLong(nLogin, list);

        sceneNodes.getChildren().addAll(nLogin);
    }

    private void displayLogInByCountry(int x, int y, String yLabel) {
        loginByCountry = LineChartThirtyDaysFactory.getLineChartThirtyDays(570, 260, x, y, yLabel,300, 0, 50);
        sceneNodes.getChildren().addAll(loginByCountry);
        //long[] array = AdminAnalysisFactory.buildRanker().getLastLogins();

        if (countryComboBox.getValue().toString().equals(""))
            return;

        List<Long> list = new ArrayList<>();

        //for (long l: array)
        for (int i = 0; i < 30; i++)
            list.add(Long.parseLong("55"));

        LineChartThirtyDaysFactory.addDataToLineChartLong(loginByCountry, list);
    }

    private void changeCountry() {
        loginByCountry.getYAxis().setLabel("Number of logins in " + countryComboBox.getValue().toString());

        // QUERYING BY COUNTRY
    }
}
