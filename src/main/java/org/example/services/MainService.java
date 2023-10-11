package org.example.services;

import jakarta.annotation.PostConstruct;
import org.example.intervalOptimization.ui.IntervalOptimization;
import org.example.linearProgramming.ui.LinearProgramming;
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

    @PostConstruct
    public void post() {
//        lab1();
//        lab2();
//        lab3();
//        lab4();
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

    }
    private void lab4() {

    }
    private void lab5() {

    }
}
