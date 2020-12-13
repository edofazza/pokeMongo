package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.charts;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.List;

public class LineChartThirtyDaysFactory {
    public static LineChart getLineChartThirtyDays(double width, double height, double x, double y, String yLabelName, int yMax, int yMin, int yTick){
        final NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Days");
        xAxis.setAutoRanging(false);
        xAxis.setTickUnit(3);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(30);
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yLabelName);
        yAxis.setAutoRanging(false);
        yAxis.setTickUnit(yTick);
        yAxis.setLowerBound(yMin);
        yAxis.setUpperBound(yMax);
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setPrefSize(width, height);
        lineChart.relocate(x, y);
        return lineChart;
    }



    public static void addDataToLineChart(LineChart lineChart, List<Double> xy){
        lineChart.getData().clear();

        XYChart.Series series = new XYChart.Series();
        int i=0;
        for(Double p: xy){
            series.getData().add(new XYChart.Data<>(i,p));
            i++;
        }

        lineChart.getData().add(series);
        lineChart.setLegendVisible(false);
        lineChart.setCreateSymbols(false);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(3);
        dropShadow.setColor(Color.GRAY);
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        series.getNode().setEffect(dropShadow);
        series.getNode().setStyle("-fx-stroke: #34abeb;");
    }

    public static void addDataToLineChartLong(LineChart lineChart, List<Long> xy){
        lineChart.getData().clear();

        XYChart.Series series = new XYChart.Series();
        int i=0;
        for(Long p: xy){
            series.getData().add(new XYChart.Data<>(i,p));
            i++;
        }

        lineChart.getData().add(series);
        lineChart.setLegendVisible(false);
        lineChart.setCreateSymbols(false);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(3);
        dropShadow.setColor(Color.GRAY);
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        series.getNode().setEffect(dropShadow);
        series.getNode().setStyle("-fx-stroke: #34abeb;");
    }
}
