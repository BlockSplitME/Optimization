package org.example.intervalOptimization.configurations;

import java.util.ArrayList;
import java.util.List;

public class Parameters {
    public final static List<Double> delta = new ArrayList<>(){{add(0.1); add(0.01);add(0.001);}};
    public final static int a0 = -100;
    public final static int b0 = 100;
    public static double getValueFunction(double x) {
        return Math.pow (x, 2) + 3 * x + 2;
    }
}
