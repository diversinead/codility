package test.java.brownbelt;

import main.java.brownbelt.EqualSegments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EqualSegmentsTest {

    private final EqualSegments equalSegments = new EqualSegments();

    // === Provided examples ===
    @Test
    public void example1() {
        assertEquals(3, equalSegments.solution(new int[]{10, 1, 3, 1, 2, 2, 1, 0, 4}));
    }

    @Test
    public void example2_allOverlapping() {
        assertEquals(1, equalSegments.solution(new int[]{5, 3, 1, 3, 2, 3}));
    }

    @Test
    public void example3_allNines() {
        assertEquals(2, equalSegments.solution(new int[]{9, 9, 9, 9, 9}));
    }

    @Test
    public void example4() {
        assertEquals(3, equalSegments.solution(new int[]{1, 5, 2, 4, 3, 3}));
    }

    // === Edge cases ===
    @Test
    public void twoElements() {
        assertEquals(1, equalSegments.solution(new int[]{4, 6}));
    }

    @Test
    public void allSameEvenLength() {
        assertEquals(2, equalSegments.solution(new int[]{7, 7, 7, 7}));
    }

    @Test
    public void allSameOddLength() {
        assertEquals(2, equalSegments.solution(new int[]{7, 7, 7, 7, 7}));
    }

    @Test
    public void allDifferent() {
        assertEquals(1, equalSegments.solution(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void twoBestSumsTied() {
        assertEquals(2, equalSegments.solution(new int[]{1, 2, 1, 2, 5, 0, 5, 0}));
    }

    @Test
    public void zeros() {
        assertEquals(2, equalSegments.solution(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void largeValuesNoOverflow() {
        assertEquals(2, equalSegments.solution(new int[]{
                1_000_000_000, 1_000_000_000, 0, 1_000_000_000, 1_000_000_000, 1_000_000_000}));
    }

    // === Stress test ===
    @Test
    public void large100kAllEqual() {
        int N = 100_000;
        int[] big = new int[N];
        for (int i = 0; i < N; i++) big[i] = 5;
        assertEquals(N / 2, equalSegments.solution(big));
    }
}
