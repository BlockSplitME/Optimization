package org.example.linearProgramming.methods;

import org.example.linearProgramming.methods.utils.Matrix;
import org.example.linearProgramming.ui.LinearProgramming;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component

public class SimplexMethod extends LinearProgramming {

    @Override
    protected double search() {
        //step1
        this.transformMatrix();
        ArrayList<Integer> idBases;
        int idRaw;
        int size = this.func.get(0).size() - 1;
        while(true) {
            //step2
            idBases = Matrix.getIdColumnBases(this.func);
            if(this.isOptimal(this.func.get(0))) break;
            int idColumn = Matrix.getIdMinInRaw(new ArrayList<>(this.func.get(0).subList(0, size)));
            idRaw = Matrix.getIdMinInRawAbs(this.getDivisionColumn(Matrix.getColumn(this.func, size, 1), Matrix.getColumn(this.func, idColumn, 1))) + 1;
            //step3
            this.normalizeMatrix(idRaw, idColumn);
        }
        idBases.forEach((id) -> {
            if(id < numbVariables) {
                try {
                    this.variables.put(id, this.getVariable(id));
                } catch (Exception e) {
                    System.out.printf(e.getMessage());
                }
            }
        });
        System.out.println("Last Simplex Table:");
        this.func.forEach(System.out::println);
        return this.func.get(0).get(size);
    }
    private void transformMatrix() {
        try {
            int size = this.func.size()-1;
            Matrix.concatMatrix(this.func, new ArrayList<>(){{
                addAll(Matrix.getZeroVector(size));
                addAll(Matrix.getIdentityMatrix(size));
            }}, size-1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void normalizeMatrix(int idRaw, int idCol) {
        double permissive = this.func.get(idRaw).get(idCol);
        ArrayList<Double> column = Matrix.getColumn(this.func, idCol);
        this.func.get(idRaw).replaceAll(el -> el / permissive);
        for(int i = 0; i < this.func.size(); i++) {
            if(i != idRaw) {
                double factor = column.get(i);
                for(int j = 0; j < this.func.get(i).size(); j++) {
                    this.func.get(i).set(j, this.func.get(i).get(j) - this.func.get(idRaw).get(j) * factor);
                }
            }
        }
    }
    private double getVariable(int id) throws Exception {
        ArrayList<Double> basis = Matrix.getColumn(this.func, id);
        for(int i = 0; i < basis.size(); i++) {
            if(basis.get(i) != 0) {
                return this.func.get(i).get(this.func.get(0).size()-1) / basis.get(i);
            }
        }
        throw new Exception("Not basis.");
    }
    private boolean isOptimal(ArrayList<Double> raw) {
        return raw.get(Matrix.getIdMinInRaw(raw)) >= 0;
    }
    private ArrayList<Double> getDivisionColumn(ArrayList<Double> a, ArrayList<Double> b) {
        return new ArrayList<>(){{
            for(int i = 0; i < a.size(); i++) {
                add(a.get(i) / b.get(i));
            }
        }};
    }
}
