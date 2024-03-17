package ru.job4j.kiss.fool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    private IOList io;

    @BeforeEach
    void setUp() {
        ArrayList<String> answers = new ArrayList<>(List.of("2", "4", "Fizz", "8", "Buzz", "Fizz", "14", "16"));
        io = new IOList(answers);
        Fool game = new Fool(io);
        game.startGame();
    }

    @Test
    void startGame() {
        assertThat(io.getAnswers().get(0)).isEqualTo("1");
    }

    @Test
    void isFizz() {
        assertThat(io.getAnswers().get(2)).isEqualTo("Fizz");
    }

    @Test
    void isBuzz() {
        assertThat(io.getAnswers().get(4)).isEqualTo("Buzz");
    }

    @Test
    void isFizzBuzz() {
        assertThat(io.getAnswers().get(14)).isEqualTo("FizzBuzz");
    }

}