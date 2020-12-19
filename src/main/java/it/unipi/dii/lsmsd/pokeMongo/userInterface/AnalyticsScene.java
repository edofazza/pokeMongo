package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.dataAnalysis.AdminAnalysisFactory;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.charts.LineChartThirtyDaysFactory;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.comboBox.CountryComboBox;
import it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.labels.FieldRelatedLabel;
import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.chart.LineChart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Scene related to the analytics (admin specific).
 */
public class AnalyticsScene extends PokeSceneWithHeaderAndBackButton {
    CountryComboBox countryComboBox;
    LineChart<Number, Number> loginByCountry;

    /**
     * <em>Constructor</em>. Calls a series of function in order to create the <em>Node</em>
     * needed to set the scene up. It also sets the music.
     */
    public AnalyticsScene() {
        Logger.log("SHOWING ANALYTICS PAGE");

        displayCountryButton();
        displayCharts();

    }

    /**
     * Add the <code>CountryComboBox</code> to the scene
     */
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

    /**
     * Calls a series of function for adding the charts related to the different analytics
     */
    private void displayCharts() {
        displayNumUsersChart(10, 50, "Number of Users");

        displayLogIn(10, 320, "Number of Logins");

        displayLogInByCountry(650, 200, "Number of logins in ");
    }

    /**
     * Add the specific chart
     * @param x position
     * @param y position
     * @param yLabel label in the y axis
     */
    private void displayNumUsersChart(int x, int y, String yLabel) {
        LineChart<Number, Number> nUser = LineChartThirtyDaysFactory.getLineChartThirtyDays(570, 260, x, y, yLabel,170000, 0, 10000);
        List<Long> list = AdminAnalysisFactory.buildRanker().getUserNumber();

        LineChartThirtyDaysFactory.addDataToLineChartLong(nUser, list);

        sceneNodes.getChildren().addAll(nUser);
    }

    /**
     * Add the specific chart
     * @param x position
     * @param y position
     * @param yLabel label in the y axis
     */
    private void displayLogIn(int x, int y, String yLabel) {
        LineChart<Number, Number> nLogin = LineChartThirtyDaysFactory.getLineChartThirtyDays(570, 260, x, y, yLabel,170000, 0, 10000);
        List<Long> list = AdminAnalysisFactory.buildRanker().getLastLogins();

        LineChartThirtyDaysFactory.addDataToLineChartLong(nLogin, list);

        sceneNodes.getChildren().addAll(nLogin);
    }

    /**
     * Add the specific chart
     * @param x position
     * @param y position
     * @param yLabel label in the y axis
     */
    private void displayLogInByCountry(int x, int y, String yLabel) {
        loginByCountry = LineChartThirtyDaysFactory.getLineChartThirtyDays(570, 260, x, y, yLabel,20000, 0, 50);
        sceneNodes.getChildren().addAll(loginByCountry);

        if (countryComboBox.getValue().toString().equals(""))
            return;
    }

    /**
     * Event related to the change of the country. When the admin changes the country the system will low the login
     * information specific to that country.
     */
    private void changeCountry() {
        if (countryComboBox.getValue().toString().equals("")) {
            loginByCountry.getData().clear();
            return;
        }

        loginByCountry.getYAxis().setLabel("Number of logins in " + countryComboBox.getValue().toString());

        // QUERYING BY COUNTRY
        List<Map<String, Long>> listOfMap = AdminAnalysisFactory.buildRanker().getLastLoginsByCountry();
        List<Long> list = new ArrayList<>();

        for (Map<String, Long> m: listOfMap) {
            list.add(m.get(countryComboBox.getValue().toString()));
        }

        LineChartThirtyDaysFactory.addDataToLineChartLong(loginByCountry, list);
    }
}
