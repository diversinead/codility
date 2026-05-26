package test.java.brownbelt;

import main.java.brownbelt.BridgeCrossing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BridgeCrossingTest {

    private final BridgeCrossing bridgeCrossing = new BridgeCrossing();

    // === Provided examples ===
    @Test
    public void example1() {
        assertEquals(4, bridgeCrossing.solution(9, new int[]{5, 3, 8, 1, 8, 7, 7, 6}));
    }

    @Test
    public void example2() {
        assertEquals(5, bridgeCrossing.solution(7, new int[]{7, 6, 5, 2, 7, 4, 5, 4}));
    }

    @Test
    public void example3() {
        assertEquals(0, bridgeCrossing.solution(7, new int[]{3, 4, 3, 1}));
    }

    @Test
    public void example4_allMustTurnBack() {
        assertEquals(10, bridgeCrossing.solution(2, new int[]{3, 7, 5, 5, 6, 3, 9, 10, 8, 4}));
    }

    // === Edge cases ===
    @Test
    public void singleCarWithinLimit() {
        assertEquals(0, bridgeCrossing.solution(5, new int[]{5}));
    }

    @Test
    public void singleCarOverLimit() {
        assertEquals(1, bridgeCrossing.solution(5, new int[]{6}));
    }

    @Test
    public void allLightWithMaxU() {
        assertEquals(0, bridgeCrossing.solution(1_000_000_000, new int[]{1, 1, 1, 1}));
    }

    @Test
    public void pairExactlyEqualsU() {
        assertEquals(1, bridgeCrossing.solution(10, new int[]{4, 6, 5, 5}));
    }

    @Test
    public void allSameSumExceedsU() {
        assertEquals(3, bridgeCrossing.solution(10, new int[]{6, 6, 6, 6}));
    }

    // === Stress test ===
    @Test
    public void large100kModular() {
        int N = 100_000;
        int[] big = new int[N];
        for (int i = 0; i < N; i++) big[i] = (i % 7) + 1;
        assertEquals(42856, bridgeCrossing.solution(8, big));
    }
}
