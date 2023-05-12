package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] strArr = text.toString().split(System.lineSeparator());
            text.setLength(0);
            for (String s : strArr) {
                read = Integer.parseInt(s);
                text.append(read);
                text.append(read % 2 == 0 ? " - Число является четным" : " - Число является нечетным");
                System.out.println(text);
                text.setLength(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
