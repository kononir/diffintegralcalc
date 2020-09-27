package com.bsuir.diffintegralcalc.utils;

import java.util.function.Function;

public class VariantUtils {
    public static final double F_INTEGRAL_EXACT_VALUE = 3.533;

    private VariantUtils() {
    }

    public static Function<Double, Double> getFirstDifferentialOfF() {
        return (x) -> 5 * Math.sin(x);
    }

    public static Function<Double, Double> getSecondDifferentialOfF() {
        return (x) -> 5 * Math.cos(x);
    }
}
