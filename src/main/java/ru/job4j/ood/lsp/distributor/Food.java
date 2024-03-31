package ru.job4j.ood.lsp.distributor;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Food {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String name;
    private final LocalDateTime createDate;
    private final LocalDateTime expiryDate;

    private double price;

    public Food(String name, LocalDateTime createDate, LocalDateTime expiryDate, double price) {
        if (name == null || createDate == null || expiryDate == null) {
            throw new NullPointerException("Передано null значение");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        if (createDate.isAfter(expiryDate)) {
            throw new IllegalArgumentException("Дата создания не может быть позже даты истечения");
        }

        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
    }

    public float expireDetect() {
        float totalTerm = ChronoUnit.DAYS.between(createDate, expiryDate);
        float daysHavePassed = ChronoUnit.DAYS.between(createDate, LocalDateTime.now());
        return (daysHavePassed / totalTerm) * 100;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expiryDate=" + expiryDate.format(formatter)
                + ", createDate=" + createDate.format(formatter)
                + ", price=" + price
                + '}';
    }
}
