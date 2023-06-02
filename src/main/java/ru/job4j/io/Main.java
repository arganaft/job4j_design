package ru.job4j.io;

import javax.xml.stream.events.StartElement;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String str = new String("name=");
        String[] arr = str.split("=");
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
