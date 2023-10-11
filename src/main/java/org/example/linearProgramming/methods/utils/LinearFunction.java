package org.example.linearProgramming.methods.utils;

import lombok.*;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class LinearFunction {
    private final double a,  b,  c;

    public LinearFunction(ArrayList<Double> array) {
        this.a = array.get(0);
        this.b = array.get(1);
        this.c = array.get(2);
    }

    public static double[] getIntersectionPoint(LinearFunction func1, LinearFunction func2) {
        return new double[]{
                (func1.getB() * func2.getC() - func2.getB() * func1.getC()) / (func1.getA() * func2.getB() - func2.getA() * func1.getB()),
                (func1.getC() * func2.getA() - func2.getC() * func1.getA()) / (func1.getA() * func2.getB() - func2.getA() * func1.getB())
        };
    }
    public static double euclideanDistance(double[] x, double[] y) {
        return Math.sqrt(Math.pow(x[0] - y[0], 2) + Math.pow(x[1] - y[1], 2));
    }

}
