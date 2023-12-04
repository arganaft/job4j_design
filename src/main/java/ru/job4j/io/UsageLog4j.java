package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        byte course = 2;
        char gender = 'm';
        short grade = 5426;
        long scholarship = 3254263L;
        float wage = 43534435.34543F;
        double revenue = 26254625634263.4263532;


        LOG.debug("User info name : {}, age : {}, course : {}, gender : {}, grade : {}", name, age, course, gender, grade);
        LOG.error("Profit info -> scholarship : {}, wage : {}, revenue : {}", scholarship, wage, revenue);
    }
}
