package org.example.linearProgramming.methods;

import org.example.linearProgramming.methods.utils.LinearFunction;
import org.example.linearProgramming.ui.LinearProgramming;
import org.example.linearProgramming.methods.utils.Line;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

@Component
public class GraphicMethod extends LinearProgramming {
    protected ArrayList<LinearFunction> functions;
    protected TreeMap<Double, Double> points;
    protected TreeSet<Line> lines = new TreeSet<>();
    @Override
    protected double search() {
        this.initialization();
        return 0;
    }
    private void initialization() {
        this.functions = new ArrayList<>(){{
            for(int i = 1; i < func.size(); i++) {
                add(new LinearFunction(func.get(i)));
            }
            add(new LinearFunction(1,0,0));
            add(new LinearFunction(0,1,0));
        }};

    }
    private void constructPolygon() {
        for(int i = 0; i < functions.size(); i++) {
            for(int j = i; j < functions.size(); j++) {
                if(i != j) {
                    double[] point = LinearFunction.getIntersectionPoint(functions.get(i), functions.get(j));
                    points.put(point[0], point[1]);

                    this.lines.add(new Line());
                }
            }
        }
    }
}
