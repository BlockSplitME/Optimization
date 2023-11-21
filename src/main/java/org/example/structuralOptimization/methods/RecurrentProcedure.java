package org.example.structuralOptimization.methods;

import org.example.structuralOptimization.configurations.Parameters;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RecurrentProcedure {
    public static ArrayList<Node> getResult(ArrayList<Double> costOf, ArrayList<Double> serviceTime, double borderTime, ArrayList<ArrayList<Double>> probabilityMatrix, double[] eVector) {
        ArrayList<Node> nodes = initSystem(costOf, serviceTime, probabilityMatrix, eVector);
        optimization(nodes, borderTime);
        return nodes;
    }

    private static ArrayList<Node> initSystem(ArrayList<Double> costOf, ArrayList<Double> serviceTime, ArrayList<ArrayList<Double>> probabilityMatrix, double[] eVector) {
        return new ArrayList<>() {{
            for (int i = 0; i < serviceTime.size(); i++) {
                if (i == 0) {
                    this.add(new Node(i + 1, 4, serviceTime.get(i), 0, probabilityMatrix.get(i), eVector[i]));
                } else {
                    this.add(new Node(i + 1, 1, serviceTime.get(i), costOf.get(i - 1), probabilityMatrix.get(i), eVector[i]));
                }
            }
        }};
    }

    private static void optimization(ArrayList<Node> nodes, double border) {
        int nTasks = 1;

        while (true) {
            ArrayList<Double> averageTimeV = new ArrayList<>();
            ArrayList<Double> eV = new ArrayList<>();

            nodes.forEach(node -> {
                double e = e(node.getProbabilities(), node.getE());
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
                searchMinCostNode(nodes).incrementK();
            }
        }
    }
    private static double getAverageTime(double serviceTime, double j, int K, double e, double time) {
        double newTime = serviceTime * (1 + (getL(e, j - 1) / K));
        double exit = ((j - 1) / time) / (j / newTime);
        if (j != 1 && exit > Parameters.eps && exit < 1) {
            return newTime;
        }
        return getAverageTime(serviceTime, ++j, K, e, newTime);
    }

    private static double getL(double e, double j) {
        return e * j;
    }
    private static double e(ArrayList<Double> raw, double e) {
        double sum = 0;
        for (Double p : raw) sum += p * e;
        return sum;
    }
    private static Node searchMinCostNode(ArrayList<Node> nodes) {
        Node res = nodes.get(1);
        for(Node node: nodes){
            if(node.getCost() < res.getCost() && node.getNumber() != 1) {
                res = node;
            }
        }
        return res;
    }
}
