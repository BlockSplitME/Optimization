package org.example.pointEstimationOptimization.ui;

import org.example.pointEstimationOptimization.configurations.Parameters;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

public abstract class PointEstimationOptimization {
    protected double delta;
    protected double[] interval;
    protected double deltaX;
    protected Map<String, Double> result;
    protected double L0, LN;
    protected double minPoint;
    public PointEstimationOptimization() {
        this.interval = new double[2];
        this.result = new HashMap<>();
    }
    public abstract int search();
    public String getName() {
        return this.getClass().getSimpleName();
    }
    public final Map<String, Double> getResult(double a0, double b0, double deltaX, double delta) {
        this.interval[0] = a0;
        this.interval[1] = b0;
        this.deltaX = deltaX;
        this.delta = delta;

        this.result.put("N", (double)this.search());
        this.result.put("xMin", this.minPoint);
        this.result.put("Fmin", Parameters.getValueFunction(this.minPoint));
        this.result.put("a(N)", this.getConvergenceSpeed());
        return this.result;
    }
    protected final double getConvergenceSpeed() {
        return this.LN / this.L0;
    }
    protected final double getLengthInterval(double[] interval) {
        return abs(interval[0] - interval[1]);
    }

}
