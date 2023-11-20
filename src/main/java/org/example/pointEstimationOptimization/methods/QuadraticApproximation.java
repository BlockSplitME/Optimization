package org.example.pointEstimationOptimization.methods;

import org.example.pointEstimationOptimization.configurations.Parameters;
import org.example.pointEstimationOptimization.ui.PointEstimationOptimization;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class QuadraticApproximation extends PointEstimationOptimization {
    private double[] x;
    private double[] f;
    private double xMin, fMin;
    private int N;

    @Override
    public int search() {
        this.x = new double[3];
        this.f = new double[3];
        this.x[0] = -1;
        this.L0 = this.deltaX;
        this.N = 0;

        this.toStep2();
        this.LN = Math.abs(this.xMin - this.minPoint);
        return this.N;
    }
    private void toStep2() {
        this.x[1] = this.x[0] + this.deltaX;

        this.f[0] = Parameters.getValueFunction(this.x[0]);
        this.f[1] = Parameters.getValueFunction(this.x[1]);

        if(this.f[0] > this.f[1]) this.x[2] = this.x[0] + 2 * this.deltaX;
        else this.x[2] = this.x[0] - this.deltaX;

        this.f[2] = Parameters.getValueFunction(this.x[2]);

        this.toStep6();
    }
    private void toStep6() {
        this.N++;
        int minN = this.findNumberMinValue(this.f);
        this.xMin = this.x[minN]; this.fMin = this.f[minN];

        try{
            this.minPoint = getPolynomialMin(x[0], x[1], x[2], f[0], f[1], f[2]);
        } catch (Exception e) {
            this.x[0] = this.xMin;
            this.toStep2();
        }

        if(!(this.condition1(this.delta) && this.condition2(this.delta))) {
           if(this.isPointInInterval(this.x[0], this.x[2], this.minPoint)) {
               this.x[1] = Math.min(this.minPoint, this.xMin);
               this.x[0] = this.x[1] - this.deltaX;
               this.x[2] = this.x[1] + this.deltaX;

               this.f[0] = Parameters.getValueFunction(this.x[0]);
               this.f[1] = Parameters.getValueFunction(this.x[1]);
               this.f[2] = Parameters.getValueFunction(this.x[2]);

               this.toStep6();
           } else {
               this.x[0] = this.minPoint;
               this.toStep2();
           }
        }
    }

    private boolean condition1(double eps) {
        double f = Parameters.getValueFunction(this.minPoint);
        return Math.abs((this.fMin - f) / f) < eps;
    }
    private boolean condition2(double eps) {
        return Math.abs((this.xMin - this.minPoint) / this.minPoint) < eps;
    }
    private boolean isPointInInterval(double x1, double x2, double x) {
        if(x2 > x1) {
            return x >= x1 && x <= x2;
        } else {
            return x <= x1 && x >= x2;
        }
    }

    private int findNumberMinValue(double[] arr) {
        double min = arr[0];
        int n = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                n = i;
            }
        }
        return n;
    }
    private double getPolynomialMin(double x1, double x2, double x3, double f1, double f2, double f3) throws Exception {
        double denominator = (x2 - x3) * f1 + (x3 - x1) * f2 + (x1 - x2) * f3;
        double numerator = (Math.pow(x2, 2) - Math.pow(x3, 2)) * f1 + (Math.pow(x3, 2) - Math.pow(x1, 2)) * f2 + (Math.pow(x1, 2) - Math.pow(x2, 2)) * f3;
        if(denominator == 0) throw new Exception();
        else return numerator / denominator / 2;
    }
}
