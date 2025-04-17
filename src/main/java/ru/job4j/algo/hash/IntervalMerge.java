package ru.job4j.algo.hash;

import java.util.Arrays;
import java.util.Comparator;

class IntervalMerge {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparing((obj) -> obj[0]));
        int mergeLen = 0;
        int[][] mergeIntervals = new int[intervals.length][2];
        mergeIntervals[0][0] = intervals[0][0];
        mergeIntervals[0][1] = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (mergeIntervals[mergeLen][0] == intervals[i][0]
                    || mergeIntervals[mergeLen][1] >= intervals[i][0]) {
                mergeIntervals[mergeLen][1] = Math.max(mergeIntervals[mergeLen][1], intervals[i][1]);
            } else {
                mergeLen++;
                mergeIntervals[mergeLen][0] = intervals[i][0];
                mergeIntervals[mergeLen][1] = intervals[i][1];
            }
        }
        return Arrays.copyOf(mergeIntervals, mergeLen + 1);

    }


}