package org.example.services;

import jakarta.annotation.PostConstruct;
import org.example.intervalOptimization.ui.IntervalOptimization;
import org.example.linearProgramming.ui.LinearProgramming;
import org.example.pointEstimationOptimization.ui.PointEstimationOptimization;
import org.example.structuralOptimization.methods.RecurrentProcedure;
import org.example.structuralOptimization.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MainService {
    @Autowired
    private List<IntervalOptimization> intervalOptimizations;
    @Autowired
    private List<LinearProgramming> linearProgrammings;
    @Autowired
    private List<PointEstimationOptimization> pointEstimationOptimization;
    @PostConstruct
    public void post() {
//        lab1();
//        lab2();
//        lab3();
        lab4();
//        lab5();

    }
    private void lab1() {
        System.out.println("\n---------------------------Lab1---------------------------------\n");
        List<Double> delta = org.example.intervalOptimization.configurations.Parameters.delta;
        int a0 = org.example.intervalOptimization.configurations.Parameters.a0;
        int b0 = org.example.intervalOptimization.configurations.Parameters.b0;

        this.intervalOptimizations.forEach(method -> delta.forEach(del -> {
            System.out.println(method.getName() + "(del= " + del + "):" + method.getResult(a0, b0, del));
        }));
    }
    private void lab2() {
        System.out.println("\n---------------------------Lab2---------------------------------\n");
        ArrayList<ArrayList<Double>> func = org.example.linearProgramming.configurations.Parameters.canonicalFunc;

        this.linearProgrammings.forEach(method -> System.out.println(method.getName() + ": " + method.getResult((ArrayList<ArrayList<Double>>) func.clone())));
    }
    private void lab3() {
        System.out.println("\n---------------------------Lab3---------------------------------\n");
        ArrayList<Double> costOfNodes = org.example.structuralOptimization.configurations.Parameters.costOfNodes;
        ArrayList<Double> serviceTime = org.example.structuralOptimization.configurations.Parameters.serviceTime;
        double borderTime = org.example.structuralOptimization.configurations.Parameters.borderTime;
        ArrayList<Double> factor = org.example.structuralOptimization.configurations.Parameters.k;
        String fileName = org.example.structuralOptimization.configurations.Parameters.fileName;

        factor.forEach(k -> {
            System.out.println("k = "+ k + ":\n" + RecurrentProcedure.getResult(costOfNodes, serviceTime, borderTime * k, FileUtil.parseFileMatrix(fileName)));
        });
    }
    private void lab4() {
        System.out.println("\n---------------------------Lab4---------------------------------\n");
        List<Double> delta = org.example.pointEstimationOptimization.configurations.Parameters.delta;
        double a0 = org.example.pointEstimationOptimization.configurations.Parameters.a0;
        double b0 = org.example.pointEstimationOptimization.configurations.Parameters.b0;
        double deltaX  = org.example.pointEstimationOptimization.configurations.Parameters.deltaX;
        this.pointEstimationOptimization.forEach(method -> delta.forEach(del -> {
            System.out.println(method.getName() + "(del= " + del + "):" + method.getResult(a0, b0, deltaX, del));
        }));
    }
    private void lab5() {
        System.out.println("\n---------------------------Lab5---------------------------------\n");

    }
}
