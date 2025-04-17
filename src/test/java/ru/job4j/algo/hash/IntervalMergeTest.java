package ru.job4j.algo.hash;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class IntervalMergeTest {

    @Test
    public void whenIntervalsHaveOverlapsThenMergeOverlappingIntervals() {
        IntervalMerge IntervalMerge = new IntervalMerge();
        int[][] intervals = new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        int[][] expected = new int[][] { { 1, 6 }, { 8, 10 }, { 15, 18 } };
        int[][] result = IntervalMerge.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    public void whenIntervalsTouchAtEdgesThenMergeIntervals() {
        IntervalMerge IntervalMerge = new IntervalMerge();
        int[][] intervals = new int[][] { { 1, 4 }, { 4, 5 } };
        int[][] expected = new int[][] { { 1, 5 } };
        int[][] result = IntervalMerge.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    public void whenIntervalsAreNonOverlappingThenReturnSameIntervals() {
        IntervalMerge IntervalMerge = new IntervalMerge();
        int[][] intervals = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        int[][] expected = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        int[][] result = IntervalMerge.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    public void whenIntervalsFullyOverlapThenMergeIntoOneInterval() {
        IntervalMerge IntervalMerge = new IntervalMerge();
        int[][] intervals = new int[][] { { 1, 5 }, { 2, 6 }, { 3, 7 } };
        int[][] expected = new int[][] { { 1, 7 } };
        int[][] result = IntervalMerge.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    public void whenIntervalsAreMixedThenMergeOverlappingAndKeepNonOverlapping() {
        IntervalMerge IntervalMerge = new IntervalMerge();
        int[][] intervals = new int[][] { { 1, 4 }, { 0, 2 }, { 3, 5 } };
        int[][] expected = new int[][] { { 0, 5 } };
        int[][] result = IntervalMerge.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }

    @Test
    public void whenMoreIntervalsAreMixedThenMergeOverlappingAndKeepNonOverlapping() {
        IntervalMerge IntervalMerge = new IntervalMerge();
        int[][] intervals = new int[][] { { 1, 4 }, { 0, 2 }, { 3, 5 }, { 7, 8 }, { 7, 9 }, {  1, 3 } };
        int[][] expected = new int[][] { { 0, 5 }, { 7, 9 } };
        int[][] result = IntervalMerge.merge(intervals);
        assertThat(result).isDeepEqualTo(expected);
    }
}