package ru.job4j.ood.ocp;

public class Square implements Shape {
    private final double side;

    public Square(double side) {
        this.side = side;
    }

    public double area() {
        return side * side;
    }
}
