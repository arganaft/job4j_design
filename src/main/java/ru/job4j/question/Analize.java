package ru.job4j.question;


import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted;
        int common = 0;
        Map<Integer, User> map = new HashMap<>();
        User el;
        for (User user : previous){
            map.put(user.getId(), user);
        }
        for (User user : current) {
            el = map.put(user.getId(), user);
            if (el == null) {
                added++;
            } else if (el.getId() == user.getId() && !el.getName().equals(user.getName())) {
                changed++;
            } else {
                common++;
            }
        }
        deleted = previous.size() - common - changed;
        return new Info(added, changed, deleted);
    }
}
