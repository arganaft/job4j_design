package ru.job4j.ood.lsp.distributor;

import java.util.List;
import java.util.function.Predicate;

public class Shop extends AbstractStore {
    private Predicate<Food> admissionRule;
    private Predicate<Food> discountRule;
    private float discount;


    public Shop(Predicate<Food> admissionRule, Predicate<Food> discountRule, float discount) {
        super(admissionRule);
        this.discountRule = discountRule;
        this.discount = discount;
    }


    @Override
    public void add(Food food) {
        doDiscount(food);
        super.add(food);
    }

    @Override
    public boolean addAll(List<Food> foods) {
        for (Food food : foods) {
            doDiscount(food);
        }
        return super.addAll(foods);
    }

    private void doDiscount(Food food) {
        if (discountRule.test(food)) {
            food.setPrice(food.getPrice() * (discount / 100));
        }
    }


}
