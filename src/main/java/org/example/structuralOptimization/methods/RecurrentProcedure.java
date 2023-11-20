package org.example.structuralOptimization.methods;

import org.example.structuralOptimization.configurations.Parameters;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

@Component
public class RecurrentProcedure {
    public static Map<String, Double> getResult(ArrayList<Double> costOf, ArrayList<Double> serviceTime, double borderTime, ArrayList<ArrayList<Double>> probabilityMatrix) {
        Map<String, Double> result = new HashMap<>();
        TreeSet<Node> nodes = initSystem(costOf, serviceTime, probabilityMatrix);

        optimization(nodes, borderTime);
        System.out.println(nodes);

        return result;
    }

    private static TreeSet<Node> initSystem(ArrayList<Double> costOf, ArrayList<Double> serviceTime, ArrayList<ArrayList<Double>> probabilityMatrix) {
        return new TreeSet<>() {{
            for (int i = 0; i < serviceTime.size(); i++) {
                if (i == 0) {
                    this.add(new Node(i + 1, 4, serviceTime.get(i), 0, probabilityMatrix.get(i)));
                } else {
                    this.add(new Node(i + 1, 1, serviceTime.get(i), costOf.get(i - 1), probabilityMatrix.get(i)));
                }
            }
        }};
    }

    private static void optimization(TreeSet<Node> nodes, double border) {
        int nTasks = 1;

        while (true) {
            ArrayList<Double> averageTimeV = new ArrayList<>();
            ArrayList<Double> eV = new ArrayList<>();

            nodes.forEach(node -> {
                double e = e(node.getProbabilities());
                averageTimeV.add(getAverageTime(node.getServiceTime(), nTasks, node.getK(), e, 0));
                eV.add(e);
            });
            double resTime = 0;
            for (int i = 0; i < eV.size(); i++) {
                resTime += eV.get(i) * averageTimeV.get(i);
            }
            if (resTime <= border) {
                break;
            } else {
                nodes.first().incrementK();
            }
        }


    }

    private static double getAverageTime(double serviceTime, double j, int K, double e, double time) {
        double newTime = serviceTime * (1 + (getL(e, j - 1) / K));
        if (j != 1 && ((j - 1) / time) / (j / newTime) >= Parameters.eps) {
            return newTime;
        }
        return getAverageTime(serviceTime, ++j, K, e, newTime);
    }

    private static double getL(double e, double j) {
        return e * j;
    }
    private static double e(ArrayList<Double> raw) {
        double sum = 0;
        double e = (double) 1 / raw.size();
        for (Double p : raw) sum += p * e;
        return sum;
    }

}
