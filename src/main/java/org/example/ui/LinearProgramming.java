package org.example.ui;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;


public abstract class IntervalOptimization {
    protected double delta;
    protected double[] interval;
    protected double x;
    protected Map<String, Double> result;
    public IntervalOptimization() {
        this.interval = new double[2];
        this.result = new HashMap<>();
    }
    public abstract int search();
    public String getName() {
        return this.getClass().getSimpleName();
    }
    public final Map<String, Double> getResult(int a, int b, double delta) {
        this.interval[0] = a;
        this.interval[1] = b;
        this.delta = delta;

        this.result.put("L0", this.getLengthInterval(this.interval));
        this.result.put("N", (double)this.search());
        this.result.put("LN", this.getLengthInterval(this.interval));
        this.result.put("x", this.x);
        this.result.put("a(N)", this.getConvergenceSpeed());
        this.result.remove("L0");
        this.result.remove("LN");

        return this.result;
    }
    protected final double getLengthInterval(double[] interval) {
        return abs(interval[0] - interval[1]);
    }
    protected final double getConvergenceSpeed() {
        return this.result.get("LN") / this.result.get("L0");
    }
}
