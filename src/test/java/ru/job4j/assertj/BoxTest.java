package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void initTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).contains("rahe")
                .containsIgnoringCase("tetrahedron")
                .doesNotContain(" ")
                .endsWith("dron")
                .startsWith("Tet").isEqualTo("Tetrahedron");
    }

    @Test
    void initUNKNOWN() {
        Box box = new Box(4, -77);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isGreaterThan(-6)
                .isLessThan(17)
                .isBetween(-4, 15)
                .isNotZero()
                .isEqualTo(-1);
    }

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisNotSphere() {
        Box box = new Box(2, 10);
        String name = box.whatsThis();
        assertThat(name).startsWithIgnoringCase("unknown")
                .containsIgnoringWhitespaces("ownobj")
                .isEqualTo("Unknown object");
    }

    @Test
    void isNotExist() {
        Box box = new Box(2, -10);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void isExist() {
        Box box = new Box(4, 1);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isAreaOfVertex4Edge5() {
        Box box = new Box(4, 5);
        double area = box.getArea();
        assertThat(area).isCloseTo(40, Percentage.withPercentage(22))
                .isGreaterThan(15.3)
                .isLessThan(50.33)
                .isEqualTo(43.3, withPrecision(0.002d));
    }

}