package org.example.linearProgramming.methods.utils;

import java.util.ArrayList;

public class Matrix {
    public static ArrayList<ArrayList<Double>> getIdentityMatrix(int size) {
        ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            ArrayList<Double> raw = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                boolean b = (i == j) ? raw.add(1.0) : raw.add(0.0);
            }
            matrix.add(raw);
        }
        return matrix;
    }
    public static ArrayList<ArrayList<Double>> getZeroVector(int size) {
        return new ArrayList<>() {{
            add(new ArrayList<>(){{
                for(int i = 0; i < size; i++) {
                    add(0.0);
                }
            }});

        }};
    }
    public static ArrayList<ArrayList<Double>> getZeroMatrix(int size) {
        return new ArrayList<>(){{
            for(int i = 0; i < size; i++) {
                add(new ArrayList<>(){{
                    for(int i = 0; i < size; i++) {
                        add(0.0);
                    }
                }});
            }
        }};
    }

    public static void concatMatrix(ArrayList<ArrayList<Double>> a, ArrayList<ArrayList<Double>> b, int index) throws Exception {
        if(a.size() != b.size()) throw new Exception("Other size matrix");
        for(int i = 0; i < a.size(); i++) {
            a.get(i).addAll(index, b.get(i));
        }
    }
    public static void concatMatrix(ArrayList<ArrayList<Double>> a, ArrayList<ArrayList<Double>> b) throws Exception {
        Matrix.concatMatrix(a, b, 0);
    }

    public static ArrayList<Integer> getIdColumnBases(ArrayList<ArrayList<Double>> matrix) {
        ArrayList<Integer> idColumn = new ArrayList<>();
        for(int i = 0; i < matrix.get(0).size(); i++) {
            boolean notNull = false;
            for (ArrayList<Double> Doubles : matrix) {
                if (Doubles.get(i) != 0) {
                    if (!notNull) {
                        notNull = true;
                    } else {
                        notNull = false;
                        break;
                    }
                }
            }
            if(notNull) {
                idColumn.add(i);
            }
        }
        return idColumn;
    }
    public static int getIdMinInRaw(ArrayList<Double> raw) {
        int idMin = 0;
        for(int i = 0; i < raw.size(); i++) {
            if(raw.get(idMin) > raw.get(i)) {
                idMin = i;
            }
        }
        return idMin;
    }
    public static int getIdMinInRawAbs(ArrayList<Double> raw) {
        int idMin = raw.size()-1;
        for(int i = 0; i < raw.size(); i++) {
            if(raw.get(idMin) > raw.get(i) && raw.get(i) > 0) {
                idMin = i;
            }
        }
        return idMin;
    }
    public static ArrayList<Double> getColumn(ArrayList<ArrayList<Double>> matrix, int id, int index) {
        ArrayList<Double> column = new ArrayList<>();
        for (int i = index; i < matrix.size(); i++) {
            column.add(matrix.get(i).get(id));
        }
        return column;
    }
    public static ArrayList<Double> getColumn(ArrayList<ArrayList<Double>> matrix, int id) {
        return Matrix.getColumn(matrix, id, 0);
    }
}
