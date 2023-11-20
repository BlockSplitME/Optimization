package org.example.pointEstimationOptimization.configurations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Search min function
public class Parameters {
    public final static List<Double> delta = new ArrayList<>(){{add(0.1); add(0.01);add(0.001);}};
    public final static double a0 = -1;
    public final static double b0 = 1;
    public final static double deltaX = 0.2;
    public static double getValueFunction(double x) {
        return 3 * Math.sin(2 * Math.pow(x, 3)  + 3);
    }
    public static double getValueFunctionDerivative(double x) {
        return 18 * Math.pow(x, 2) * Math.cos(2 * Math.pow(x, 3)  + 3);
    }
    public static double getValueFunctionDerivative2(double x) {
        return 36 * x * Math.cos(2 * Math.pow(x, 3)  + 3) - 108 * Math.pow(x, 4) * Math.sin(2 * Math.pow(x,3) + 3);
    }
}
