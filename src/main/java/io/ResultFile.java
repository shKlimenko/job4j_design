package io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        int size = 5;
        int[][] tabl = new int[size][size];
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    tabl[i][j] = (i + 1) * (j + 1);
                    out.write((tabl[i][j] + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}