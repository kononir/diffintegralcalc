package com.bsuir.diffintegralcalc.logic;

import java.util.function.Function;

public interface IntegralCalculationService {
    double calculateIntegral(double a, double b, int m, Function<Double, Double> fun);
}
