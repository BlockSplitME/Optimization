package org.example.structuralOptimization.configurations;

import java.nio.file.Path;
import java.util.ArrayList;

public class Parameters {
    public static final ArrayList<Double> costOfNodes = new ArrayList<>(){{ add(1.5); add(1.0);add(1.7);add(2.1);}};
    public static final ArrayList<Double> serviceTime = new ArrayList<>(){{ add(0.2); add(0.25);add(0.3);add(0.32);add(0.4);}};
    public static final double borderTime = 0.65;
    public static final ArrayList<Double> k = new ArrayList<>(){{add(1.0); add(0.5);add(1.7);}};
    public static final String fileName = "src/resources/GraphProbabilityMatrix.txt";

    public static final double eps = 0.9;
}
