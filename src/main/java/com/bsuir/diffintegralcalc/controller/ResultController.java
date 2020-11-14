package com.bsuir.diffintegralcalc.controller;

import com.bsuir.diffintegralcalc.data.CalculationResult;
import com.bsuir.funapproximation.util.ViewUtils;
import com.bsuir.linearsystem.model.Vector;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
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

        ViewUtils.addSeries(graphicsF, "f(x)", xVector, results.getYVector());

        ViewUtils.addSeries(graphicsD1, "d't(x)", xVector, results.getD1tVector());
        Vector[] d1Vectors = results.getD1Vectors();
        Vector[] delD1Vectors = results.getDelD1Vectors();
        for (int i = 0; i < HP_MAS.length; i++) {
            ViewUtils.addSeries(graphicsD1, "d'(x), hp=" + HP_MAS[i], xVector, d1Vectors[i]);
            ViewUtils.addSeries(graphicsD1, "Δ'(x), hp=" + HP_MAS[i], xVector, delD1Vectors[i]);
        }

        ViewUtils.addSeries(graphicsD2, "d''t(x)", xVector, results.getD2tVector());
        Vector[] d2Vectors = results.getD2Vectors();
        Vector[] delD2Vectors = results.getDelD2Vectors();
        for (int i = 0; i < HP_MAS.length; i++) {
            ViewUtils.addSeries(graphicsD2, "d''(x), hp=" + HP_MAS[i], xVector, d2Vectors[i]);
            ViewUtils.addSeries(graphicsD2, "Δ''(x), hp=" + HP_MAS[i], xVector, delD2Vectors[i]);
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
}
