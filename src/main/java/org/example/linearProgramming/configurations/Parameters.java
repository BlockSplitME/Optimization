package org.example.linearProgramming.configurations;

import java.util.ArrayList;
import java.util.Arrays;

//f(x) = 3x + 2y -> max
//|5x + 2y <= 160
//|3x + 4y <= 180
//|7x < 196
//|x,y >= 0
public class Parameters {
    public static final ArrayList<ArrayList<Double>> canonicalFunc = new ArrayList<>(){{
        add(new ArrayList<>(Arrays.asList(-3.0, -2.0, 0.0)));
        add(new ArrayList<>(Arrays.asList(5.0, 2.0, 160.0)));
        add(new ArrayList<>(Arrays.asList(3.0, 4.0, 180.0)));
        add(new ArrayList<>(Arrays.asList(7.0, 0.0, 196.0)));
    }};

}
