package org.example.structuralOptimization.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtil {

    public static ArrayList<ArrayList<Double>> parseFileMatrix(String fileName) {
        ArrayList<ArrayList<Double>> matrix = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while (br.ready()) {
                String[] line = br.readLine().split(" ");
                ArrayList<Double> raw = new ArrayList<>();
                for (String s : line) {
                    raw.add(Double.parseDouble(s));
                }
                matrix.add(raw);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return matrix;
    }
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    private static double parseProbability(final String s) {
        final int index = s.indexOf('p');
        final int end = s.indexOf('g');
        return Double.parseDouble(s.substring(index + 2, end - 1));
    }

}
