package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        List<User> prevList = previous.stream().sorted(Comparator.comparingInt(User::getId)).toList();
        List<User> currList = current.stream().sorted(Comparator.comparingInt(User::getId)).toList();
        int inPr = 0;
        int inCu = 0;

        while (inPr < prevList.size() && inCu < currList.size()) {
            if (prevList.get(inPr).getId() == currList.get(inCu).getId()
                && !prevList.get(inPr).getName().equals(currList.get(inCu).getName())) {
                changed++;
                inPr++;
                inCu++;
            } else if (prevList.get(inPr).getId() != currList.get(inCu).getId()) {
                deleted++;
                inPr++;
            } else {
                inPr++;
                inCu++;
            }
        }
        added += currList.size() - inCu;
        return new Info(added, changed, deleted);
    }

}
