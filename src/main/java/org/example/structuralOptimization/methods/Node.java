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
public class Node {
    private int number;
    private int K;
    private double serviceTime;
    private double cost;
    private ArrayList<Double> probabilities;
    private double e;
    public void incrementK() {
        this.setK(this.getK() + 1);
    }
    public double getCost() {
        return cost * K;
    }

}
