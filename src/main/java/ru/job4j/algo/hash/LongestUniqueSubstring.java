package ru.job4j.algo.hash;

import java.util.LinkedHashSet;
import java.util.Set;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        if (str.length() == 0) {
            return "";
        }
        Set<Character> result = new LinkedHashSet<>();
        Set<Character> temp = new LinkedHashSet<>();
        for (char ch : str.toCharArray()) {
            if (!temp.add(ch)) {
                if (temp.size() > result.size()) {
                    rewriteResult(result, temp);
                }
                temp.add(ch);
            }
        }
        if (result.size() == 0 || temp.size() > result.size()) {
            rewriteResult(result, temp);
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : result) {
            sb.append(ch);
        }

        return sb.toString();
    }

    private static void rewriteResult(Set<Character> result, Set<Character> temp) {
        result.clear();
        result.addAll(temp);
        temp.clear();
    }


    public static void main(String[] args) {
        String s = "cbaebabacdcbar";
        String anagramIndices = longestUniqueSubstring(s);
        System.out.println(anagramIndices);
    }

}
