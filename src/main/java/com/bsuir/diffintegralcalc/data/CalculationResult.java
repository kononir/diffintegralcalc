package com.bsuir.diffintegralcalc.data;

import com.bsuir.linearsystem.model.Vector;

public class CalculationResult {
    private Vector xVector;
    private Vector yVector;
    private Vector d1tVector;
    private Vector d2tVector;
    private Vector[] d1Vectors;
    private Vector[] d2Vectors;
    private Vector[] delD1Vectors;
    private Vector[] delD2Vectors;
    private double integralExactValue;
    private double[] integralValues;
    private double[] delIntegralValues;

    private CalculationResult() {
    }

    public static class Builder {
        CalculationResult result = new CalculationResult();

        public CalculationResult build() {
            CalculationResult build = result;
            result = new CalculationResult();
            return build;
        }

        public Builder setXVector(Vector xVector) {
            result.xVector = xVector;
            return this;
        }

        public Builder setYVector(Vector yVector) {
            result.yVector = yVector;
            return this;
        }

        public Builder setD1tVector(Vector d1tVector) {
            result.d1tVector = d1tVector;
            return this;
        }

        public Builder setD2tVector(Vector d2tVector) {
            result.d2tVector = d2tVector;
            return this;
        }

        public Builder setD1Vectors(Vector[] d1Vectors) {
            result.d1Vectors = d1Vectors;
            return this;
        }

        public Builder setD2Vectors(Vector[] d2Vectors) {
            result.d2Vectors = d2Vectors;
            return this;
        }

        public Builder setDelD1Vectors(Vector[] delD1Vectors) {
            result.delD1Vectors = delD1Vectors;
            return this;
        }

        public Builder setDelD2Vectors(Vector[] delD2Vectors) {
            result.delD2Vectors = delD2Vectors;
            return this;
        }

        public Builder setIntegralExactValue(double integralExactValue) {
            result.integralExactValue = integralExactValue;
            return this;
        }

        public Builder setIntegralValues(double[] integralValues) {
            result.integralValues = integralValues;
            return this;
        }

        public Builder setDelIntegralValues(double[] delIntegralValues) {
            result.delIntegralValues = delIntegralValues;
            return this;
        }
    }

    public Vector getXVector() {
        return xVector;
    }

    public Vector getYVector() {
        return yVector;
    }

    public Vector getD1tVector() {
        return d1tVector;
    }

    public Vector getD2tVector() {
        return d2tVector;
    }

    public Vector[] getD1Vectors() {
        return d1Vectors;
    }

    public Vector[] getD2Vectors() {
        return d2Vectors;
    }

    public Vector[] getDelD1Vectors() {
        return delD1Vectors;
    }

    public Vector[] getDelD2Vectors() {
        return delD2Vectors;
    }

    public double getIntegralExactValue() {
        return integralExactValue;
    }

    public double[] getIntegralValues() {
        return integralValues;
    }

    public double[] getDelIntegralValues() {
        return delIntegralValues;
    }
}
