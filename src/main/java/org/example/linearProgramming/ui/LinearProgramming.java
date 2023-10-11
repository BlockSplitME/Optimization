package org.example.linearProgramming.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


@Setter
@Getter
@RequiredArgsConstructor
public abstract class LinearProgramming {
    protected ArrayList<ArrayList<Double>> func;
    protected HashMap<String, Double> result = new HashMap<>();
    protected TreeMap<Integer, Double> variables = new TreeMap<>();
    protected int numbVariables;
    protected abstract double search();
    public String getName() {
        return this.getClass().getSimpleName();
    }
    public String getResult(ArrayList<ArrayList<Double>> func) {
        this.func = func;
        this.numbVariables = func.get(0).size() - 1;
        this.result.put("max", this.search());
        this.variables.forEach( (k, v) -> this.result.put("x"+ k , v));
        return this.result.toString();
    }
}
