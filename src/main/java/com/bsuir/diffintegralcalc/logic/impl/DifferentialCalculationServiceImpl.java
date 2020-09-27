package com.bsuir.diffintegralcalc.logic.impl;

import com.bsuir.diffintegralcalc.logic.DifferentialCalculationService;
import com.bsuir.linearsystem.model.Vector;

import java.util.function.Function;

public class DifferentialCalculationServiceImpl implements DifferentialCalculationService {
    @Override
    public Vector calculateD1ApproximateVector(Function<Double, Double> fun, Vector xVector, double h) {
        int m = xVector.len();
        Vector d1fVector = new Vector(m);
        for (int i = 0; i < m; i++) {
            double xi = xVector.get(i);
            d1fVector.set(i, (fun.apply(xi + h) - fun.apply(xi - h)) / (2 * h));
        }
        return d1fVector;
    }

    @Override
    public Vector calculateD2ApproximateVector(Function<Double, Double> fun, Vector xVector, double h) {
        int m = xVector.len();
        Vector d2fVector = new Vector(m);
        for (int i = 0; i < m; i++) {
            double xi = xVector.get(i);
            d2fVector.set(i, (fun.apply(xi + h) - 2 * fun.apply(xi) + fun.apply(xi - h)) / (h * h));
        }
        return d2fVector;
    }
}
