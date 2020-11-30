package it.unipi.dii.lsmsd.pokeMongo.userInterface;

import it.unipi.dii.lsmsd.pokeMongo.utils.Logger;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class AnalyticsScene extends PokeSceneWithHeaderAndBackButton {
    public AnalyticsScene() {

        Logger.log("SHOWING ANALYTICS PAGE");
        for (int i = 0; i < 3; i++)
            chart(80+i*400, 140);

        displaySelectYear();
    }

    private void chart(int x, int y) {
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Days");
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("#Users");
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("Total user in the last 30days");

        series.getData().add(new XYChart.Data<>(1, 200));
        series.getData().add(new XYChart.Data<>(20, 100));
        series.getData().add(new XYChart.Data<>(30, 300));

        lineChart.getData().add(series);

        lineChart.setPrefSize(350, 350);
        lineChart.relocate(x, y);

        sceneNodes.getChildren().add(lineChart);
    }

    private void displaySelectYear() {

    }
}
