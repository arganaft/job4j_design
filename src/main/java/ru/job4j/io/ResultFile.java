package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/dataresult.txt")) {
            out.write("Hello, world!".getBytes());
            out.write(System.lineSeparator().getBytes());
            int size = 10;
            int[][] table = multiple(size);
            for (int row = 0; row < size; row++) {
                for (int cell = 0; cell < size; cell++) {
                    table[row][cell] = (cell + 1) * (row + 1);
                    out.write(String.valueOf(table[row][cell]).getBytes());
                    out.write("   ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int cell = 0; cell < size; cell++) {
                table[row][cell] = (cell + 1) * (row + 1);
            }
        }
        return table;
    }
}
