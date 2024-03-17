package ru.job4j.kiss.fool;

import java.util.Scanner;

public class IOConsole implements IOInterface {
    private final Scanner input = new Scanner(System.in);

    @Override
    public String input() {
        return input.nextLine();
    }

    @Override
    public void output(String msg) {
        System.out.println(msg);
    }
}
