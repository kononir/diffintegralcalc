package com.bsuir.diffintegralcalc.logic;

import com.bsuir.linearsystem.model.Vector;

import java.util.function.Function;

public interface DifferentialCalculationService {
    Vector calculateD1ApproximateVector(Function<Double, Double> fun, Vector xVector, double h);

    Vector calculateD2ApproximateVector(Function<Double, Double> fun, Vector xVector, double h);
}
