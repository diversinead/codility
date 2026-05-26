package test.java.brownbelt;

import main.java.brownbelt.OptimisingFarthestDifferent;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptimisingFarthestDifferentTest {

    private final OptimisingFarthestDifferent optimisingFarthestDifferent = new OptimisingFarthestDifferent();

    // === Provided examples ===
    @Test
    public void example1() {
        assertEquals(5, optimisingFarthestDifferent.solution(new int[]{4, 6, 2, 2, 6, 6, 4}));
    }

    @Test
    public void example2_largeAscending() {
        int[] big = new int[75001];
        for (int i = 0; i <= 75000; i++) big[i] = i;
        assertEquals(75000, optimisingFarthestDifferent.solution(big));
    }

    // === Edge cases ===
    @Test
    public void singleElement() {
        assertEquals(0, optimisingFarthestDifferent.solution(new int[]{5}));
    }

    @Test
    public void twoEqual() {
        assertEquals(0, optimisingFarthestDifferent.solution(new int[]{3, 3}));
    }

    @Test
    public void twoDifferent() {
        assertEquals(1, optimisingFarthestDifferent.solution(new int[]{3, 7}));
    }

    @Test
    public void allSame() {
        assertEquals(0, optimisingFarthestDifferent.solution(new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void allDifferent() {
        assertEquals(4, optimisingFarthestDifferent.solution(new int[]{1, 2, 3, 4, 5}));
    }

    // === Negative numbers ===
    @Test
    public void withNegatives() {
        assertEquals(2, optimisingFarthestDifferent.solution(new int[]{-1000000000, 0, 1000000000}));
    }

    @Test
    public void negativeEquals() {
        assertEquals(0, optimisingFarthestDifferent.solution(new int[]{-5, -5, -5}));
    }

    // === Endpoint patterns ===
    @Test
    public void endpointsSameMidDifferent() {
        assertEquals(3, optimisingFarthestDifferent.solution(new int[]{7, 1, 2, 3, 7}));
    }

    @Test
    public void endpointsDifferent() {
        assertEquals(4, optimisingFarthestDifferent.solution(new int[]{1, 5, 5, 5, 2}));
    }

    @Test
    public void sameEndpointsBigMiddleGap() {
        assertEquals(3, optimisingFarthestDifferent.solution(new int[]{1, 1, 2, 3, 1, 1}));
    }

    // === Stress tests ===
    @Test
    public void largeAlternating() {
        int[] alternating = new int[100000];
        for (int i = 0; i < 100000; i++) alternating[i] = i % 2;
        assertEquals(99999, optimisingFarthestDifferent.solution(alternating));
    }

    @Test
    public void largeAllSame() {
        int[] allSameLarge = new int[100000];
        Arrays.fill(allSameLarge, 42);
        assertEquals(0, optimisingFarthestDifferent.solution(allSameLarge));
    }
}
