package ru.job4j.ood.ocp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class AreaReportGenerator {
    public String filterGenerate(List<Shape> shapes, Predicate<Shape> filter) {
        StringBuilder builder = new StringBuilder();
        for (Shape shape : shapes) {
            if (filter.test(shape)) {
                builder.append(String.valueOf(shape.area()))
                       .append(System.lineSeparator());
            }
        }
        return builder.toString();
    }

    public String sortGenerate(List<Shape> shapes, Comparator<Shape> sortType) {
        StringBuilder builder = new StringBuilder();
        List<Shape> sortShapes = shapes;
        sortShapes.sort(sortType);
        for (Shape shape : sortShapes) {
            builder.append(String.valueOf(shape.area()))
                   .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
