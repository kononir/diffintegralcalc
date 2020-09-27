package com.bsuir.diffintegralcalc.controller;

import com.bsuir.diffintegralcalc.data.CalculationResult;
import com.bsuir.diffintegralcalc.logic.DifferentialCalculationService;
import com.bsuir.diffintegralcalc.logic.IntegralCalculationService;
import com.bsuir.diffintegralcalc.logic.impl.DifferentialCalculationServiceImpl;
import com.bsuir.diffintegralcalc.logic.impl.IntegralCalculationServiceImpl;
import com.bsuir.diffintegralcalc.utils.VectorUtils;
import com.bsuir.funapproximation.logic.FunctionService;
import com.bsuir.funapproximation.logic.impl.FunctionServiceImpl;
import com.bsuir.linearsystem.model.Vector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.bsuir.diffintegralcalc.utils.TaskUtils.HP_MAS;
import static com.bsuir.diffintegralcalc.utils.TaskUtils.M_MAS;
import static com.bsuir.diffintegralcalc.utils.VariantUtils.*;
import static com.bsuir.funapproximation.util.VariantUtils.getF;

public class MainController {
    private final FunctionService functionService = new FunctionServiceImpl();
    private final DifferentialCalculationService differentialCalculationService
            = new DifferentialCalculationServiceImpl();
    private final IntegralCalculationService integralCalculationService
            = new IntegralCalculationServiceImpl();

    @FXML
    private TextField aField;

    @FXML
    private TextField bField;

    @FXML
    public void calculateDifferentialsAndIntegralAndShowResult() throws IOException {
        double a = Double.parseDouble(aField.getText());
        double b = Double.parseDouble(bField.getText());

        Vector xVector = functionService.calculateXVector(a, b, 21);
        Vector yVector = functionService.calculateYVector(getF(), xVector);

        Vector d1tVector = functionService.calculateYVector(getFirstDifferentialOfF(), xVector);
        Vector d2tVector = functionService.calculateYVector(getSecondDifferentialOfF(), xVector);

        Vector[] d1Vectors = new Vector[HP_MAS.length];
        Vector[] d2Vectors = new Vector[HP_MAS.length];
        Vector[] delD1Vectors = new Vector[HP_MAS.length];
        Vector[] delD2Vectors = new Vector[HP_MAS.length];
        for (int i = 0; i < HP_MAS.length; i++) {
            double h = HP_MAS[i];
            d1Vectors[i] = differentialCalculationService.calculateD1ApproximateVector(getF(), xVector, h);
            d2Vectors[i] = differentialCalculationService.calculateD2ApproximateVector(getF(), xVector, h);
            delD1Vectors[i] = VectorUtils.diff(d1tVector, d1Vectors[i]);
            delD2Vectors[i] = VectorUtils.diff(d2tVector, d2Vectors[i]);
        }

        double[] integralValues = new double[M_MAS.length];
        double[] delIntegralValues = new double[M_MAS.length];
        for (int i = 0; i < M_MAS.length; i++) {
            int m = M_MAS[i];
            integralValues[i] = integralCalculationService.calculateIntegral(a, b, m, getF());
            delIntegralValues[i] = F_INTEGRAL_EXACT_VALUE - integralValues[i];
        }

        CalculationResult results = new CalculationResult.Builder()
                .setXVector(xVector)
                .setYVector(yVector)
                .setD1tVector(d1tVector)
                .setD2tVector(d2tVector)
                .setD1Vectors(d1Vectors)
                .setD2Vectors(d2Vectors)
                .setDelD1Vectors(delD1Vectors)
                .setDelD2Vectors(delD2Vectors)
                .setIntegralExactValue(F_INTEGRAL_EXACT_VALUE)
                .setIntegralValues(integralValues)
                .setDelIntegralValues(delIntegralValues)
                .build();
        showResultWindow(results);
    }

    private void showResultWindow(CalculationResult results) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/results.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        ResultController controller = loader.getController();
        controller.initResults(results);

        stage.show();
    }
}
