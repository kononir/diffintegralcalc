package com.bsuir.diffintegralcalc.logic.impl;

import com.bsuir.diffintegralcalc.logic.IntegralCalculationService;

import java.util.function.Function;

public class IntegralCalculationServiceImpl implements IntegralCalculationService {
    @Override
    public double calculateIntegral(double a, double b, int m, Function<Double, Double> fun) {
        double h = (b - a) / m;
        double s = 0;
        double x = a + h / 2;
        for (int i = 0; i < m; i++) {
            s += fun.apply(x);
            x += h;
        }
        return s * h;
    }
}
