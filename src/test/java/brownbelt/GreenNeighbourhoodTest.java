package test.java.brownbelt;

import main.java.brownbelt.GreenNeighbourhood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreenNeighbourhoodTest {

    private final GreenNeighbourhood greenNeighbourhood = new GreenNeighbourhood();

    // === Provided examples ===
    @Test
    public void example1() {
        assertEquals(2, greenNeighbourhood.solution(new int[]{1, -3, 2}));
    }

    @Test
    public void example2() {
        assertEquals(3, greenNeighbourhood.solution(new int[]{-3, 2, 4, -5, 3}));
    }

    @Test
    public void example3() {
        assertEquals(4, greenNeighbourhood.solution(new int[]{-2, 1, -3, 1}));
    }

    // === Edge cases ===
    @Test
    public void singlePositive() {
        assertEquals(0, greenNeighbourhood.solution(new int[]{5}));
    }

    @Test
    public void singleZero() {
        assertEquals(0, greenNeighbourhood.solution(new int[]{0}));
    }

    @Test
    public void singleNegative() {
        assertEquals(7, greenNeighbourhood.solution(new int[]{-7}));
    }

    @Test
    public void allPositive() {
        assertEquals(0, greenNeighbourhood.solution(new int[]{3, 4, 5, 6}));
    }

    @Test
    public void allZero() {
        assertEquals(0, greenNeighbourhood.solution(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void twoNegatives() {
        assertEquals(10, greenNeighbourhood.solution(new int[]{-5, -5}));
    }

    @Test
    public void pollutionAtBothEnds() {
        assertEquals(10, greenNeighbourhood.solution(new int[]{-5, 0, 0, 0, -5}));
    }

    @Test
    public void alternatingPollutionAndForest() {
        assertEquals(10, greenNeighbourhood.solution(new int[]{-10, 10, -10, 10}));
    }

    @Test
    public void largePollutionSingleField() {
        assertEquals(10000, greenNeighbourhood.solution(new int[]{-10000}));
    }

    // === Stress test ===
    @Test
    public void large100kWorstCase() {
        int N = 100_000;
        int[] big = new int[N];
        for (int i = 0; i < N; i++) big[i] = -10_000;
        assertEquals(1_000_000_000, greenNeighbourhood.solution(big));
    }
}
