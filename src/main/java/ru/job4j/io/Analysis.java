package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
            BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            boolean flag = true;
            boolean valid;
            List<String[]> list = in.lines().map(line -> line.split(" ", 2)).toList();
            for (String[] arr: list) {
                valid = "400".equals(arr[0]) || "500".equals(arr[0]);
                if (flag == valid) {
                    out.write(arr[1]);
                    out.write(";");
                    out.write(valid ? "" : System.lineSeparator());
                    flag = !flag;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
