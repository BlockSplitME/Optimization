package org.example.structuralOptimization.methods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Node implements Comparator, Comparable{
    private int number;
    private int K;
    private double serviceTime;
    private double cost;
    private ArrayList<Double> probabilities;
    public void incrementK() {
        this.setK(this.getK() + 1);
    }
    @Override
    public int compareTo(Object o) {
        return compare(this, o);
    }

    @Override
    public int compare(Object o1, Object o2) {
        Node n1 = (Node) o1;
        Node n2 = (Node) o2;
        if(n1.number == 1) {
            return 1;
        } else if (n2.number == 1) {
            return -1;
        }
        return (n1.getCost() > n2.getCost()) ? 1 : -1;
    }
}
