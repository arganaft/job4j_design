package ru.job4j.ood.lsp;

import java.util.List;

public class Drawing {
    public static void drawShapes(List<Shape> shapes) {
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    static class Shape {
        public void draw() {
        }
    }

    static class Rectangle extends Shape {
        @Override
        public void draw() {
        }
    }

    static class Square extends Rectangle {
        private int sideLength;

        public Square(int sideLength) {
            this.sideLength = sideLength;
        }

        @Override
        public void draw() {
            if (sideLength <= 0) {
                throw new IllegalStateException("Side length must be positive.");
            }
        }
    }
}
