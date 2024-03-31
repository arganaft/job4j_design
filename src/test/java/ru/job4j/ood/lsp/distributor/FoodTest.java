package ru.job4j.ood.lsp.distributor;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.*;

class FoodTest {
    @Test
    public void whenExpireDetectReturnsValidValue() {
        LocalDateTime createDate = LocalDateTime.now().minusDays(10);
        LocalDateTime expiryDate = LocalDateTime.now().plusDays(90);
        Food food = new Food("food", createDate, expiryDate, 50);
        assertThat(food.expireDetect()).isEqualTo(10);
    }

    @Test
    public void whenCreateDateIsAfterExpiryDateReturnsException() {
        LocalDateTime createDate = LocalDateTime.now().plusDays(5);
        LocalDateTime expiryDate = LocalDateTime.now().minusDays(2);
        assertThatThrownBy(() -> new Food("food", createDate, expiryDate, 50))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("Дата создания не может быть позже даты истечения");
    }

    @Test
    public void whenArgumentIsNulReturnsException() {
        assertThatThrownBy(() -> new Food("food", null, null, 50))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Передано null значение");
    }

    @Test
    public void whenPriceIsLessThanZeroReturnsException() {
        LocalDateTime createDate = LocalDateTime.now().plusDays(5);
        LocalDateTime expiryDate = LocalDateTime.now().minusDays(2);
        assertThatThrownBy(() -> new Food("food", createDate, expiryDate, -4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Цена не может быть отрицательной");
    }

}