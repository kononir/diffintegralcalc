package com.bsuir.diffintegralcalc.utils;

import com.bsuir.linearsystem.model.Vector;

public class VectorUtils {
    private VectorUtils() {
    }

    public static Vector diff(Vector v1, Vector v2) {
        if (v1.len() != v2.len()) {
            throw new IllegalArgumentException(
                    String.format("Vectors have different length - v1.len = %d, v2.len = %d", v1.len(), v2.len()));
        }

        Vector vRes = new Vector(v1.len());
        for (int i = 0; i < v1.len(); i++) {
            vRes.set(i, v1.get(i) - v2.get(i));
        }

        return vRes;
    }
}
