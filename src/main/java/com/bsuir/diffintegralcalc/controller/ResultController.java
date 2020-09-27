package com.bsuir.diffintegralcalc.controller;

import com.bsuir.diffintegralcalc.data.CalculationResult;
import com.bsuir.linearsystem.model.Vector;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static com.bsuir.diffintegralcalc.utils.TaskUtils.HP_MAS;
import static com.bsuir.diffintegralcalc.utils.TaskUtils.M_MAS;

public class ResultController {
    @FXML
    private LineChart<Number, Number> graphicsF;

    @FXML
    private LineChart<Number, Number> graphicsD1;

    @FXML
    private LineChart<Number, Number> graphicsD2;

    @FXML
    private VBox integralResults;

    public void initResults(CalculationResult results) {
        Vector xVector = results.getXVector();

        addSeries(graphicsF, "f(x)", xVector, results.getYVector());

        addSeries(graphicsD1, "d't(x)", xVector, results.getD1tVector());
        Vector[] d1Vectors = results.getD1Vectors();
        Vector[] delD1Vectors = results.getDelD1Vectors();
        for (int i = 0; i < HP_MAS.length; i++) {
            addSeries(graphicsD1, "d'(x), hp=" + HP_MAS[i], xVector, d1Vectors[i]);
            addSeries(graphicsD1, "Δ'(x), hp=" + HP_MAS[i], xVector, delD1Vectors[i]);
        }

        addSeries(graphicsD2, "d''t(x)", xVector, results.getD2tVector());
        Vector[] d2Vectors = results.getD2Vectors();
        Vector[] delD2Vectors = results.getDelD2Vectors();
        for (int i = 0; i < HP_MAS.length; i++) {
            addSeries(graphicsD2, "d''(x), hp=" + HP_MAS[i], xVector, d2Vectors[i]);
            addSeries(graphicsD2, "Δ''(x), hp=" + HP_MAS[i], xVector, delD2Vectors[i]);
        }

        Label exactIntegralValueLabel = new Label("Точное значение c=" + results.getIntegralExactValue());
        integralResults.getChildren().add(exactIntegralValueLabel);
        double[] integralValues = results.getIntegralValues();
        double[] delIntegralValues = results.getDelIntegralValues();
        for (int i = 0; i < M_MAS.length; i++) {
            Label calculatedIntegralValueLabel = new Label("Значение (при m=" + M_MAS[i] + ") c="
                    + integralValues[i] + ", Δ=" + delIntegralValues[i]);
            integralResults.getChildren().add(calculatedIntegralValueLabel);
        }
    }

    private void addSeries(LineChart<Number, Number> graphics, String name, Vector xVector, Vector yVector) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(name);
        ObservableList<XYChart.Data<Number,Number>> seriesData = series.getData();

        int m = xVector.len();
        for (int i = 0; i < m; i++) {
            seriesData.add(new XYChart.Data<>(xVector.get(i), yVector.get(i)));
        }

        graphics.getData().add(series);
    }
}
