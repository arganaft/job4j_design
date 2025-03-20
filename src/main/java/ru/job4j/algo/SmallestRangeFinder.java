package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {
    public static int[] findSmallestRange(int[] nums, int k) {
        int[] result = {0, 0};
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                result[0] = i;
            }
            result[1]++;
            if (result[1] - result[0] == k - 1) {
                return result;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}